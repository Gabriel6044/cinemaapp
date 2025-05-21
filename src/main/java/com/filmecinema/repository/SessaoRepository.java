package com.filmecinema.repository;

import com.filmecinema.models.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    Optional<Sessao> findByIdSessao(Long idSessao);
}
