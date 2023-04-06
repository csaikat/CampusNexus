package edu.in.mckvie.CampusNexus.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;

import java.util.Date;

@Entity
@Data
public class Role {
    @Id
    private int id;
    private String name;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_on;
}
