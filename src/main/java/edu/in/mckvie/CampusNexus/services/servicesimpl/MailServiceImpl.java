package edu.in.mckvie.CampusNexus.services.servicesimpl;

import edu.in.mckvie.CampusNexus.payloads.MailDTO;
import edu.in.mckvie.CampusNexus.services.MailService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.Properties;
@Service
public class MailServiceImpl implements MailService {
    private String username="paulmonish.mp@gmail.com";
    private String password="tehnmosxdbiaapzx";
    @Override
    public boolean sendMail(MailDTO mailDTO) {
        boolean flag=false;
        Properties properties=new Properties();
        properties.put("mail.smtp.auth",true);
        properties.put("mail.smtp.starttls.enable",true);
        properties.put("mail.smtp.port",587);
        properties.put("mail.smtp.host","smtp.gmail.com");

        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });
        try{
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(mailDTO.getTo()));
            message.setSubject(mailDTO.getSubject());
            message.setText(mailDTO.getMessage());
            Transport.send(message);
            flag=true;

        }catch (Exception e){
            e.printStackTrace();
        }


        return flag;
    }
}
