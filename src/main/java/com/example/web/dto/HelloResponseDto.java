package com.example.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HelloResponseDto { // Test용
    private final String name;
    private final int amount;
}