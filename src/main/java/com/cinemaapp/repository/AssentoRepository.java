package com.cinemaapp.repository;

import com.cinemaapp.models.Assento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssentoRepository extends JpaRepository<Assento, String> {
    Optional<Assento> findByIdAssento(long IdAssento);
    Optional<Assento> findByNumeroAssento(long numeroAssento);
}
