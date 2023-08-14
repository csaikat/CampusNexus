package edu.in.mckvie.CampusNexus.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "sem")
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "sem",nullable = false)
    private String name;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Fees fees;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Year year;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_on;
}
