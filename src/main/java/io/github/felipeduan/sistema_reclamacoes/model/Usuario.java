package io.github.felipeduan.sistema_reclamacoes.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(unique = true)
    private String cpf;

    @Column(nullable = false)
    private String senha;

    public Usuario(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }
}
