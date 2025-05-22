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
public class Assento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAssento;
    private int numeroAssento;
    private int fileira;
    private boolean disponivel = true;
    @ManyToOne
    @JoinColumn(name = "sessao_id_sessao")
    @JsonIgnore
    private Sessao sessao;

    @ManyToOne
    private Pessoa ocupante;
}
