package com.example.bankingappdemo.controllers;
import com.example.bankingappdemo.dto.requestDto.CreditDebitRequest;
import com.example.bankingappdemo.dto.requestDto.EnquiryRequest;
import com.example.bankingappdemo.dto.requestDto.FundsTransferRequest;
import com.example.bankingappdemo.dto.requestDto.UserRequest;
import com.example.bankingappdemo.dto.responseDto.BankResponse;
import com.example.bankingappdemo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BankResponse> createAccount(@RequestBody @Valid UserRequest userRequest){
        if (userRequest == null) throw new RuntimeException("Fill in your details");

        BankResponse createdUser =userService.createAccount(userRequest);

         return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/getBalance")
    public ResponseEntity<BankResponse> balanceEnquiry(@RequestBody @Valid EnquiryRequest enquiryRequest){
        if (enquiryRequest == null) throw new RuntimeException("Enter accountNumber");

      BankResponse accountBalanceResponse = userService.balanceEnquiry(enquiryRequest);
      return ResponseEntity.status(HttpStatus.OK).body(accountBalanceResponse);

    }

    @PostMapping("/fundsTransfer")
    public ResponseEntity<BankResponse> fundsTransfer(@RequestBody FundsTransferRequest fundsTransferRequest){
        if (fundsTransferRequest == null) throw new RuntimeException("Enter accountNumber");

        BankResponse fundsTransferResponse =userService.fundsTransfer(fundsTransferRequest);

        return ResponseEntity.status(HttpStatus.OK).body(fundsTransferResponse);

    }

    @GetMapping("/getAccountName")
    public ResponseEntity<String> getAccountName(@RequestBody @Valid EnquiryRequest enquiryRequest){
        if (enquiryRequest == null) throw new RuntimeException("Enter accountNumber");

       String accountName =userService.getAccountName(enquiryRequest);
        return ResponseEntity.status(HttpStatus.OK).body(accountName);

    }

    @PostMapping("/credit")
    public ResponseEntity<BankResponse> creditAccount(@RequestBody CreditDebitRequest creditDebitRequest){
        if (creditDebitRequest == null) throw new RuntimeException("Enter accountNumber");

        BankResponse creditResponse =userService.creditAccount(creditDebitRequest);

        return ResponseEntity.status(HttpStatus.OK).body(creditResponse);

    }

    @PostMapping("/debit")
    public ResponseEntity<BankResponse> debitAccount(@RequestBody CreditDebitRequest creditDebitRequest){
        if (creditDebitRequest == null) throw new RuntimeException("Enter accountNumber");

        BankResponse debitResponse =userService.debitAccount(creditDebitRequest);

        return ResponseEntity.status(HttpStatus.OK).body(debitResponse);

    }

}
