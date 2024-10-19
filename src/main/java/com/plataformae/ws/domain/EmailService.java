package com.plataformae.ws.domain;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private  String from;

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(String userEmail,String subject,String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(userEmail);
        helper.setFrom(from);
        helper.setSubject(subject);
        helper.setText(body, true);

        mailSender.send(message);
    }
}