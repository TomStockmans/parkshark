package com.team4.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.team4")
public class ParksharkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParksharkApplication.class, args);
    }
}
