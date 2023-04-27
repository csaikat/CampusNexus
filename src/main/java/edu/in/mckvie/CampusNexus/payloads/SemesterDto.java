package edu.in.mckvie.CampusNexus.payloads;

import edu.in.mckvie.CampusNexus.entities.Fees;
import lombok.Data;

@Data
public class SemesterDto {
    private Integer id;
    private String name;
    private Fees fees;
}
