package edu.in.mckvie.CampusNexus.payloads;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;
@Data
public class PaymentDetailsDTO {
    private String orderId;
    private String amount;
    private String receipt;
    private String status;
    private String paymentId;
    private String currency;
    private Date created_on;
}
