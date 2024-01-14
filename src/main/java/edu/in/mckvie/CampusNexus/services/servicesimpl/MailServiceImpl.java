package edu.in.mckvie.CampusNexus.services.servicesimpl;

import edu.in.mckvie.CampusNexus.payloads.MailDTO;
import edu.in.mckvie.CampusNexus.services.MailService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Properties;
@Service
public class MailServiceImpl implements MailService {
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Override
    public boolean sendMail(MailDTO mailDTO) {
        boolean flag=false;
        System.out.println(username+password);
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
    @Autowired
    private JavaMailSender javaMailSender;
    public boolean sendEmailWithAttachment(MailDTO mailDTO) throws MessagingException {
        boolean flag=false;
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(username);
        mimeMessageHelper.setTo(mailDTO.getTo());
        mimeMessageHelper.setSubject(mailDTO.getSubject());
        mimeMessageHelper.setText(mailDTO.getMessage());

        FileSystemResource file = new FileSystemResource(new File(mailDTO.getAttachment()));
        mimeMessageHelper.addAttachment(file.getFilename(),file);
        try {
            javaMailSender.send(mimeMessage);
            flag=true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
