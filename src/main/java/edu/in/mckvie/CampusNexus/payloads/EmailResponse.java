package edu.in.mckvie.CampusNexus.payloads;

import lombok.Data;

@Data
public class EmailResponse {
    private String message;

    public EmailResponse(String message) {
        this.message = message;
    }
}
