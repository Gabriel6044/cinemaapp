package com.filmecinema.controllers;

import com.filmecinema.models.Filme;
import com.filmecinema.models.Sessao;
import com.filmecinema.services.FilmeService;
import com.filmecinema.services.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/filmes")
public class SessaoController {

    private final FilmeService filmeService;
    private final SessaoService sessaoService;

    @Autowired
    public SessaoController(FilmeService filmeService, SessaoService sessaoService) {
        this.filmeService = filmeService;
        this.sessaoService = sessaoService;
    }


    @PostMapping(value = "/{codigo}/sessao")
    public ResponseEntity<Filme> saveNewSessao(@PathVariable("codigo") long codigo, @RequestBody @Valid Sessao sessao) {
        return ResponseEntity.ok(this.filmeService.newSessao(codigo, sessao));
    }

    @DeleteMapping(value = "/{codigo}/sessao/{idSessao}/delete")
    public ResponseEntity<String> deleteSessao(@PathVariable("idSessao") long idSessao) {
        Sessao sessao = sessaoService.findByidSessao(idSessao);
        sessaoService.delete(sessao);
        return ResponseEntity.ok("Sessão deletada com sucesso");
    }

    @GetMapping(value = "/{codigo}/sessao/{idSessao}")
    public ResponseEntity getSessaoByCode(@PathVariable("idSessao") long idSessao) {
        Sessao sessao = sessaoService.findByidSessao(idSessao);
        return ResponseEntity.ok(sessao);
    }

    @PatchMapping(value = "/{codigo}/sessao/{idSessao}")
    public ResponseEntity patchSessaoByCode(@PathVariable("idSessao") long idSessao, @RequestBody Map<String, Object> updates, BindingResult result) {
        Sessao sessao = sessaoService.findByidSessao(idSessao);
        updates.forEach((key, value) -> {
            switch (key) {
                case "horarioInicio":
                    sessao.setHorarioInicio((String) value);
                    break;
                case "horarioTermino":
                    sessao.setHorarioTermino((String) value);
                    break;
            }
        });
        Sessao sessaoAtualizada = sessaoService.save(sessao, result);
        return ResponseEntity.ok(sessaoAtualizada);
    }
}
