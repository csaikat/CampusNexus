package edu.in.mckvie.CampusNexus.services.servicesimpl;

import com.razorpay.RazorpayException;
import edu.in.mckvie.CampusNexus.controllers.PaymentController;
import edu.in.mckvie.CampusNexus.entities.PaymentDetails;
import edu.in.mckvie.CampusNexus.exceptions.ResourceNotFoundException;
import edu.in.mckvie.CampusNexus.payloads.PaymentDetailsDTO;
import edu.in.mckvie.CampusNexus.payloads.PaymentHandlerDTO;
import edu.in.mckvie.CampusNexus.repositories.PaymentRepository;
import edu.in.mckvie.CampusNexus.repositories.UserRepository;
import edu.in.mckvie.CampusNexus.services.PaymentService;
import edu.in.mckvie.CampusNexus.utils.PaymentHandler;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentHandler paymentHandler;
    @Autowired
    private ModelMapper modelMapper;
    Logger logger= LoggerFactory.getLogger(PaymentController.class);


    @Autowired
    UserRepository userRepository;
    @Override
    public PaymentDetailsDTO createOrder(PaymentDetailsDTO paymentDetailsDTO) throws RazorpayException {
        PaymentDetails paymentDetails=this.modelMapper.map(paymentDetailsDTO,PaymentDetails.class);
        int amount=Integer.parseInt(paymentDetails.getAmount());
        String uRoll=paymentDetailsDTO.getUniversityRollNumber();
        logger.info("amount= {} ",amount);
        logger.info("uRoll= {} ",uRoll);
        //filter
        int id=userRepository.findByUniversityRollNumber(uRoll).get().getId();
        logger.info("id= {} ",id);
        System.out.println(userRepository.findOrderIdById(id));

        PaymentDetails savedPaymentDetails=paymentHandler.createOrder(amount,uRoll);
        return this.modelMapper.map(savedPaymentDetails,PaymentDetailsDTO.class);
    }
    @Override
    public PaymentDetailsDTO updateOrder(PaymentHandlerDTO paymentHandlerDTO) {
        PaymentDetails paymentDetails=null;
        try{
            paymentDetails=this.paymentRepository.findByOrderId(paymentHandlerDTO.getRazorpayOrderId().toString());
        }catch(Exception e){
            throw new ResourceNotFoundException("payment","paymentId",paymentHandlerDTO.getRazorpayOrderId().toString());
        }
        paymentDetails.setPaymentId(paymentHandlerDTO.getRazorpayPaymentId().toString());
        paymentDetails.setStatus(paymentHandlerDTO.getStatus().toString());
        PaymentDetails savedpaymentDetails=this.paymentRepository.save(paymentDetails);
        return this.modelMapper.map(savedpaymentDetails,PaymentDetailsDTO.class);
    }
}
