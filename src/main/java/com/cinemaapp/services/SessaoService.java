package com.cinemaapp.services;

import com.cinemaapp.models.Sessao;
import com.cinemaapp.repository.SessaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;

    public SessaoService(SessaoRepository sessaoRepository) {
        this.sessaoRepository = sessaoRepository;
    }

    public Sessao findByidSessao(long idSessao) {
        return this.sessaoRepository.findByIdSessao(idSessao).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessão não encontrada"));
    }

    public Iterable<Sessao> findAll() {
        return this.sessaoRepository.findAll();
    }

    public void sessaoSave(Sessao sessao) {
        this.sessaoRepository.save(sessao);
    }

    public void save(Sessao sessao, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao salvar sessão");
        }
        this.sessaoSave(sessao);
    }

    public void sessaoDelete(Sessao sessao) {
        this.sessaoRepository.delete(sessao);
    }

    public void delete(Sessao sessao) {
        if (!sessao.getAssentosOcupados().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esta sessão não pode ser deletada pois possui assentos reservados");
        } else {
            this.sessaoDelete(sessao);
        }
    }
}

