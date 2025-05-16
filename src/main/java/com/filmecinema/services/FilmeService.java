package com.filmecinema.services;

import com.filmecinema.models.Filme;
import com.filmecinema.models.Sessao;
import com.filmecinema.repository.FilmeRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Transactional
    public Filme findByCodigo(long codigo) {
        return this.filmeRepository.findByCodigo(codigo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado"));
    }

    @Transactional
    public Iterable<Filme> findAll() {
        return this.filmeRepository.findAll();
    }

    @Transactional
    public Filme save(Filme filme, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao salvar filme");
        }
        return this.filmeRepository.save(filme);
    }

    @Transactional
    public void delete(Filme filme) {
        for (Sessao sessao : filme.getSessaoList()) {
            if (sessao.getAssentosOcupados() != null && !sessao.getAssentosOcupados().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Este filme não pode ser deletado pois possui assentos reservados em uma ou mais sessões.");
            }
        }
        this.filmeRepository.delete(filme);
    }

    @Transactional
    public Filme newSessao(long codigo, Sessao sessao) {
        Filme filme = this.findByCodigo(codigo);
        filme.addSessao(sessao);
        return this.filmeRepository.save(filme);
    }
}
