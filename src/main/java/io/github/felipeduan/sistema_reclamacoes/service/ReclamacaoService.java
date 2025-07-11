package io.github.felipeduan.sistema_reclamacoes.service;

import io.github.felipeduan.sistema_reclamacoes.exception.ReclamacaoNaoEncontradaException;
import io.github.felipeduan.sistema_reclamacoes.dto.ReclamacaoDTO;
import io.github.felipeduan.sistema_reclamacoes.enums.StatusReclamacao;
import io.github.felipeduan.sistema_reclamacoes.exception.ReclamacaoStatusInvalido;
import io.github.felipeduan.sistema_reclamacoes.model.Reclamacao;
import io.github.felipeduan.sistema_reclamacoes.repository.ReclamacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReclamacaoService {

    private final ReclamacaoRepository reclamacaoRepository;

    public ReclamacaoService(ReclamacaoRepository reclamacaoRepository) {
        this.reclamacaoRepository = reclamacaoRepository;
    }

    public Reclamacao criarReclamacao(ReclamacaoDTO dto, String cpfUsuario) {
        Reclamacao reclamacao = new Reclamacao(dto.getTitulo(), dto.getDescricao(), cpfUsuario);
        return reclamacaoRepository.save(reclamacao);
    }

    public List<Reclamacao> listaReclamacoes(String cpfUsuario) {
        return reclamacaoRepository.findByCpfUsuario(cpfUsuario);
    }

    public Reclamacao buscarReclamacaoDoUsuario(UUID id, String cpfUsuario) {
        Reclamacao reclamacao = reclamacaoRepository.findById(id)
                .orElseThrow(() -> new ReclamacaoNaoEncontradaException("Reclamação não encontrada"));

        if (!reclamacao.getCpfUsuario().equals(cpfUsuario)) {
            throw new ReclamacaoNaoEncontradaException("Você não tem permissão para acessar esta reclamação");
        }

        return reclamacao;
    }

    public Reclamacao atualizarStatusParaRespondido(UUID id, String cpfUsuario) {
        Reclamacao reclamacao = buscarReclamacaoDoUsuario(id, cpfUsuario);
        reclamacao.setStatus(StatusReclamacao.RESPONDIDO);
        return reclamacaoRepository.save(reclamacao);
    }

    // ToDo: Criar método de Atualizar Reclamacao somente com status PENDENTE

    // ToDo: Criar método de Deletar Reclamacao somente com status PENDENTE
    public void deletarReclamacao(UUID id, String cpfUsuario) {
        Reclamacao reclamacao = buscarReclamacaoDoUsuario(id, cpfUsuario);

        if (!reclamacao.getStatus().equals(StatusReclamacao.PENDENTE)) {
            throw new ReclamacaoStatusInvalido("Não é possível realizar essa operação, pois o status não é pendente");
        }

        reclamacaoRepository.delete(reclamacao);
    }
}
