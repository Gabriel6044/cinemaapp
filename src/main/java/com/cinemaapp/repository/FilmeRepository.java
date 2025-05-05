package com.cinemaapp.repository;

import com.cinemaapp.models.Filme;
import org.springframework.data.repository.CrudRepository;

public interface FilmeRepository extends CrudRepository<Filme, String> {
    Filme findByCodigo(long codigo);
}
