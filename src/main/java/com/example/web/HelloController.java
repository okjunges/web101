package com.example.web;

import com.example.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // Controller가 json을 반환하도록
public class HelloController { // test용
    @GetMapping("/hello") // HTTP의 GET 메소드를 요청받을 수 있는 API 생성
    public String hello() {
        return "Hello, Spring Boot!";
    }
    @GetMapping("/hello/dto")
    public HelloResponseDto hello(@RequestParam String name, @RequestParam int amount) {
        return new HelloResponseDto(name, amount);
    }
}