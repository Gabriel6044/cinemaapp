package com.filmecinema.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
//@AssentoValido
public class Assento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAssento;
//    @Max(value = 32, message = "Número do assento deve ser menor ou igual a 32")
//    @Min(value = 1, message = "Número do assento deve ser maior ou igual a 1")
    private int numeroAssento;
//    @Max(value = 4, message = "Número da fileira deve ser menor ou igual a 4")
//    @Min(value = 1, message = "Número da fileira deve ser maior ou igual a 1")
    private int fileira;
    private boolean disponivel = true;
    @ManyToOne
    @JoinColumn(name = "sessao_id_sessao")
    @JsonIgnore
    private Sessao sessao;
}
