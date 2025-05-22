package com.filmecinema.services;

import com.filmecinema.models.Assento;
import com.filmecinema.models.Filme;
import com.filmecinema.models.Sessao;
import com.filmecinema.repository.FilmeRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Transactional
    public Filme findByCodigo(Long codigo) {
        return this.filmeRepository.findByCodigo(codigo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado"));
    }

    @Transactional
    public Iterable<Filme> findAll() {
        return this.filmeRepository.findAll();
    }

    @Transactional
    public Filme save(Filme filme, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao salvar filme, verifique os campos!");
        }
        return this.filmeRepository.save(filme);
    }

    @Transactional
    public void delete(Filme filme) {
        for (Sessao sessao : filme.getSessaoList()) {
            if (sessao.getAssentos() != null && !sessao.getAssentos().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Este filme não pode ser deletado pois possui assentos reservados em uma ou mais sessões.");
            }
        }
        this.filmeRepository.delete(filme);
    }

    @Transactional
    public Filme newSessao(Long codigo, Sessao sessao) {
        Filme filme = this.findByCodigo(codigo);
        List<Assento> assentosList = new ArrayList<>();
        int numero = 1;

        for (int fileira = 1; fileira <= 4; fileira++) {
            for (int i = 1; i <= 8; i++) {
                Assento assento = new Assento();
                assento.setFileira(fileira);
                assento.setNumeroAssento(numero++);
                assento.setSessao(sessao);
                assentosList.add(assento);
            }
        }

        sessao.setAssentos(assentosList);
        filme.addSessao(sessao);
        return this.filmeRepository.save(filme);
    }

}
