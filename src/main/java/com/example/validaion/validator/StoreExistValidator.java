package com.example.validaion.validator;

import com.example.payload.status.ErrorStatus;
import com.example.repository.StoreRepository;
import com.example.validaion.annotation.ExistsStore;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistsStore, Long> {


    private final StoreRepository storeRepository;

    @Override
    public void initialize(ExistsStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = storeRepository.existsById(id);

        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString())
                    .addConstraintViolation();
        }
        return isValid;
    }
}