package com.filmecinema.repository;

import com.filmecinema.models.Assento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssentoRepository extends JpaRepository<Assento, Long> {
    Optional<Assento> findByIdAssento(Long IdAssento);

    Optional<Assento> findByNumeroAssento(int numeroAssento);

    Optional<Assento> findAllBySessao_IdSessaoAndNumeroAssento(Long idSessao, int numeroAssento);

    List<Assento> findBySessao_IdSessaoAndDisponivel(Long idSessao, boolean disponivel);
}
