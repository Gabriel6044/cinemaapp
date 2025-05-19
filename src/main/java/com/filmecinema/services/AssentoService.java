package com.filmecinema.services;

import com.filmecinema.models.Sessao;
import com.filmecinema.models.Assento;
import com.filmecinema.repository.AssentoRepository;
import com.filmecinema.repository.SessaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AssentoService {
    private final AssentoRepository assentoRepository;
    private final SessaoRepository sessaoRepository;

    public AssentoService(AssentoRepository assentoRepository, SessaoRepository sessaoRepository) {
        this.assentoRepository = assentoRepository;
        this.sessaoRepository = sessaoRepository;
    }

    @Transactional
    public Assento findByIdAssento(long idAssento) {
        return this.assentoRepository.findByIdAssento(idAssento).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assento não encontrado"));
    }

    @Transactional
    public Assento findByNumero(int numero) {
        return this.assentoRepository.findByNumero(numero).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assento não encontrado"));
    }

    @Transactional
    public Assento findByDisponivel(boolean disponivel) {
        return this.assentoRepository.findByDisponivel(disponivel).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assento não encontrado"));
    }

    @Transactional
    public Iterable<Assento> findAll() {
        return this.assentoRepository.findAll();
    }

    @Transactional
    public void delete(Assento assento, Sessao sessao) {
        assento.setDisponivel(true);
        sessaoRepository.save(sessao);
        this.assentoRepository.delete(assento);
    }
}
