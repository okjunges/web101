package com.example.web.dto;

import com.example.validaion.annotation.ExistsCategories;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import java.util.List;

public class MemberRequest {

    @Getter
    public static class JoinDto {
        @NotBlank
        private String name;
        @NotBlank
        private String gender;
        @NotNull
        private Integer birthYear;
        @NotNull
        private Integer birthMonth;
        @NotNull
        private Integer birthDay;
        @Size(min = 2, max = 20)
        private String address;
        private String specAddress;
        @ExistsCategories
        List<Long> preferCategories;
    }
}