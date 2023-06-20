package com.example.bankingappdemo.services;

import com.example.bankingappdemo.dto.requestDto.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderMail;
    @Override
    public ResponseEntity<String> sendEmail(EmailDetails emailDetails) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(senderMail);
            simpleMailMessage.setTo(emailDetails.getRecipient());
            simpleMailMessage.setSubject(emailDetails.getSubject());
            simpleMailMessage.setText(emailDetails.getMessage());

            javaMailSender.send(simpleMailMessage);

            return new ResponseEntity<>("Message sent successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while sending mail", HttpStatus.BAD_REQUEST);
        }


    }
}
