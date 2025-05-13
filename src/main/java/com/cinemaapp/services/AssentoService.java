package com.cinemaapp.services;

import com.cinemaapp.models.Assento;
import com.cinemaapp.repository.AssentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AssentoService {
    private final AssentoRepository assentoRepository;

    public AssentoService(AssentoRepository assentoRepository) {
        this.assentoRepository = assentoRepository;
    }

    public Assento findByNumeroAssento(long numeroAssento) {
        return this.assentoRepository.findByNumeroAssento(numeroAssento).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assento não encontrado"));
    }

    public Iterable<Assento> findAll() {
        return this.assentoRepository.findAll();
    }

    public void assentoSave(Assento assento) {
        this.assentoRepository.save(assento);
    }

    public void save(Assento assento, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao salvar assento");
        }
        this.assentoSave(assento);
    }

    private void assentoDelete(Assento assento) {
        this.assentoRepository.delete(assento);
    }

    public void delete(Assento assento) {
//        if (!sessao.getAssentosOcupados().isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esta sessão não pode ser deletada pois possui assentos reservados");
//        } else {
            this.assentoDelete(assento);
//        }
    }
}
