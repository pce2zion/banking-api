package com.example.bankingappdemo.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FundsTransferRequest {
    private String recipientAccountNumber;
    private BigDecimal amount;
    private String sourceAccountNumber;
}
