package com.filmecinema.validation;

import com.filmecinema.validation.impl.DateRangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateRangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateRange {
    String message() default "A data de término não pode ser anterior à data de início";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}