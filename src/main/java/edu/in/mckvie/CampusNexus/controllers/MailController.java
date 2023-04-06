package edu.in.mckvie.CampusNexus.controllers;

import edu.in.mckvie.CampusNexus.payloads.MailDTO;
import edu.in.mckvie.CampusNexus.services.MailService;
import edu.in.mckvie.CampusNexus.payloads.EmailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mail")
public class MailController {
    @Autowired
    MailService mailService;
    @PostMapping("/send-mail")
    public ResponseEntity<EmailResponse> sendMail(@RequestBody MailDTO mailDTO){
        System.out.println(mailDTO.getTo());
        if(mailService.sendMail(mailDTO))
            return ResponseEntity.ok(new EmailResponse("Email is send successfully !!"));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email is not send !!"));
    }
}
