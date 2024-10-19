package com.plataformae.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PlataformaeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlataformaeApplication.class, args);
    }

}
