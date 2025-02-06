package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication // 스프링 부트의 자동 설정
@EnableJpaAuditing
public class Application {
    public static void main(String[] args) {
        // project initialization
        SpringApplication.run(Application.class, args);
    }
}