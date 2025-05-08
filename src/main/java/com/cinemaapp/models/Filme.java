package com.cinemaapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @NotEmpty
    @Column(name = "nome")
    private String nome;
    @NotEmpty
    @Column(name = "data_inicio")
    private String dataInicio;
    @NotEmpty
    @Column(name = "data_termino")
    private String dataTermino;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Sessao> sessaoList;


    public void addSessao(Sessao sessao) {
        if (Objects.isNull(sessaoList)) {
            sessaoList = new ArrayList<>();
        }
        sessaoList.add(sessao);
    }
}
