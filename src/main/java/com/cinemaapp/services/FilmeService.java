package com.cinemaapp.services;

import com.cinemaapp.models.Filme;
import com.cinemaapp.repository.FilmeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public Filme findByCodigo(long codigo) {
        return this.filmeRepository.findByCodigo(codigo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filme não encontrado"));
    }

    public Iterable<Filme> findAll() {
        return this.filmeRepository.findAll();
    }

    public Filme save(Filme filme) {
        return this.filmeRepository.save(filme);
    }

    public Filme save(Filme filme, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao salvar filme");
        }
        return this.save(filme);
    }

    public Filme delete(Filme filme) {
        this.filmeRepository.delete(filme);
        return filme;
    }

    public Filme delete(Filme filme, BindingResult result) {
//        if (!filme.getAssentosOcupados().isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este filme não pode ser deletado pois possui assentos reservados");
//        } else {
            return this.delete(filme);
//        }
    }
}
