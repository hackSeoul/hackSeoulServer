package com.hackseoul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HackSeoul {

    public static void main(String[] args) {
        SpringApplication.run(HackSeoul.class, args);
    }

}
