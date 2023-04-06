package edu.in.mckvie.CampusNexus.payloads;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.in.mckvie.CampusNexus.entities.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Data
public class UserDto {
    @NotEmpty(message = "Name must not be empty")
    private String name;
    @NotEmpty(message = "Email must not be empty")
    @Email
    private String email;
    @NotEmpty(message = "University RollNumber must not be empty")
    private String universityRollNumber;
    @NotEmpty(message = "Phone Number must not be empty")
    private String contactNumber;
    @NotNull(message = "D.O.B must not be Null")
    private Date dob;
    private String password;
    private Set<RoleDTO> roles=new HashSet<>();
    private Date created_on;
}
