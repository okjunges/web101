package com.example.validaion.validator;

import com.example.payload.status.ErrorStatus;
import com.example.repository.MemberMissionRepository;
import com.example.repository.MissionRepository;
import com.example.validaion.annotation.MissionChallenged;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionChallengedValidator implements ConstraintValidator<MissionChallenged, Long> {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    @Override
    public void initialize(MissionChallenged constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = memberMissionRepository.existsByMissionId(id);

        if (isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.ALREADY_MEMBER_MISSION.toString())
                    .addConstraintViolation();
        }

        return !isValid;
    }
}