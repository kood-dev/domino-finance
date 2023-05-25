package com.domino.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DominoFinanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DominoFinanceApplication.class, args);
    }

}
