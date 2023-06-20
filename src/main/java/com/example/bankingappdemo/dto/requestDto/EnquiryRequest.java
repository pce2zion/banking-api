package com.example.bankingappdemo.dto.requestDto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnquiryRequest {

    @NotEmpty(message = "accountNumber should not be null or empty")
    private String accountNumber;
}
