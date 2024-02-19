package com.example.saratang.config;

import com.example.saratang.util.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncoderConfig {
    @Bean
    public Encoder encoder(){
        return new Encoder();
    }
}
