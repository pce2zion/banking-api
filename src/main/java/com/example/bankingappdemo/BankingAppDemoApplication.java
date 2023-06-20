package com.example.bankingappdemo;

import com.example.bankingappdemo.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BankingAppDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(BankingAppDemoApplication.class, args);


    }
    @GetMapping("/")
    public String printHello(){
        return "world";
    }

}
