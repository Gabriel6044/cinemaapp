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
@RequestMapping("/filmes/{codigo}/sessao")
public class SessaoAPI {

    private final FilmeService filmeService;
    private final SessaoService sessaoService;

    @Autowired
    public SessaoAPI(FilmeService filmeService, SessaoService sessaoService) {
        this.filmeService = filmeService;
        this.sessaoService = sessaoService;
    }


    @PostMapping
    public ResponseEntity<Filme> newSessao(@PathVariable("codigo") Long codigo, @RequestBody @Valid Sessao sessao) {
        return ResponseEntity.ok(this.filmeService.newSessao(codigo, sessao));
    }

    @DeleteMapping(value = "/{idSessao}/delete")
    public ResponseEntity<String> deleteSessao(@PathVariable("idSessao") Long idSessao, @PathVariable Long codigo) {
        Sessao sessao = sessaoService.findByidSessao(idSessao);
        sessaoService.delete(sessao, codigo);
        return ResponseEntity.ok("Sessão deletada com sucesso");
    }

    @GetMapping(value = "/{idSessao}")
    public ResponseEntity getSessaoByCode(@PathVariable("idSessao") Long idSessao, @PathVariable Long codigo) {
        Sessao sessao = sessaoService.findByidSessao(idSessao);
        return ResponseEntity.ok(sessao);
    }

    @PatchMapping(value = "/{idSessao}")
    public ResponseEntity patchSessaoByCode(@PathVariable("idSessao") Long idSessao, @RequestBody Map<String, Object> updates, BindingResult result, @PathVariable String codigo) {
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
