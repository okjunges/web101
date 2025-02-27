package com.example.web.controller;

import com.example.converter.MemberMissionConverter;
import com.example.domain.mapping.MemberMission;
import com.example.payload.CommonResponse;
import com.example.service.MemberMission.MemberMissionCommandService;
import com.example.validaion.annotation.ExistsMission;
import com.example.validaion.annotation.MissionChallenged;
import com.example.web.dto.MemberMissionRequest;
import com.example.web.dto.MemberMissionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    public final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/{missionId}/challenges")
    @Operation(summary = "미션을 도전하는 API",
            description = "가게의 미션을 도전 중인 미션에 추가하는 API이며 path variable로 미션 id가 필요합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "COMMON_200", description = "성공"),
            @ApiResponse(responseCode = "Mission_4001", description = "해당 미션을 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(implementation = CommonResponse.class))),
            @ApiResponse(responseCode = "Member_4001", description = "사용자를 찾을 수 없습니다.",
                    content = @Content(schema = @Schema(implementation = CommonResponse.class))),
    })
    @Parameters(value = {
            @Parameter(name = "missionId", description = "미션 ID(path variable)", required = true),
    })
    public CommonResponse<MemberMissionResponse.ChallengeResultDto> challenge(
            @ExistsMission @MissionChallenged @PathVariable(name = "missionId") Long missionId,
            @RequestBody @Valid MemberMissionRequest.ChallengeDto dto) {
        MemberMission memberMission = memberMissionCommandService.challenge(dto, missionId);
        return CommonResponse.onSuccess(MemberMissionConverter.toChallengeResultDto(memberMission));
    }
}