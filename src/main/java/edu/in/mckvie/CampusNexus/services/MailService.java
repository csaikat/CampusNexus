package edu.in.mckvie.CampusNexus.services;

import edu.in.mckvie.CampusNexus.payloads.MailDTO;

public interface MailService {
    public boolean sendMail(MailDTO mailDTO);
}
