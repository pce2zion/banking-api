package com.example.bankingappdemo.services;

import com.example.bankingappdemo.dto.requestDto.*;
import com.example.bankingappdemo.dto.responseDto.AccountInfo;
import com.example.bankingappdemo.dto.responseDto.BankResponse;
import com.example.bankingappdemo.entity.User;
import com.example.bankingappdemo.enums.Status;
import com.example.bankingappdemo.repository.UserRepository;
import com.example.bankingappdemo.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    AccountUtils accountUtils;



    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public BankResponse createAccount(UserRequest userRequest) {
        User returnedUser = userRepository.findUserByEmail(userRequest.getEmail());
        if(returnedUser != null) {
            return BankResponse.builder()
                    .responseCode(accountUtils.accountExistsCode)
                    .responseMessage(accountUtils.accountExistsMessage)
                    .accountInfo(null)
                    .build();
        }

        User user = new User();
        modelMapper.map(userRequest, user );
        user.setAccountNumber(accountUtils.createAccountNo());
        user.setAccountBalance(BigDecimal.ZERO);
        user.setStatus(Status.ACTIVE);
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        log.info(user.getAccountNumber());


        User savedUser =userRepository.save(user);
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedUser.getEmail())
                .subject("ACCOUNT CREATION")
                .message("Congratulations your account has been successfully created." +
                        "\nYour account details:" +
                        "\nAccount Name: "+ savedUser.getFirstName() + " " + savedUser.getLastName() + " " + savedUser.getOtherName() +
                        "\nAccount number: "+ savedUser.getAccountNumber() +
                        "\nAccountBalance: "+ savedUser.getAccountBalance()
                )
                .build();
        emailSenderService.sendEmail(emailDetails);

        return BankResponse.builder()
                .responseMessage(accountUtils.accountCreationMessage)
                .responseCode(accountUtils.accountCreationCode)
                .accountInfo(AccountInfo.builder()
                        .accountName(savedUser.getFirstName() + " " + savedUser.getOtherName() + " " + savedUser.getLastName())
                        .accountBalance(savedUser.getAccountBalance())
                        .accountNumber(savedUser.getAccountNumber())
                        .build())
                .build();
    }

    @Override
    public BankResponse balanceEnquiry(EnquiryRequest enquiryRequest) {
        User foundUser =userRepository.findUserByAccountNumber(enquiryRequest.getAccountNumber());
        if(foundUser == null){
            return BankResponse.builder()
                    .responseCode(accountUtils.accountNotExistsCode)
                    .responseMessage(accountUtils.accountNotExistMessage)
                    .accountInfo(null)
                    .build();
        }

         return BankResponse.builder()
                .responseCode(accountUtils.accountFoundCode)
                .responseMessage(accountUtils.accountFoundSuccess)
                .accountInfo(AccountInfo.builder()
                        .accountNumber(foundUser.getAccountNumber())
                        .accountName(foundUser.getFirstName() + " " + foundUser.getOtherName() + " " + foundUser.getLastName())
                        .accountBalance(foundUser.getAccountBalance())
                        .build())
                .build();
    }

    @Override
    public BankResponse fundsTransfer(FundsTransferRequest fundsTransferRequest) {
        User foundUser = userRepository.findUserByAccountNumber(fundsTransferRequest.getSourceAccountNumber());
        if(foundUser == null){
             return BankResponse.builder()
                    .responseCode(accountUtils.accountExistsCode)
                    .responseMessage(accountUtils.accountExistsMessage)
                    .accountInfo(null)
                    .build();
        }
        BigInteger availableBal = foundUser.getAccountBalance().toBigInteger();
        BigInteger amountToTransfer = fundsTransferRequest.getAmount().toBigInteger();

        if(availableBal.intValue() == 0 || amountToTransfer.intValue() > availableBal.intValue() ){
            return BankResponse.builder()
                    .responseCode(accountUtils.insufficientFundsCode)
                    .responseMessage(accountUtils.insufficientFundsMessage)
                    .accountInfo(null)
                    .build();
        }
        else {

            foundUser.setAccountBalance(foundUser.getAccountBalance().subtract(fundsTransferRequest.getAmount()));
            User savedUser = userRepository.save(foundUser);
            EmailDetails emailDetails = EmailDetails.builder()
                    .recipient(savedUser.getEmail())
                    .subject("FUNDS TRANSFER")
                    .message("Congratulations your funds have been transferred." +
                            "\nYour account details:" +
                            "\nAccount Name: "+ savedUser.getFirstName() + " " + savedUser.getLastName() + " " + savedUser.getOtherName() +
                            "\nAccount number: "+ savedUser.getAccountNumber() +
                            "\nAccountBalance: "+ savedUser.getAccountBalance()
                    )
                    .build();
            emailSenderService.sendEmail(emailDetails);
            return BankResponse.builder()
                    .responseCode(accountUtils.fundsTransferCode)
                    .responseMessage(accountUtils.FundsTransferMessage)
                    .accountInfo(AccountInfo.builder()
                            .accountNumber(savedUser.getAccountNumber())
                            .accountName(savedUser.getFirstName() + " " + savedUser.getOtherName() + " " + savedUser.getLastName())
                            .accountBalance(savedUser.getAccountBalance())
                            .build())
                    .build();
        }
    }

    @Override
    public String getAccountName(EnquiryRequest enquiryRequest) {
      User returnedUser  = userRepository.findUserByAccountNumber(enquiryRequest.getAccountNumber());
        if(returnedUser == null){
            return accountUtils.accountNotExistMessage;
        }
        return returnedUser.getFirstName() + " " + returnedUser.getOtherName() + " " + returnedUser.getLastName() + " ";
    }

    @Override
    public BankResponse creditAccount(CreditDebitRequest creditDebitRequest) {
        User foundUser = userRepository.findUserByAccountNumber(creditDebitRequest.getAccountNumber());
        if(foundUser == null){
            return BankResponse.builder()
                    .responseCode(accountUtils.accountNotExistsCode)
                    .responseMessage(accountUtils.accountNotExistMessage)
                    .accountInfo(null)
                    .build();
        }
        foundUser.setAccountBalance(foundUser.getAccountBalance().add(creditDebitRequest.getAmount()));
        User savedUser = userRepository.save(foundUser);
        return BankResponse.builder()
                .responseCode(accountUtils.accountCreditCode)
                .responseMessage(accountUtils.accountCreditMessage)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(savedUser.getAccountBalance())
                        .accountNumber(savedUser.getAccountNumber())
                        .accountName(savedUser.getFirstName() + " " + savedUser.getOtherName() + " " + savedUser.getLastName())
                .build())
                .build();
    }

    @Override
    public BankResponse debitAccount(CreditDebitRequest creditDebitRequest) {
        User foundUser = userRepository.findUserByAccountNumber(creditDebitRequest.getAccountNumber());
        if(foundUser == null){
            return BankResponse.builder()
                    .responseCode(accountUtils.accountNotExistsCode)
                    .responseMessage(accountUtils.accountNotExistMessage)
                    .accountInfo(null)
                    .build();
        }
        BigInteger availableBal = foundUser.getAccountBalance().toBigInteger();
        BigInteger amountToDebit = creditDebitRequest.getAmount().toBigInteger();

        if(availableBal.intValue() == 0 || amountToDebit.intValue() > availableBal.intValue() ){
            return BankResponse.builder()
                    .responseCode(accountUtils.insufficientFundsCode)
                    .responseMessage(accountUtils.insufficientFundsMessage)
                    .accountInfo(null)
                    .build();
        }
        else {
            foundUser.setAccountBalance(foundUser.getAccountBalance().subtract(creditDebitRequest.getAmount()));
            userRepository.save(foundUser);

            return BankResponse.builder()
                    .responseCode(accountUtils.accountDebitCode)
                    .responseMessage(accountUtils.accountDebitMessage)
                    .accountInfo(AccountInfo.builder()
                            .accountBalance(foundUser.getAccountBalance())
                            .accountNumber(foundUser.getAccountNumber())
                            .accountName(foundUser.getFirstName() + " " + foundUser.getOtherName() + " " + foundUser.getLastName())
                            .build())
                    .build();
        }
    }
}
