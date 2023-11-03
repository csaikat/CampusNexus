package edu.in.mckvie.CampusNexus.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "subjects")
public class Subject {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        @Column(unique = true,nullable = false)
        private String subjectCode;
        @Column(unique = true,nullable = false)
        private String subjectName;
        @Column(columnDefinition = "boolean default 1")
        private boolean isActive;
        @Column(columnDefinition = "boolean default 0")
        private boolean isOptional;
        @ManyToOne(cascade = CascadeType.REMOVE)
        private Department department;
        @ManyToOne(cascade = CascadeType.REMOVE)
        private Semester semester;
        private String electiveNo;
        @CreationTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        private Date created_on;
        @Transient
        private int count;
}
