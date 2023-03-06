package edu.in.mckvie.CampusNexus.servicesimpl;

import com.razorpay.RazorpayException;
import edu.in.mckvie.CampusNexus.controllers.PaymentController;
import edu.in.mckvie.CampusNexus.entities.PaymentDetails;
import edu.in.mckvie.CampusNexus.payloads.PaymentHandlerDTO;
import edu.in.mckvie.CampusNexus.repositories.PaymentRepository;
import edu.in.mckvie.CampusNexus.services.PaymentService;
import edu.in.mckvie.CampusNexus.utils.PaymentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository mckvPaymentOrderRepository;
   @Autowired
    private PaymentHandler paymentHandler;
    Logger logger= LoggerFactory.getLogger(PaymentController.class);
    @Override
    public PaymentDetails createOrder(PaymentDetails payment) throws RazorpayException {
        int amount=Integer.parseInt(payment.getAmount());
        logger.info("amount= {} ",amount);
        PaymentDetails paymentDetails=paymentHandler.createOrder(amount);
        return paymentDetails;
    }

//    @Override
//    public Map<String,String> updateOrder(Map<String,String> data) {
//        logger.trace("{update-order}",data);
//        PaymentDetails mckvPaymentOrder1=this.mckvPaymentOrderRepository.findByOrderId(data.get("razorpayOrderId").toString());
//        mckvPaymentOrder1.setPaymentId(data.get("razorpayPaymentId").toString());
//        mckvPaymentOrder1.setStatus(data.get("status").toString());
//        this.mckvPaymentOrderRepository.save(mckvPaymentOrder1);
//        return data;
//    }
    @Override
    public PaymentDetails updateOrder(PaymentHandlerDTO paymentHandlerDTO) {
        logger.info("{update-order}",paymentHandlerDTO);
        PaymentDetails mckvPaymentOrder1=this.mckvPaymentOrderRepository.findByOrderId(paymentHandlerDTO.getRazorpayOrderId().toString());
        mckvPaymentOrder1.setPaymentId(paymentHandlerDTO.getRazorpayPaymentId().toString());
        mckvPaymentOrder1.setStatus(paymentHandlerDTO.getStatus().toString());
        this.mckvPaymentOrderRepository.save(mckvPaymentOrder1);
        return mckvPaymentOrder1;
    }
}
