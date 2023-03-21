package com.desafio.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.desafio.api.config.exception.ApiExceptionMessage;
import com.desafio.api.model.Pagamento;
import com.desafio.api.repository.*;
import com.desafio.api.utils.StatusPagamento;

@Service
public class ProcessamentoService {

    @Autowired
    PagamentoRepository pagamentoRepository;

    public Pagamento atualizar(Long pagamentoId, StatusPagamento statusNovo) throws ApiExceptionMessage {
        Pagamento pagamento = pagamentoRepository.findById(pagamentoId).orElse(null);
        StatusPagamento statusAtual = pagamento.getStatus();

        if (statusAtual == StatusPagamento.sucesso) {
            throw new ApiExceptionMessage(HttpStatus.BAD_REQUEST, "Pagamento já aprovado não pode ser alterado!");

        } else if (statusAtual == StatusPagamento.pendente) {

            if (statusNovo == StatusPagamento.pendente) {
                throw new ApiExceptionMessage(HttpStatus.BAD_REQUEST,
                        "Pagamento pendente não pode ser alterado para pendente!");
            }

            pagamento.setStatus(statusNovo);

            return pagamentoRepository.save(pagamento);

        } else if (statusAtual == StatusPagamento.falha) {

            if (statusNovo != StatusPagamento.pendente) {
                throw new ApiExceptionMessage(HttpStatus.BAD_REQUEST,
                        "Não pode ser alterado para outro valor além de pendente");

            }
            pagamento.setStatus(statusNovo);
            pagamentoRepository.save(pagamento);
        }

        return pagamento;

    }
}
