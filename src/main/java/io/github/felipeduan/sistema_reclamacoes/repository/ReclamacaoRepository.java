package io.github.felipeduan.sistema_reclamacoes.repository;

import io.github.felipeduan.sistema_reclamacoes.model.Reclamacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReclamacaoRepository extends JpaRepository<Reclamacao, UUID> {

    List<Reclamacao> findByCpfUsuario(String cpfUsuario);
}
