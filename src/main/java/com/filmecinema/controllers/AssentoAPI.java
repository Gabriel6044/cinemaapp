package com.filmecinema.controllers;

import com.filmecinema.models.Assento;
import com.filmecinema.models.Sessao;
import com.filmecinema.services.AssentoService;
import com.filmecinema.services.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/filmes/{codigo}/sessao/{idSessao}")
public class AssentoAPI {

    private final SessaoService sessaoService;
    private final AssentoService assentoService;

    @Autowired
    public AssentoAPI(SessaoService sessaoService, AssentoService assentoService) {
        this.sessaoService = sessaoService;
        this.assentoService = assentoService;
    }

    @PostMapping(value = "/assento/{numeroAssento}/comprar")
    public ResponseEntity<String> buyAssento(@PathVariable("idSessao") long idSessao, @PathVariable("numeroAssento") int numeroAssento) {
        Assento assento = assentoService.findByNumeroAssento(numeroAssento);
        sessaoService.buyAssento(idSessao, assento);
        return ResponseEntity.ok("Assento " + numeroAssento + " comprado com sucesso.");
    }

    @DeleteMapping(value = "/assento/{numeroAssento}/delete")
    public ResponseEntity<String> deleteAssento(@PathVariable("numeroAssento") int numeroAssento, @PathVariable("idSessao") long idSessao, @PathVariable String codigo) {
        Assento assento = assentoService.findByNumeroAssento(numeroAssento);
        Sessao sessao = sessaoService.findByidSessao(idSessao);
        assentoService.delete(assento, sessao);
        return ResponseEntity.ok("Assento deletado com sucesso");
    }

    @GetMapping(value = "/assento/{numeroAssento}")
    public ResponseEntity getAssentoByCode(@PathVariable("numeroAssento") int numeroAssento, @PathVariable String codigo) {
        Assento assento = assentoService.findByNumeroAssento(numeroAssento);
        return ResponseEntity.ok(assento);
    }
}
