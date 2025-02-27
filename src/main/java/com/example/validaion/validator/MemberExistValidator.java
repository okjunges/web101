package com.example.validaion.validator;

import com.example.payload.status.ErrorStatus;
import com.example.repository.MemberRepository;
import com.example.validaion.annotation.ExistsMember;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MemberExistValidator implements ConstraintValidator<ExistsMember, Long> {

    private final MemberRepository memberRepository;

    @Override
    public void initialize(ExistsMember constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = memberRepository.existsById(id);

        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString())
                    .addConstraintViolation();
        }
        return isValid;
    }
}
