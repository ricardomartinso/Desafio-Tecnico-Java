package com.desafio.api.utils;

public class VerificadorStatusPagamento {

    boolean validaStatus(StatusPagamento statusPagamento) {
        if (statusPagamento != StatusPagamento.falha || statusPagamento != StatusPagamento.sucesso
                || statusPagamento != StatusPagamento.pendente) {
            return false;
        }
        return true;
    }
}
