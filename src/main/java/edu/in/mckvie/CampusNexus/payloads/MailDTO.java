package edu.in.mckvie.CampusNexus.payloads;

import lombok.Data;
import org.springframework.http.codec.multipart.Part;

@Data
public class MailDTO {
    private String to;
    private String from;
    private String subject;
    private String message;
    private byte[] attachment;
}
