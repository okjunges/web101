package com.example.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

public class ReviewRequest {
    @Getter
    public static class AddReviewDto {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
        @NotNull
        private Double score;
    }
}