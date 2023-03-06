package edu.in.mckvie.CampusNexus.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="orders")
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderId;
    private String amount;
    private String receipt;
    private String status;
    private String paymentId;
    private String currency;
   @Temporal(TemporalType.TIMESTAMP)
    private Date created_on;
}
