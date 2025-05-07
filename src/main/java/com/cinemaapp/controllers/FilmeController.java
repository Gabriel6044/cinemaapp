package com.cinemaapp.controllers;

import com.cinemaapp.models.Filme;
import com.cinemaapp.repository.FilmeRepository;
import com.cinemaapp.services.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService filmeService;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @PostMapping
    public ResponseEntity<Filme> saveNew(@RequestBody @Valid Filme filme, BindingResult result) {
        return ResponseEntity.ok(this.filmeService.save(filme, result));
    }

    @DeleteMapping(value = "/delete/{codigo}")
    public ResponseEntity<String> delete(@PathVariable("codigo") long codigo) {
        Filme filme = filmeService.findByCodigo(codigo);
       if(!filme.getAssentosOcupados().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este filme não pode ser deletado pois possui assentos reservados");
        }
       else {
            filmeService.delete(filme);
            return ResponseEntity.ok("Filme deletado com sucesso");
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<Filme>> listaFilmes() {
        Iterable<Filme> filmes = filmeService.findAll();
        return ResponseEntity.ok(filmes);
    }


    @PostMapping(value = "/{codigo}")
    public ResponseEntity detalhesFilmePost(@PathVariable("codigo") long codigo, BindingResult result, RedirectAttributes attributes) {
        Filme filme = filmeService.findByCodigo(codigo);
        return ResponseEntity.ok(filme);
    }


    @GetMapping(value = "/{codigo}")
    public ResponseEntity getFilmeByCode(@PathVariable("codigo") long codigo) {
        Filme filme = filmeService.findByCodigo(codigo);
        return ResponseEntity.ok(filme);
    }
}
