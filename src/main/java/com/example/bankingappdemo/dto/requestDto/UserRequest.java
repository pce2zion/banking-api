package com.example.bankingappdemo.dto.requestDto;

import com.example.bankingappdemo.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @NotEmpty(message = "user first name should not be null or empty")
    private String firstName;

    @NotEmpty(message = "user last name should not be null or empty")
    private String lastName;

    private String otherName;

    @NotEmpty(message = "gender should not be null or empty")
    private Gender gender;

    @NotEmpty(message = " address should not be null or empty")
    private String address;

    @NotEmpty(message = "state of origin should not be null or empty")
    private String stateOfOrigin;

    @NotEmpty(message = "email should not be null or empty")
    @Email(message = "email must be valid")
    private String email;

    @Size(min = 11, message = "phone number must be more than 10 characters")
    @NotEmpty(message = "not empty")
    private String phoneNumber;

    @Size(min = 11, message = "phone number must be more than 10 characters")
    private String alternativePhoneNumber;

    @NotEmpty(message = "status should not be null or empty")
    private String status;

    @Size(min = 8, message = "password must be more than 7 characters")
    @NotEmpty(message = "password must include uppercase, lowercase, non character and number")
    private String password;
}
