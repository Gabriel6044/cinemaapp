package com.cinemaapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Sessao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idSessao;
    @NotEmpty
    private String horarioInicio;
    @NotEmpty
    private String horarioTermino;

    @Max(value = 52)
    @ElementCollection
    @CollectionTable(name = "filme_assentos_ocupados", joinColumns = @JoinColumn(name = "filme_codigo"))
    private List<Integer> assentosOcupados;


}
