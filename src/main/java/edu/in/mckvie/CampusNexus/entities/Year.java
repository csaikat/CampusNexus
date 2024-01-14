package edu.in.mckvie.CampusNexus.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "class")
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "class",nullable = false)
    private String name;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_on;
}
