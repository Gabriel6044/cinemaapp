package com.filmecinema.controllers;

import com.filmecinema.models.Assento;
import com.filmecinema.models.Sessao;
import com.filmecinema.services.AssentoService;
import com.filmecinema.services.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/filmes")
public class AssentoController {

    private final SessaoService sessaoService;
    private final AssentoService assentoService;

    @Autowired
    public AssentoController(SessaoService sessaoService, AssentoService assentoService) {
        this.sessaoService = sessaoService;
        this.assentoService = assentoService;
    }

    @PostMapping(value = "/{codigo}/sessao/{idSessao}/comprar_assento")
    public ResponseEntity<Sessao> newAssento(@PathVariable("idSessao") long idSessao, @RequestBody @Valid Assento assento, @PathVariable String codigo) {
        return ResponseEntity.ok(this.sessaoService.newAssento(idSessao, assento));
    }

    @DeleteMapping(value = "/{codigo}/sessao/{idSessao}/assento/{numeroAssento}/delete")
    public ResponseEntity<String> deleteAssento(@PathVariable("numeroAssento") long numeroAssento, @PathVariable("idSessao") long idSessao, @PathVariable String codigo) {
        Assento assento = assentoService.findByIdAssento(numeroAssento);
        Sessao sessao = sessaoService.findByidSessao(idSessao);
        assentoService.delete(assento, sessao);
        return ResponseEntity.ok("Assento deletado com sucesso");
    }

    @GetMapping(value = "/{codigo}/sessao/{idSessao}/assento/{numeroAssento}")
    public ResponseEntity getAssentoByCode(@PathVariable("numero") int numero, @PathVariable String codigo) {
        Assento assento = assentoService.findByNumero(numero);
        return ResponseEntity.ok(assento);
    }
}
