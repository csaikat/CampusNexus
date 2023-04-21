package edu.in.mckvie.CampusNexus.controllers;

import com.razorpay.RazorpayException;
import edu.in.mckvie.CampusNexus.entities.PaymentDetails;
import edu.in.mckvie.CampusNexus.exceptions.ResourceNotFoundException;
import edu.in.mckvie.CampusNexus.payloads.PaymentDetailsDTO;
import edu.in.mckvie.CampusNexus.payloads.PaymentHandlerDTO;
import edu.in.mckvie.CampusNexus.services.servicesimpl.PaymentServiceImpl;
import org.json.HTTP;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    @Autowired
    PaymentServiceImpl paymentService;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("/create-order")
    public ResponseEntity<PaymentDetailsDTO> createOrder(@RequestBody PaymentDetailsDTO paymentDetailsDTO) throws RazorpayException {
        System.out.println(paymentDetailsDTO);
        PaymentDetailsDTO createPaymentDetailsDTO=paymentService.createOrder(paymentDetailsDTO);
        return new ResponseEntity<>(createPaymentDetailsDTO, HttpStatus.CREATED);
    }
    @PutMapping("/update-order")
    public ResponseEntity<PaymentDetailsDTO> updateOrder(@RequestBody PaymentHandlerDTO paymentHandlerDTO){
        PaymentDetailsDTO updatedPaymentDetailsDTO=paymentService.updateOrder(paymentHandlerDTO);
        return ResponseEntity.ok(updatedPaymentDetailsDTO);
    }
}
