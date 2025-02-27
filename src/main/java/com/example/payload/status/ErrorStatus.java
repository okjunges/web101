package com.example.payload.status;

import com.example.payload.ReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseStatus {
    // Common Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_500", "서버 에러, 관리자에게 문의 바랍니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON_400","잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON_401","인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON_403", "금지된 요청입니다."),

    // Member Error
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER_4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER_4002", "닉네임은 필수 입니다."),

    // Article Error
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE_4001", "게시글이 없습니다."),

    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP_4001", "이것은 테스트"),

    // Category Error
    CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "CATEGORY_4001", "카테고리가 없습니다."),

    // Store Error
    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, "STORE_4001", "해당 가게가 없습니다."),

    // Mission Error
    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION_4001", "해당 미션이 없습니다."),
    ALREADY_MEMBER_MISSION(HttpStatus.BAD_REQUEST, "MISSION_4002", "해당 미션은 이미 도전중이거나 완료된 미셥입니다."),

    // Page Error
    NEGATIVE_NUMBER_PAGE(HttpStatus.BAD_REQUEST, "PAGE_4001", "페이지 값이 음수일 수 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .httpStatus(httpStatus)
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }
}