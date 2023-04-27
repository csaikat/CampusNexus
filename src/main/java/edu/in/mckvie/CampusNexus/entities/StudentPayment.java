package edu.in.mckvie.CampusNexus.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "payment_list")
public class StudentPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(columnDefinition = "integer default 0")
    private double dueFees;
    @OneToOne
    private PaymentDetails paymentDetails;
    @OneToOne
    private Semester semester;
}
