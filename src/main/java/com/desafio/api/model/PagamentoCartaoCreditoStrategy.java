package com.desafio.api.model;

import com.desafio.api.config.exception.ApiExceptionMessage;
import com.desafio.api.dto.PagamentoDTO;
import com.desafio.api.repository.PagamentoRepository;
import com.desafio.api.utils.ValidadorNumeroCartaoImpl;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class PagamentoCartaoCreditoStrategy implements PagamentoStrategy {

    @Autowired
    ValidadorNumeroCartaoImpl validadorNumeroCartao;

    public Pagamento realizarPagamento(PagamentoDTO pagamentoDTO, String cpf, String cnpj,
            @NotNull PagamentoRepository pagamentoRepository) throws ApiExceptionMessage {

        boolean validadorCartao = validadorNumeroCartao.validaNumeroCartao(pagamentoDTO.numeroCartao().orElse(""));

        if (!validadorCartao) {
            throw new ApiExceptionMessage(HttpStatus.BAD_REQUEST,
                    "Número de cartão inválido, por favor reveja os itens e tente novamente");
        }

        return pagamentoRepository.save(new Pagamento(pagamentoDTO, cpf, cnpj));
    }

}
