package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 스프링 부트의 자동 설정
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}