package com.cinemaapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Pessoa {

    @NotEmpty
    private String nomePessoa;
    @NotEmpty
    @Id
    private String rg;
    @ManyToOne
    private Filme filme;

    public Filme getFilme() {return filme;}
    public void setFilme(Filme filme) {this.filme = filme;}
    public String getNomePessoa() {return nomePessoa;}
    public void setNomePessoa(String nomePessoa) {this.nomePessoa = nomePessoa;}
    public String getRg() {return rg;}
    public void setRg(String rg) {this.rg = rg;}
}
