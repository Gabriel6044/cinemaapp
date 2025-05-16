package com.filmecinema.validation.impl;

import com.filmecinema.models.Assento;
import com.filmecinema.validation.AssentoValido;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AssentoValidoValidator implements ConstraintValidator<AssentoValido, Assento> {

    @Override
    public boolean isValid(Assento assento, ConstraintValidatorContext context) {
        if (assento == null) return true;

        int fileira = assento.getFileira();
        int numero = assento.getNumeroAssento();

        int min = (fileira - 1) * 8 + 1;
        int max = fileira * 8;

        return numero >= min && numero <= max;
    }
}
