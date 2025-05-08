package com.cinemaapp.controllers;

import com.cinemaapp.models.Filme;
import com.cinemaapp.models.Sessao;
import com.cinemaapp.services.FilmeService;
import com.cinemaapp.services.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService filmeService;
    private final SessaoService sessaoService;

    @Autowired
    public FilmeController(FilmeService filmeService, SessaoService sessaoService) {
        this.filmeService = filmeService;
        this.sessaoService = sessaoService;
    }

    @PostMapping
    public ResponseEntity<Filme> saveNew(@RequestBody @Valid Filme filme, BindingResult result) {
        return ResponseEntity.ok(this.filmeService.save(filme, result));
    }

    @DeleteMapping(value = "/{codigo}/delete")
    public ResponseEntity<Void> delete(@PathVariable("codigo") long codigo) {
        Filme filme = filmeService.findByCodigo(codigo);
//        if (!filme.getAssentosOcupados().isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este filme não pode ser deletado pois possui assentos reservados");
//        } else {
        filmeService.delete(filme);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Filme>> listaFilmes() {
        Iterable<Filme> filmes = filmeService.findAll();
        return ResponseEntity.ok(filmes);
    }


    @PostMapping(value = "/{codigo}")
    public ResponseEntity detalhesFilmePost(@PathVariable("codigo") long codigo, @Valid Sessao sessao, BindingResult result) {
        Filme filme = filmeService.findByCodigo(codigo);
        sessaoService.save(sessao, result);
        return ResponseEntity.ok(filme);
    }

    @PostMapping(value = "/{codigo}/sessao")
    public ResponseEntity<Filme> saveNewSessao(@PathVariable("codigo") long codigo, @RequestBody @Valid Sessao sessao) {
        return ResponseEntity.ok(this.filmeService.newSessao(codigo, sessao));
    }

    @GetMapping(value = "/{codigo}")
    public ResponseEntity getFilmeByCode(@PathVariable("codigo") long codigo) {
        Filme filme = filmeService.findByCodigo(codigo);
        return ResponseEntity.ok(filme);
    }

    @GetMapping(value = "/{codigo}/sessao/{idSessao}")
    public ResponseEntity getSessaoByCode(@PathVariable("idSessao") long idSessao) {
        Sessao sessao = sessaoService.findByidSessao(idSessao);
        return ResponseEntity.ok(sessao);
    }

//    @GetMapping(value = "/sessao")
//    public ResponseEntity<Iterable<Sessao>> listaSessoes() {
//        Iterable<Sessao> sessoes = sessaoService.findAll();
//        return ResponseEntity.ok(sessoes);
//    }

    @DeleteMapping(value = "/{codigo}/sessao/{idSessao}/delete")
    public ResponseEntity<String> deleteSessao(@PathVariable("idSessao") long idSessao) {
        Sessao sessao = sessaoService.findByidSessao(idSessao);
        sessaoService.delete(sessao);
        return ResponseEntity.ok("Sessão deletada com sucesso");

    }
}
