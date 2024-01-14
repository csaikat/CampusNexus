package edu.in.mckvie.CampusNexus.payloads;

import lombok.Data;

@Data
public class MailDTO {
    private String to;
    private String from;
    private String subject;
    private String message;
    private String attachment;
}
