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

    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private StatusReclamacao status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
