package io.github.felipeduan.sistema_reclamacoes.controller;

import io.github.felipeduan.sistema_reclamacoes.dto.ReclamacaoDTO;
import io.github.felipeduan.sistema_reclamacoes.model.Reclamacao;
import io.github.felipeduan.sistema_reclamacoes.service.ReclamacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reclamacoes")
public class ReclamacaoController {

    // ToDo: depois lembrar de apagar essa rota aqui, é só para teste
    @GetMapping("/hello")
    public String helloReclamacao() {
        return "Esse é o controller de reclamacoes";
    }

    public final ReclamacaoService reclamacaoService;

    public ReclamacaoController(ReclamacaoService reclamacaoService) {
        this.reclamacaoService = reclamacaoService;
    }

    @PostMapping
    public ResponseEntity<Reclamacao> criar(@Valid @RequestBody ReclamacaoDTO dto, Authentication authentication) {
        String cpfUsuario = authentication.getName();
        Reclamacao reclamacao = reclamacaoService.criarReclamacao(dto, cpfUsuario);
        return ResponseEntity.ok(reclamacao);
    }

    @GetMapping
    public ResponseEntity<List<Reclamacao>> listar(Authentication authentication) {
        String cpfUsuario = authentication.getName();
        List<Reclamacao> reclamacoes = reclamacaoService.listaReclamacoes(cpfUsuario);
        return ResponseEntity.ok(reclamacoes);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Reclamacao> atualizarStatus(@PathVariable UUID id, Authentication authentication) {
        String cpfUsuario = authentication.getName();
        Reclamacao reclamacao = reclamacaoService.atualizarStatusParaRespondido(id, cpfUsuario);
        return ResponseEntity.ok(reclamacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reclamacao> atualizar(@PathVariable UUID id, @Valid @RequestBody ReclamacaoDTO dto,
                                                Authentication authentication) {
        String cpfUsuario = authentication.getName();
        Reclamacao reclamacaoAtualizada = reclamacaoService.atualizarReclamacao(id, cpfUsuario, dto);
        return ResponseEntity.ok(reclamacaoAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id, Authentication authentication) {
        String cpfUsuario = authentication.getName();
        reclamacaoService.deletarReclamacao(id, cpfUsuario);
        return ResponseEntity.noContent().build();
    }
}
