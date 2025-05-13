package com.cinemaapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Entity
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
    @CollectionTable(name = "filme_assentos_disponiveis", joinColumns = @JoinColumn(name = "filme_codigo"))
    private ArrayList<Integer> assentosDisponiveis = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52));

    @Max(value = 52)
    @OneToMany(cascade = CascadeType.ALL)
    private List<Assento> assentosOcupados;


    public void addAssentoOcupado(Assento assento) {
        if (Objects.isNull(assentosOcupados)) {
            assentosOcupados = new ArrayList<>();
        }
        assentosOcupados.add(assento);
    }

    public void removeAssentoDisponivel(int numeroAssento) {
        if (assentosDisponiveis.contains(numeroAssento)) {
            assentosDisponiveis.remove(Integer.valueOf(numeroAssento));
        } else {
            throw new IllegalArgumentException("Assento já ocupado");
        }
    }

    public void addAssentoDisponivel(int numeroAssento) {
        if (!assentosDisponiveis.contains(numeroAssento)) {
            assentosDisponiveis.add(numeroAssento);
        } else {
            throw new IllegalArgumentException("Assento já disponível");
        }
    }
    
//    public void removeAssentoLivre() {
//        assentosDisponiveis.remove();
//    }

//    public void removeAssentoOcupado(Assento assento) {
//        assentosOcupados.remove(assento);
//    }

//    public void addAssentoDisponivel(Assento assento) {
//        if (Objects.isNull(assentosDisponiveis)) {
//            List<Integer> numerosAdicionar = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51,52);
//            ArrayList<Integer> assentosDisponiveis = new ArrayList<>(numerosAdicionar);
////        }
//        assentosDisponiveis.add(assento);
//    }

//    public void removeAssentoDisponivel(Assento assento) {
//        assentosDisponiveis.remove(assento);
//    }

//    public Collection<Object> getAssentosDisponiveis() {
//        return List.of();
//    }
}
