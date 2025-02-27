package com.example.service.member;

import com.example.domain.Member;
import com.example.web.dto.MemberRequest;

public interface MemberCommandService {
    Member join(MemberRequest.JoinDto dto);
}