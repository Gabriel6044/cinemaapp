package com.filmecinema.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.filmecinema.validation.ValidTimeRange;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ValidTimeRange
public class Sessao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSessao;
    @NotNull(message = "Horário de início não pode estar vazio ou nulo")
    private LocalTime horarioInicio;
    @NotNull(message = "Horário de término não pode estar vazio ou nulo")
    private LocalTime horarioTermino;

    @ManyToOne
    @JoinColumn(name = "filme_id_filme")
    @JsonIgnore
    private Filme filme;

    @Max(value = 32)
    @OneToMany(mappedBy = "sessao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assento> assentos = new ArrayList<>();
}