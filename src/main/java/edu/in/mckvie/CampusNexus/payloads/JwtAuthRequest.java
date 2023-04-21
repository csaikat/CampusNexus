package edu.in.mckvie.CampusNexus.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtAuthRequest {
    private String username;
    private String password;

    public JwtAuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
