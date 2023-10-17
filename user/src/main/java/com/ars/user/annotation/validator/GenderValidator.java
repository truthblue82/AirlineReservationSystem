package com.ars.user.annotation.validator;

import com.ars.user.annotation.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class GenderValidator implements ConstraintValidator<Gender, String> {
    private final List<String> genders = Arrays.asList("MALE", "FEMALE", "OTHER");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        return genders.contains(value);
    }
}
