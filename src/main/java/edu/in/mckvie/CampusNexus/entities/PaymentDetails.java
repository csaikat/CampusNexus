package edu.in.mckvie.CampusNexus.entities;

import edu.in.mckvie.CampusNexus.payloads.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

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
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
   @Temporal(TemporalType.TIMESTAMP)
    private Date created_on;
}
