package edu.in.mckvie.CampusNexus.services;

import edu.in.mckvie.CampusNexus.payloads.MailDTO;
import jakarta.mail.MessagingException;

public interface MailService {
    public boolean sendMail(MailDTO mailDTO);
    public boolean sendEmailWithAttachment(MailDTO mailDTO) throws MessagingException;
}
