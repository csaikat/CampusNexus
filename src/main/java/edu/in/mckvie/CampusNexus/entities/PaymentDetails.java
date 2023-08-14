package edu.in.mckvie.CampusNexus.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="fees_orders")
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
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;
//    @OneToOne(mappedBy = "paymentDetails")
//    private StudentPayment studentPayment;
    @Transient
    private String universityRollNumber;
    private int semId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_on;
}
