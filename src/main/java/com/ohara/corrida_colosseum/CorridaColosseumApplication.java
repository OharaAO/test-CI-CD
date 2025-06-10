package com.ohara.corrida_colosseum;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.TimeZone;

@SpringBootApplication
public class CorridaColosseumApplication  extends SpringBootServletInitializer {




        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(CorridaColosseumApplication.class);
        }

    public static void main(String[] args) {
        SpringApplication.run(CorridaColosseumApplication.class, args);

        System.out.println("Hello World!");
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}