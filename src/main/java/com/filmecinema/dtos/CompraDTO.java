package com.filmecinema.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompraDTO {
    @NotBlank(message = "Nome não pode estar vazio ou nulo")
    private String nome;
}