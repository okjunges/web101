package com.example.converter;

import com.example.domain.Member;
import com.example.domain.enums.Gender;
import com.example.web.dto.MemberRequest;
import com.example.web.dto.MemberResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

    public static MemberResponse.JoinResultDto toJoinResultDto(Member member) {
        return MemberResponse.JoinResultDto.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequest.JoinDto dto) {
        Gender gender;

        try {
            gender = Gender.valueOf(dto.getGender());
        } catch (IllegalArgumentException e) {
            gender = Gender.NO_ANSWER;
        }

        return Member.builder()
                .name(dto.getName())
                .age(LocalDateTime.now().getYear() - dto.getBirthYear())
                .address(dto.getAddress())
                .specAddress(dto.getSpecAddress())
                .gender(gender)
                .memberPrefers(new ArrayList<>())
                .build();
    }
}