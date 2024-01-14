package edu.in.mckvie.CampusNexus.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PaymentHandlerDTO {
    @NotEmpty(message = "must have paymentId")
    private String razorpayPaymentId;
    @NotEmpty(message = "must have orderId")
    private String razorpayOrderId;
    private String razorpaySignature;
    private String status;
//    private UserDto userDto;
    private String universityRollNumber;
    private int semId;
    private double amount;
    private Long payment_id;
}
