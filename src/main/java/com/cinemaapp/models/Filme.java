package com.cinemaapp.models;
import jakarta.persistence.*;
//import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
public class Filme implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigo;
//    @NotEmpty
    private String nome;
//    @NotEmpty
    private String dataInicio;
//    @NotEmpty
    private String dataTermino;
    @OneToMany
    private List<Pessoa> pessoas;



    public long getCodigo() {return codigo;}
    public void setCodigo(long codigo) {this.codigo = codigo;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getDataInicio() {return dataInicio;}
    public void setDataInicio(String dataInicio) {this.dataInicio = dataInicio;}
    public String getDataTermino() {return dataTermino;}
    public void setDataTermino(String dataTermino) {this.dataTermino = dataTermino;}
    public List<Pessoa> getPessoas() {return pessoas;}
    public void setPessoas(List<Pessoa> pessoas) {this.pessoas = pessoas;}

}
