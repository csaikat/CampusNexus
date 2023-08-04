package edu.in.mckvie.CampusNexus.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="defaulter_list")
public class DefaulterList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    private User user;
    @Column(columnDefinition = "boolean default 0")
    private boolean SEM1;
    @Column(columnDefinition = "boolean default 0")
    private boolean SEM2;
    @Column(columnDefinition = "boolean default 0")
    private boolean SEM3;
    @Column(columnDefinition = "boolean default 0")
    private boolean SEM4;
    @Column(columnDefinition = "boolean default 0")
    private boolean SEM5;
    @Column(columnDefinition = "boolean default 0")
    private boolean SEM6;
    @Column(columnDefinition = "boolean default 0")
    private boolean SEM7;
    @Column(columnDefinition = "boolean default 0")
    private boolean SEM8;




}
