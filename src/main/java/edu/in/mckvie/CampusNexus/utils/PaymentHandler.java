package edu.in.mckvie.CampusNexus.utils;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import edu.in.mckvie.CampusNexus.controllers.PaymentController;
import edu.in.mckvie.CampusNexus.entities.PaymentDetails;
import edu.in.mckvie.CampusNexus.repositories.PaymentRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PaymentHandler {
    @Value("${razorpay.key_id}")
    private  String key_id;
    @Value("${razorpay.key_secret}")
    private  String key_secret;
    @Autowired
    private PaymentRepository mckvPaymentOrderRepository;
    Logger logger= LoggerFactory.getLogger(PaymentController.class);
    public PaymentDetails createOrder(int amount) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient(this.key_id,this.key_secret);
        //create new order
        Order order=null;
        PaymentDetails mckvPaymentOrder=null;
        try {
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount*100);
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "txn_235425_11");
            order = razorpay.orders.create(orderRequest);
            //save order in database
            mckvPaymentOrder=new PaymentDetails();
            mckvPaymentOrder.setPaymentId(null);
            mckvPaymentOrder.setStatus(order.get("status"));
            mckvPaymentOrder.setAmount(order.get("amount")+"");
            mckvPaymentOrder.setOrderId(order.get("id"));
            mckvPaymentOrder.setReceipt(order.get("receipt"));
            mckvPaymentOrder.setCurrency(order.get("currency"));
            mckvPaymentOrder.setCreated_on(new Date());
            mckvPaymentOrderRepository.save(mckvPaymentOrder);

            logger.info("order: {}",order);
            logger.info("order: {}",mckvPaymentOrder);
        } catch (RazorpayException e) {
            logger.error(e.getMessage());
        }
        return mckvPaymentOrder;
    }
}
