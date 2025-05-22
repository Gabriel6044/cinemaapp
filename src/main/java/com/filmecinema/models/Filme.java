package com.filmecinema.models;

import com.filmecinema.validation.ValidDateRange;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ValidDateRange
@NoArgsConstructor
public class Filme implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @NotEmpty(message = "Nome não pode estar vazio ou nulo")
    @Column(name = "nome")
    private String nome;
    @NotNull(message = "Data de início não pode estar vazio ou nulo")
    @Column(name = "data_inicio")
    private LocalDate dataInicio;
    @NotNull(message = "Data de término não pode estar vazio ou nulo")
    @Column(name = "data_termino")
    private LocalDate dataTermino;
    private boolean estaEmCartaz(LocalDate hoje) {
        return !hoje.isBefore(dataInicio) && !hoje.isAfter(dataTermino);
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sessao> sessaoList = new ArrayList<>();

    public void addSessao(Sessao sessao) {
        sessaoList.add(sessao);
    }

}
