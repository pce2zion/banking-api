package com.example.bankingappdemo.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI bankAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("bank API ")
                        .description("DecaPay is a personal finance tracker that helps you keep track of your budget.")
                        .version("1.11")

                );


    }
}
