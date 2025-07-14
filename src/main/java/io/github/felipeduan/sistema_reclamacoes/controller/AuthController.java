package io.github.felipeduan.sistema_reclamacoes.controller;

import io.github.felipeduan.sistema_reclamacoes.dto.LoginDTO;
import io.github.felipeduan.sistema_reclamacoes.dto.UsuarioDTO;
import io.github.felipeduan.sistema_reclamacoes.helpers.ResponseHelper;
import io.github.felipeduan.sistema_reclamacoes.model.Usuario;
import io.github.felipeduan.sistema_reclamacoes.security.JwtUtil;
import io.github.felipeduan.sistema_reclamacoes.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@Valid @RequestBody UsuarioDTO dto) {
        usuarioService.registrarUsuario(dto.getNome(), dto.getCpf(), dto.getSenha());
        return ResponseHelper.success("Usuário registrado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO dto) {
        Optional<Usuario> usuario = usuarioService.buscarPorCpf(dto.getCpf());

        if (usuario.isPresent() && passwordEncoder.matches(dto.getSenha(), usuario.get().getSenha())) {
            String token = JwtUtil.generateToken(usuario.get().getCpf());
            return ResponseEntity.ok(Map.of("token", token));
        }

        return ResponseEntity.status(401).body("Credenciais inválidas");
    }

}
