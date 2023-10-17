package com.ars.user.annotation;

import com.ars.user.annotation.validator.GenderValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
@Documented
public @interface Gender {
    String message() default "Gender must be MALE, FEMALE OR OTHER";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
