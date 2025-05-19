package com.filmecinema.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sessao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idSessao;
    @NotEmpty(message = "Horário de início não pode estar vazio ou nulo")
    private String horarioInicio;
    @NotEmpty(message = "Horário de término não pode estar vazio ou nulo")
    private String horarioTermino;

    @Max(value = 32)
    @OneToMany(mappedBy = "sessao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assento> assentos = new ArrayList<>();

//    @ElementCollection
//    @Max(value = 32)
//    @CollectionTable(name = "filme_assentos_disponiveis", joinColumns = @JoinColumn(name = "filme_codigo"))
//    private Set<Integer> assentosDisponiveis = gerarAssentos();

//    @PostPersist
//    public void inicializarAssentos() {
//        if (assentos.isEmpty()) {
//            int numero = 1;
//            for (int fileira = 1; fileira <= 4; fileira++) {
//                for (int i = 1; i <= 8; i++) {
//                    Assento assento = new Assento();
//                    assento.setFileira(fileira);
//                    assento.setNumero(numero++);
//                    assento.setDisponivel(true);
//                    assento.setSessao(getIdSessao());
//                    assentos.add(assento);
//                }
//            }
//        }
//
    }


//    private Set<Integer> gerarAssentos() {
//        return IntStream.rangeClosed(1, 32).boxed().collect(Collectors.toCollection(TreeSet::new));
//    }

//    public void addAssentoOcupado(Assento assento) {
//        Objects.requireNonNull(assento, "Assento não pode ser nulo");
//        assentos.add(assento);
//    }

//    public void removeAssentoDisponivel(int numeroAssento) {
//        if (!assentosDisponiveis.remove(numeroAssento)) {
//            throw new IllegalStateException("Assento já ocupado ou inválido");
//        }
//    }

//    public void addAssentoDisponivel(int numeroAssento) {
//        assentosDisponiveis.