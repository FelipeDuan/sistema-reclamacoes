package io.github.felipeduan.sistema_reclamacoes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reclamacoes")
public class ReclamacaoController {

    @GetMapping
    public String helloReclamacao() {
        return "Esse é o controller de reclamacoes";
    }
}
