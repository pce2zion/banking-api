package com.example.bankingappdemo.entity;

import com.example.bankingappdemo.enums.Gender;
import com.example.bankingappdemo.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User extends BaseEntity{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String otherName;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String address;
    private String stateOfOrigin;
    private String accountNumber;
    private BigDecimal accountBalance;

    @Column(name = "email", unique = true, nullable = false)
    private String email;
    private String phoneNumber;
    private String alternativePhoneNumber;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "encrypted-password", nullable = false, unique = true)
    private String encryptedPassword;
}
