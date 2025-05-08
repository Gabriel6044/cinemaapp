package com.cinemaapp.repository;

import com.cinemaapp.models.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, String> {
    Optional<Sessao> findByIdSessao(long idSessao);
}
