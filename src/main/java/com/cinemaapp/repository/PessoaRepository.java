package com.cinemaapp.repository;

import com.cinemaapp.models.Pessoa;
import com.cinemaapp.models.Filme;
import org.springframework.data.repository.CrudRepository;

public interface PessoaRepository extends CrudRepository<Pessoa, String> {
    Iterable<Pessoa> findByFilme(Filme filme);
}
