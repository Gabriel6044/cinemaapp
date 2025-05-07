package com.cinemaapp.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Filme implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigo;
    @NotEmpty
    @Column(name="nome")
    private String nome;
    @NotEmpty
    @Column(name="data_inicio")
    private String dataInicio;
    @NotEmpty
    @Column(name="data_termino")
    private String dataTermino;

    @Max(value = 52)
    @ElementCollection
    @CollectionTable(name="filme_assentos_ocupados", joinColumns=@JoinColumn(name="filme_codigo"))
    private List<Integer> assentosOcupados;
}
