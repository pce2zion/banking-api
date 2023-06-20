package com.example.bankingappdemo.controllers;

import com.example.bankingappdemo.dto.requestDto.EmailDetails;
import com.example.bankingappdemo.dto.responseDto.EmailOperationRest;
import com.example.bankingappdemo.enums.OperationName;
import com.example.bankingappdemo.enums.OperationStatusResult;
import com.example.bankingappdemo.services.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {


   private final EmailSenderService emailSenderService;

    @PostMapping("/sendEmail")
    public EmailOperationRest sendEmail(@RequestBody EmailDetails emailDetails){

        emailSenderService.sendEmail(emailDetails);

        return EmailOperationRest.builder()
                .operationName(OperationName.VERIFY_EMAIL.name())
                .operationResult(OperationStatusResult.SUCCESS.name())
                .build();

    }
}
