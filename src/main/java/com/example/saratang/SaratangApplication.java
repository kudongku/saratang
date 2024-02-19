package com.example.saratang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SaratangApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaratangApplication.class, args);
    }

}
