package edu.in.mckvie.CampusNexus.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.in.mckvie.CampusNexus.entities.Department;
import edu.in.mckvie.CampusNexus.entities.Semester;
import edu.in.mckvie.CampusNexus.entities.UpRole;
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

    //add

    private String collageRollNumber;

    private String examRollNumber;
    private boolean isEnrolled;
    private boolean isLateral;
    private boolean isStreamChanger;
    //
    private Semester semester;
    private String password;
    private Department department;
    private Set<RoleDTO> roles=new HashSet<>();
    private Date created_on;
    private UpRole role1;
    private String batch;
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }
}
