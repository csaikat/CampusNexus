package edu.in.mckvie.CampusNexus.payloads;

import lombok.Data;

@Data
public class PaymentHandlerDTO {
    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignature;
    private String status;
}
