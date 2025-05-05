package com.cinemaapp.controllers;

import com.cinemaapp.models.Pessoa;
import com.cinemaapp.models.Filme;
import com.cinemaapp.repository.PessoaRepository;
import com.cinemaapp.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class FilmeController {

    
    @Autowired
    private FilmeRepository fr;
    @Autowired
    private PessoaRepository pr;

    @RequestMapping(value = "/cadastrarFilme", method = RequestMethod.GET)
    public String form() {
        return "filme/formFilme";
    }

    @RequestMapping(value = "/cadastrarFilme", method = RequestMethod.POST)
    public String form(@Valid Filme filme, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/cadastrarFilme";
        }
        fr.save(filme);
        attributes.addFlashAttribute("mensagem", "Filme adicionado com sucesso!");
        return "redirect:/cadastrarFilme";
    }

    @RequestMapping("/filmes")
    public ModelAndView listaFilmes() {
        ModelAndView mv = new ModelAndView("index");
        Iterable<Filme> filmes = fr.findAll();
        mv.addObject("filmes", filmes);
        return mv;
    }

    @RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
    public ModelAndView detalhesFilme(@PathVariable("codigo") long codigo) {
        Filme filme = fr.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("filme/detalhesFilme");
        mv.addObject("filme", filme);
        Iterable<Pessoa> pessoas = pr.findByFilme(filme);
        mv.addObject("pessoas", pessoas);
        return mv;
    }



    @RequestMapping(value="/{codigo}", method=RequestMethod.POST)
    public String detalhesFilmePost(@PathVariable("codigo") long codigo, @Valid Pessoa pessoa, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/{codigo}";
        }
        Filme filme = fr.findByCodigo(codigo);
        pessoa.setFilme(filme);
        pr.save(pessoa);
        attributes.addFlashAttribute("mensagem", "Pessoa adicionada com sucesso!");
        return "redirect:/{codigo}";
    }
}
