package com.filmecinema.validation.impl;

import com.filmecinema.models.Filme;
import com.filmecinema.validation.ValidDateRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, Filme> {

    @Override
    public boolean isValid(Filme filme, ConstraintValidatorContext context) {
        if (filme == null) {
            return true;
        }
        if (filme.getDataInicio() == null || filme.getDataTermino() == null) {
            return true;
        }

        return !filme.getDataTermino().isBefore(filme.getDataInicio());
    }
}