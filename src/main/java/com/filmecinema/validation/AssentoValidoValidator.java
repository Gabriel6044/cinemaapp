package com.filmecinema.validation;

import com.filmecinema.models.Assento;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AssentoValidoValidator implements ConstraintValidator<AssentoValido, Assento> {

    @Override
    public boolean isValid(Assento assento, ConstraintValidatorContext context) {
        if (assento == null) return true; // evita erro de null pointer

        int fileira = assento.getFileira();
        int numero = assento.getNumeroAssento();

        int min = (fileira - 1) * 8 + 1;
        int max = fileira * 8;

        return numero >= min && numero <= max;
    }
}
