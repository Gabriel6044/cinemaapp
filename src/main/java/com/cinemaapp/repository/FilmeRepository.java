package com.cinemaapp.repository;

import com.cinemaapp.models.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, String> {
    Optional<Filme> findByCodigo(long codigo);
}
