package edu.in.mckvie.CampusNexus.controllers;

import com.razorpay.RazorpayException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import edu.in.mckvie.CampusNexus.entities.PaymentDetails;
import edu.in.mckvie.CampusNexus.payloads.PaymentHandlerDTO;
import edu.in.mckvie.CampusNexus.servicesimpl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin
public class PaymentController {
    @Autowired
    PaymentServiceImpl paymentService;
    //creating order for payment
    @PostMapping("/create-order")
    public ResponseEntity<PaymentDetails> createOrder(@RequestBody PaymentDetails payment) throws RazorpayException {
        PaymentDetails paymentDetails=paymentService.createOrder(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentDetails);
    }
    @PutMapping("/update-order")
    public ResponseEntity<PaymentDetails> updateOrder(@RequestBody PaymentHandlerDTO paymentHandlerDTO){
        PaymentDetails paymentDetails=paymentService.updateOrder(paymentHandlerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(paymentDetails);
    }
//    public ResponseEntity<?> sendOTP(@RequestBody Map<String,String> number){
////        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        Message message = Message.creator(new PhoneNumber("+916290442416"),
//                new PhoneNumber("+919331657070"),
//                "This is the ship that made the Kessel Run in fourteen parsecs?").create();
//
//        System.out.println(message.getSid());
//        return ResponseEntity.status(HttpStatus.OK).body(number);
//    }
}
