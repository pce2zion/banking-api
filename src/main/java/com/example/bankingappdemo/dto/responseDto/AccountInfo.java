package com.example.bankingappdemo.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AccountInfo {
    private String accountName;
    private String accountBalance;
    private String accountNumber;
}