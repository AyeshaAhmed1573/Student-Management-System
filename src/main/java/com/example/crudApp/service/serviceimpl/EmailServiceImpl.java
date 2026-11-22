package com.example.crudApp.service.serviceimpl;

import com.example.crudApp.service.services.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final String from;
    EmailServiceImpl(JavaMailSender mailSender, @Value("${spring.mail.username}") String from){
        this.mailSender=mailSender;
        this.from=from;
    }
    public void send(String to, String subject, String body){

        SimpleMailMessage msg=new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(body);
        mailSender.send(msg);
    }
}