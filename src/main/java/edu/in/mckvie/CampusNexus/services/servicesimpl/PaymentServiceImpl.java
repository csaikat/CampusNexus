package edu.in.mckvie.CampusNexus.services.servicesimpl;

import com.razorpay.RazorpayException;
import edu.in.mckvie.CampusNexus.controllers.PaymentController;
import edu.in.mckvie.CampusNexus.entities.*;
import edu.in.mckvie.CampusNexus.exceptions.ResourceNotFoundException;
import edu.in.mckvie.CampusNexus.filter.CreateOrderFilter;
import edu.in.mckvie.CampusNexus.payloads.MailDTO;
import edu.in.mckvie.CampusNexus.payloads.PaymentDetailsDTO;
import edu.in.mckvie.CampusNexus.payloads.PaymentHandlerDTO;
import edu.in.mckvie.CampusNexus.repositories.*;
import edu.in.mckvie.CampusNexus.services.InvoiceService;
import edu.in.mckvie.CampusNexus.services.MailService;
import edu.in.mckvie.CampusNexus.services.PaymentService;
import edu.in.mckvie.CampusNexus.utils.PaymentHandler;
import jakarta.mail.MessagingException;
import net.sf.jasperreports.engine.JRException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.text.ParseException;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentHandler paymentHandler;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CreateOrderFilter createOrderFilter;
    @Autowired
    private SemesterRepositories semesterRepositories;

    Logger logger= LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private StudentPaymentRepository studentPaymentRepository;
    @Autowired
    private DefaulterListRepository defaulterListRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private InvoiceService invoiceService;
    @Override
    public PaymentDetailsDTO createOrder(PaymentDetailsDTO paymentDetailsDTO) throws RazorpayException {
        PaymentDetails savedPaymentDetails=null;
        System.out.println(paymentDetailsDTO);
        PaymentDetails paymentDetails=this.modelMapper.map(paymentDetailsDTO,PaymentDetails.class);
        int amount=Integer.parseInt(paymentDetails.getAmount());
        String uRoll=paymentDetailsDTO.getUniversityRollNumber();
        logger.info("amount= {} ",amount);
        logger.info("uRoll= {} ",uRoll);
        int semId=paymentDetailsDTO.getSemId();
        logger.info("semId= {} ",semId);
        //filter
        String order_id=createOrderFilter.isOrderAlreadyCreated(uRoll,semId);
        if(order_id == null){
            savedPaymentDetails=paymentHandler.createOrder(amount,uRoll,semId);
            savedPaymentDetails.setUniversityRollNumber(uRoll);
            savedPaymentDetails.setSemId(semId);
        }

        else{
            savedPaymentDetails=paymentRepository.findByOrderId(order_id);
            savedPaymentDetails.setUniversityRollNumber(uRoll);
            savedPaymentDetails.setSemId(semId);
        }

        return this.modelMapper.map(savedPaymentDetails,PaymentDetailsDTO.class);
    }

    @Override
    public PaymentDetailsDTO updateOrder(PaymentHandlerDTO paymentHandlerDTO) throws JRException, FileNotFoundException, MessagingException, ParseException {
        PaymentDetails paymentDetails=null;
        try{
            paymentDetails=this.paymentRepository.findByOrderId(paymentHandlerDTO.getRazorpayOrderId().toString());
        }catch(Exception e){
            throw new ResourceNotFoundException("payment","paymentId",paymentHandlerDTO.getRazorpayOrderId().toString());
        }
        //due logic
        StudentPayment studentPayment=new StudentPayment();
        Semester semester=this.semesterRepositories.findById(paymentHandlerDTO.getSemId()).orElseThrow();
        studentPayment.setSemester(semester);
        double due=(semester.getFees().getFees()-(paymentHandlerDTO.getAmount()/100));
        studentPayment.setDueFees(due);
        PaymentDetails paymentDetail=this.paymentRepository.findById(paymentHandlerDTO.getPayment_id()).orElseThrow();
        studentPayment.setPaymentDetails(paymentDetail);
        this.studentPaymentRepository.save(studentPayment);


        String uRoll=paymentHandlerDTO.getUniversityRollNumber();
        logger.info("h_uRoll= {} ",uRoll);
        //userid
        User user=this.userRepository.findByUniversityRollNumber(uRoll).get();
        int userId=user.getId();
        String userMailId=user.getEmail();
        System.out.println(userId+userMailId);
        //add in defaulter list
        int semId=semester.getId();

        DefaulterList defaulterList=this.defaulterListRepository.findByUserId(userId);
        switch (semId) {
            case 1 -> defaulterList.setSEM1(true);
            case 2 -> defaulterList.setSEM2(true);
            case 3 -> defaulterList.setSEM3(true);
            case 4 -> defaulterList.setSEM4(true);
            case 5 -> defaulterList.setSEM5(true);
            case 6 -> defaulterList.setSEM6(true);
            case 7 -> defaulterList.setSEM7(true);
            case 8 -> defaulterList.setSEM8(true);
        }
        this.defaulterListRepository.save(defaulterList);

        paymentDetails.setPaymentId(paymentHandlerDTO.getRazorpayPaymentId().toString());
        paymentDetails.setStatus(paymentHandlerDTO.getStatus().toString());
        PaymentDetails savedpaymentDetails=this.paymentRepository.save(paymentDetails);
        //generate invoice

        String pdfName=this.invoiceService.generateInvoice(userId,semId);

        //send mail for successful payment with attachment
        MailDTO mailDTO=new MailDTO();
        mailDTO.setTo(userMailId);
        mailDTO.setSubject("Fees Payment");
        mailDTO.setMessage("Your payment is successfull");
        mailDTO.setAttachment("C:\\Users\\monis\\Desktop\\CampusNexus\\campus-nexus-backend\\"+pdfName);
        this.mailService.sendEmailWithAttachment(mailDTO);

        return  this.modelMapper.map(savedpaymentDetails,PaymentDetailsDTO.class);


    }
}
