package edu.in.mckvie.CampusNexus.services;

import com.razorpay.RazorpayException;
import edu.in.mckvie.CampusNexus.entities.PaymentDetails;
import edu.in.mckvie.CampusNexus.payloads.PaymentDetailsDTO;
import edu.in.mckvie.CampusNexus.payloads.PaymentHandlerDTO;



public interface PaymentService {
    public PaymentDetailsDTO createOrder(PaymentDetailsDTO paymentDetailsDTO) throws RazorpayException;
    public PaymentDetailsDTO updateOrder(PaymentHandlerDTO paymentHandlerDTO);
}
