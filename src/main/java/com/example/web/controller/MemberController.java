package com.example.web.controller;

import com.example.converter.MemberConverter;
import com.example.domain.Member;
import com.example.payload.CommonResponse;
import com.example.service.member.MemberCommandService;
import com.example.web.dto.MemberRequest;
import com.example.web.dto.MemberResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/")
    public CommonResponse<MemberResponse.JoinResultDto> join(@RequestBody @Valid MemberRequest.JoinDto dto) {
        Member member = memberCommandService.join(dto);
        return CommonResponse.onSuccess(MemberConverter.toJoinResultDto(member));
    }
}