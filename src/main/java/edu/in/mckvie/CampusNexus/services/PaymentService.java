package edu.in.mckvie.CampusNexus.services;

import com.razorpay.RazorpayException;
import edu.in.mckvie.CampusNexus.payloads.PaymentDetailsDTO;
import edu.in.mckvie.CampusNexus.payloads.PaymentHandlerDTO;
import jakarta.mail.MessagingException;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.text.ParseException;


public interface PaymentService {
    public PaymentDetailsDTO createOrder(PaymentDetailsDTO paymentDetailsDTO) throws RazorpayException;
    public PaymentDetailsDTO updateOrder(PaymentHandlerDTO paymentHandlerDTO) throws JRException, FileNotFoundException, MessagingException, ParseException;
}
