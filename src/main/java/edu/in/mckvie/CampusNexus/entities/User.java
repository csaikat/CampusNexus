package edu.in.mckvie.CampusNexus.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
     @Column(unique = true,nullable = false)
    private String email;
    private String password;
     @Column(unique = true)
    private String universityRollNumber;
    private String contactNumber;
    @Temporal(TemporalType.DATE)
    private Date dob;

    //add
    @Column(unique = true,nullable = false,name = "collageRollNumber")
    private String collageRollNumber;
     @Column(unique = true,nullable = false,name = "examRollNumber")
    private String examRollNumber;
    @Column(columnDefinition = "boolean default 0")
    private boolean isLateral;
    @Column(columnDefinition = "boolean default 0")
    private boolean isStreamChanger;
    @Column(columnDefinition = "boolean default 0")
    private boolean isEnrolled;
    //
    @ManyToMany(cascade = CascadeType.PERSIST ,fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles=new HashSet<>();
    //
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_subject",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subject=new HashSet<>();
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Department department;
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Semester semester;
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private UpRole role1;
    private String batch;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_on;
    @Column(columnDefinition = "boolean default 0")
    private boolean hasStudentCreditCard;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
