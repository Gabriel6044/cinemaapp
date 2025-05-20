package com.filmecinema.repository;

import com.filmecinema.models.Assento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssentoRepository extends JpaRepository<Assento, Long> {
    Optional<Assento> findByIdAssento(long IdAssento);
    Optional<Assento> findByNumeroAssento(int numeroAssento);
    Optional<Assento> findByDisponivel(boolean disponivel);
}
