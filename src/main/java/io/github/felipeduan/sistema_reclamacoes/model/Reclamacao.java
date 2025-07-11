package io.github.felipeduan.sistema_reclamacoes.model;

import io.github.felipeduan.sistema_reclamacoes.enums.StatusReclamacao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "reclamacoes")
public class Reclamacao {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusReclamacao status;

    @Column(nullable = false)
    private String cpfUsuario;

    public Reclamacao(String titulo, String descricao, String cpfUsuario) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.cpfUsuario = cpfUsuario;
        this.status = StatusReclamacao.PENDENTE;
        this.dataCriacao = LocalDateTime.now();
    }

}
