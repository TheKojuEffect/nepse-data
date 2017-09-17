package com.kapilkoju.nepse.data;

import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static lombok.AccessLevel.PRIVATE;

@SpringBootApplication
@NoArgsConstructor(access = PRIVATE)
public class NepseDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(NepseDataApplication.class, args);
    }
}
