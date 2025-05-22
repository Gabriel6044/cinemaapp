package com.filmecinema.controllers;

import com.filmecinema.dtos.CompraDTO;
import com.filmecinema.models.Assento;
import com.filmecinema.services.AssentoService;
import com.filmecinema.services.SessaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/filmes/{codigo}/sessao/{idSessao}/assento/{numeroAssento}/comprar")
public class CompraAPI {

    private final AssentoService assentoService;
    private final SessaoService sessaoService;

    public CompraAPI(AssentoService assentoService, SessaoService sessaoService) {
        this.assentoService = assentoService;
        this.sessaoService = sessaoService;
    }

    @PostMapping
    public ResponseEntity<String> buyAssento(@PathVariable("idSessao") Long idSessao, @PathVariable("numeroAssento") int numeroAssento, @RequestBody @Valid CompraDTO compraDTO) {
        String nome = compraDTO.getNome();
        Assento assento = assentoService.findNumeroAssentoBySessao(idSessao, numeroAssento);
        sessaoService.buyAssento(idSessao, assento, nome);

        return ResponseEntity.ok("Assento " + numeroAssento + " comprado com sucesso para " + nome + ".");
    }

}
