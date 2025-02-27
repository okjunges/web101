package com.example.service.MemberMission;

import com.example.domain.mapping.MemberMission;
import com.example.web.dto.MemberMissionRequest;

public interface MemberMissionCommandService {
    MemberMission challenge(MemberMissionRequest.ChallengeDto dto, Long missionId);
}