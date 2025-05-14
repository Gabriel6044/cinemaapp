package com.cinemaapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@NoArgsConstructor
public class Assento implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Id
    private int idAssento;

//    @NotEmpty
    @Getter
    private int numeroAssento;

//    @NotEmpty
    @Getter
    private int fileira;


    public void setFileira(int fileira) {
        if (fileira < 1 || fileira > 4) {
            throw new IllegalArgumentException("Número da fileira deve estar entre 1 e 4");
        }
        this.fileira = fileira;
    }

    public void setNumeroAssento(int numeroAssento) {
        if (numeroAssento < 1 || numeroAssento > 32) {
            throw new IllegalArgumentException("Número do assento deve estar entre 1 e 32");
        }
        this.numeroAssento = numeroAssento;
    }

}
