package com.example.bankingappdemo.repository;

import com.example.bankingappdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
