package edu.in.mckvie.CampusNexus.services;

import com.razorpay.RazorpayException;
import edu.in.mckvie.CampusNexus.entities.PaymentDetails;
import edu.in.mckvie.CampusNexus.payloads.PaymentHandlerDTO;

import java.util.Map;

public interface PaymentService {
    public PaymentDetails createOrder(PaymentDetails payment) throws RazorpayException;
    public PaymentDetails updateOrder(PaymentHandlerDTO paymentHandlerDTO);
}
