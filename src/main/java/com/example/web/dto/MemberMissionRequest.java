package com.example.web.dto;

import com.example.validaion.annotation.ExistsMember;
import lombok.Getter;

public class MemberMissionRequest {

    @Getter
    public static class ChallengeDto {
        @ExistsMember
        private Long memberId;
    }
}
