package com.filmecinema.services;

import com.filmecinema.models.Assento;
import com.filmecinema.models.Filme;
import com.filmecinema.models.Sessao;
import com.filmecinema.repository.FilmeRepository;
import com.filmecinema.repository.SessaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final FilmeRepository filmeRepository;

    public SessaoService(SessaoRepository sessaoRepository, FilmeRepository filmeRepository) {
        this.sessaoRepository = sessaoRepository;
        this.filmeRepository = filmeRepository;
    }

    @Transactional
    public Sessao findByidSessao(Long idSessao) {
        return this.sessaoRepository.findByIdSessao(idSessao).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Sessão não encontrada"));
    }

    @Transactional
    public Iterable<Sessao> findAll() {
        return this.sessaoRepository.findAll();
    }

    @Transactional
    public Sessao save(Sessao sessao, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao salvar sessão");
        }

        return this.sessaoRepository.save(sessao);
    }

    @Transactional
    public Filme findByCodigo(Long codigo) {
        return this.filmeRepository.findByCodigo(codigo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado"));
    }


    @Transactional
    public void delete(Sessao sessao, Long codigo) {
        Filme filme = this.findByCodigo(codigo);
        filme.getSessaoList().remove(sessao);
        this.sessaoRepository.delete(sessao);
    }

    @Transactional
    public Sessao buyAssento(Long idSessao, Assento assento) {
        Sessao sessao = this.findByidSessao(idSessao);
        if (assento.isDisponivel() == true) {
            assento.setDisponivel(false);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Assento já reservado");
        }

        return this.sessaoRepository.save(sessao);
    }
}





