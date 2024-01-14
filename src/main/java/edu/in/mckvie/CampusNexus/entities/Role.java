package edu.in.mckvie.CampusNexus.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    private int id;
    @Column(name="role",nullable = false)
    private String name;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_on;
}
