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
    public Assento findByNumeroAssento(long numeroAssento) {
        return this.assentoRepository.findByNumeroAssento(numeroAssento).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assento não encontrado"));
    }

    @Transactional
    public Iterable<Assento> findAll() {
        return this.assentoRepository.findAll();
    }

    @Transactional
    public void delete(Assento assento, Sessao sessao) {
        sessao.getAssentosOcupados().removeIf(a -> a.getIdAssento() == assento.getIdAssento());
        sessao.addAssentoDisponivel(assento.getNumeroAssento());
        sessaoRepository.save(sessao);
        this.assentoRepository.delete(assento);
    }
}
