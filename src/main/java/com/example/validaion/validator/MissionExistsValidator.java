package com.example.validaion.validator;

import com.example.payload.status.ErrorStatus;
import com.example.repository.MissionRepository;
import com.example.validaion.annotation.ExistsMission;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionExistsValidator implements ConstraintValidator<ExistsMission, Long> {

    private final MissionRepository missionRepository;

    @Override
    public void initialize(ExistsMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = missionRepository.existsById(id);

        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_NOT_FOUND.toString())
                    .addConstraintViolation();
        }
        return isValid;
    }
}