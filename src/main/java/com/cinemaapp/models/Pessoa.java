package com.cinemaapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Pessoa {

    @NotEmpty
    private String nomePessoa;
    @NotEmpty
    @Id
    private String rg;

    public String getNomePessoa() {return nomePessoa;}
    public void setNomePessoa(String nomePessoa) {this.nomePessoa = nomePessoa;}
    public String getRg() {return rg;}
    public void setRg(String rg) {this.rg = rg;}
}
