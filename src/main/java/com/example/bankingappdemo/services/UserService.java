package com.example.bankingappdemo.services;

import com.example.bankingappdemo.dto.requestDto.CreditDebitRequest;
import com.example.bankingappdemo.dto.requestDto.EnquiryRequest;
import com.example.bankingappdemo.dto.requestDto.FundsTransferRequest;
import com.example.bankingappdemo.dto.requestDto.UserRequest;
import com.example.bankingappdemo.dto.responseDto.BankResponse;
import org.springframework.stereotype.Service;


public interface UserService {
    BankResponse createAccount(UserRequest userRequest);

    BankResponse balanceEnquiry(EnquiryRequest enquiryRequest);

    BankResponse fundsTransfer(FundsTransferRequest fundsTransferRequest);

    String getAccountName(EnquiryRequest enquiryRequest);

    BankResponse creditAccount(CreditDebitRequest creditDebitRequest);

    BankResponse debitAccount(CreditDebitRequest creditDebitRequest);
}
