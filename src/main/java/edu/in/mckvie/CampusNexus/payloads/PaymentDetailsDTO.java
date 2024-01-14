package edu.in.mckvie.CampusNexus.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;
@Data
public class PaymentDetailsDTO {
    private Long id;
    @NotEmpty(message = "must have orderId")
    private String orderId;
    @NotEmpty(message = "must have amount")
    private String amount;
    @NotEmpty(message = "must have receipt")
    private String receipt;
    private String status;
    private String paymentId;
    @NotEmpty(message = "must have currency")
    private String currency;
//    private UserDto userDto;
    private String universityRollNumber;
    private int semId;
    private String pdfUri;
    private Date created_on;
}
