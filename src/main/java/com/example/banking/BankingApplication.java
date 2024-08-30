package com.example.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Clase principal para ejecutar la aplicación Spring Boot.
 */
@SpringBootApplication
@EnableWebMvc
public class BankingApplication {

    public static void main(String[] args) {
    	
        SpringApplication.run(BankingApplication.class, args);
    }
}
