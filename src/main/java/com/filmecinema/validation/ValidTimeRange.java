package com.filmecinema.validation;

import com.filmecinema.validation.impl.TimeRangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TimeRangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTimeRange {
    String message() default "A hora de término não pode ser anterior ou igual à hora de início";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}