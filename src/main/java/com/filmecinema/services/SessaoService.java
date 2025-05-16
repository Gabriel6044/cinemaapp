package com.filmecinema.services;

import com.filmecinema.models.Assento;
import com.filmecinema.models.Sessao;
import com.filmecinema.repository.SessaoRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Sessao findByidSessao(long idSessao) {
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
    public void delete(Sessao sessao) {
        this.sessaoRepository.delete(sessao);
    }

    @Transactional
    public Sessao newAssento(long idSessao, Assento assento) {
        Sessao sessao = this.findByidSessao(idSessao);
        sessao.addAssentoOcupado(assento);
        sessao.removeAssentoDisponivel(assento.getNumeroAssento());
        assento.setIdAssento(Integer.parseInt(String.valueOf(assento.getFileira()) + assento.getNumeroAssento()));

        return this.sessaoRepository.save(sessao);
    }

}



