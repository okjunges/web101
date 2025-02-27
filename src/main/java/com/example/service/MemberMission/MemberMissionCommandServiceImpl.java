package com.example.service.MemberMission;

import com.example.converter.MemberMissionConverter;
import com.example.domain.Member;
import com.example.domain.Mission;
import com.example.domain.mapping.MemberMission;
import com.example.repository.MemberMissionRepository;
import com.example.repository.MemberRepository;
import com.example.repository.MissionRepository;
import com.example.validaion.annotation.ExistsMember;
import com.example.web.dto.MemberMissionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService{

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MemberMission challenge(MemberMissionRequest.ChallengeDto dto, Long missionId) {
        Member member = memberRepository.findById(dto.getMemberId()).get();
        Mission mission = missionRepository.findById(missionId).get();
        MemberMission newMemberMission = MemberMissionConverter.toMemberMission(dto, member, mission);

        return memberMissionRepository.save(newMemberMission);
    }
}