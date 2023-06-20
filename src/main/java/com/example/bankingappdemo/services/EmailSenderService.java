package com.example.bankingappdemo.services;

import com.example.bankingappdemo.dto.requestDto.EmailDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface EmailSenderService {

    ResponseEntity<String> sendEmail(EmailDetails emailDetails);
}
