package io.github.felipeduan.sistema_reclamacoes;

public class ReclamacaoNaoEncontradaException extends RuntimeException {
    public ReclamacaoNaoEncontradaException(String message) {
        super(message);
    }
}
