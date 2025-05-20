package com.filmecinema.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Filme implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigo;

    @NotEmpty(message = "Nome não pode estar vazio ou nulo")
    @Column(name = "nome")
    private String nome;
    @NotEmpty(message = "Data de início não pode estar vazio ou nulo")
    @Column(name = "data_inicio")
    private String dataInicio;
    @NotEmpty(message = "Data de término não pode estar vazio ou nulo")
    @Column(name = "data_termino")
    private String dataTermino;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sessao> sessaoList = new ArrayList<>();

    public void addSessao(Sessao sessao) {
        sessaoList.add(sessao);
    }
}
