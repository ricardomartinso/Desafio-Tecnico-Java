package com.desafio.api.model;

import com.desafio.api.config.exception.ApiExceptionMessage;
import com.desafio.api.dto.PagamentoDTO;
import com.desafio.api.repository.PagamentoRepository;

import org.springframework.http.HttpStatus;

public class PagamentoBoletoStrategy implements PagamentoStrategy {



    public Pagamento realizarPagamento(PagamentoDTO pagamentoDTO, String cpf, String cnpj,
            PagamentoRepository pagamentoRepository) throws ApiExceptionMessage {


        if (pagamentoDTO.numeroCartao().isPresent()) {
            throw new ApiExceptionMessage(HttpStatus.BAD_REQUEST,
                    "Número de cartão não aceito com esse método de pagamento");
        }

        return pagamentoRepository.save(new Pagamento(pagamentoDTO, cpf, cnpj));

    }

}
