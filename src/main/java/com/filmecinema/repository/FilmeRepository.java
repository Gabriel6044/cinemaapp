package com.filmecinema.repository;

import com.filmecinema.models.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
    Optional<Filme> findByCodigo(Long codigo);
    List<Filme> findByEmCartaz(Boolean emCartaz);
}
