package com.filmecinema.validation.impl;

import com.filmecinema.models.Sessao;
import com.filmecinema.validation.ValidTimeRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimeRangeValidator implements ConstraintValidator<ValidTimeRange, Sessao> {

    @Override
    public boolean isValid(Sessao sessao, ConstraintValidatorContext context) {
        if (sessao.getHorarioInicio() == null || sessao.getHorarioTermino() == null) {
            return true;
        }

        return !sessao.getHorarioTermino().isBefore(sessao.getHorarioInicio());
    }
}