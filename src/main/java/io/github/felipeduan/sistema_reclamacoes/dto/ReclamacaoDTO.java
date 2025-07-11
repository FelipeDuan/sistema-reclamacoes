package io.github.felipeduan.sistema_reclamacoes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReclamacaoDTO {

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;
}
