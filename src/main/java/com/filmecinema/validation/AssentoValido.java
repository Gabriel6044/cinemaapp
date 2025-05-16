package com.filmecinema.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AssentoValidoValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AssentoValido {
    String message() default "Número do assento não corresponde à fileira escolhida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
