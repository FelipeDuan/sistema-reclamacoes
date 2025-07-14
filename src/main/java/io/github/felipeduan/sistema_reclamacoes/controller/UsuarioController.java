package io.github.felipeduan.sistema_reclamacoes.controller;

import io.github.felipeduan.sistema_reclamacoes.model.Usuario;
import io.github.felipeduan.sistema_reclamacoes.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> minhasInfos(Authentication authentication) {
        String cpfUsuario = authentication.getName();
        Map<String, String> nomeUsuario = usuarioService.meuNome(cpfUsuario);
        return ResponseEntity.ok(nomeUsuario);
    }
}
