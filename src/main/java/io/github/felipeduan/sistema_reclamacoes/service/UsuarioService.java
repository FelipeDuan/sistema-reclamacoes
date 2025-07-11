package io.github.felipeduan.sistema_reclamacoes.service;

import io.github.felipeduan.sistema_reclamacoes.model.Usuario;
import io.github.felipeduan.sistema_reclamacoes.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarUsuario(String cpf, String senha) {
        String senhaCriptografada = passwordEncoder.encode(senha);
        Usuario usuario = new Usuario(cpf, senhaCriptografada);

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }
}
