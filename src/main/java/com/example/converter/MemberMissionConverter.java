package com.example.converter;

import com.example.domain.Member;
import com.example.domain.Mission;
import com.example.domain.enums.MissionStatus;
import com.example.domain.mapping.MemberMission;
import com.example.web.dto.MemberMissionRequest;
import com.example.web.dto.MemberMissionResponse;

import java.time.LocalDateTime;

public class MemberMissionConverter {

    public static MemberMissionResponse.ChallengeResultDto toChallengeResultDto(MemberMission memberMission) {
        return MemberMissionResponse.ChallengeResultDto.builder()
                .status(memberMission.getStatus())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MemberMission toMemberMission(MemberMissionRequest.ChallengeDto dto, Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.PROGRESS)
                .build();
    }
}
