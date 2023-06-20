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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "other_name")
    private String otherName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "state-of-origin")
    private String stateOfOrigin;

    @Column(name = "account-number")
    private String accountNumber;

    @Column(name = "account-balance")
    private BigDecimal accountBalance;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "alt-phoneNumber")
    private String alternativePhoneNumber;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "encrypted-password", nullable = false, unique = true)
    private String encryptedPassword;
}
