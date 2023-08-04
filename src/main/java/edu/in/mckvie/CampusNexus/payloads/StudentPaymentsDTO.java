package edu.in.mckvie.CampusNexus.payloads;

import edu.in.mckvie.CampusNexus.entities.PaymentDetails;
import edu.in.mckvie.CampusNexus.entities.Semester;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentPaymentsDTO {
    private Integer id;
    private double dueFees;
    private PaymentDetails paymentDetails;
    private Semester semester;
}
