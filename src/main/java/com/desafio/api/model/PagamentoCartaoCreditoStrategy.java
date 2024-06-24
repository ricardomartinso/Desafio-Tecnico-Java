package com.desafio.api.model;

import com.desafio.api.config.exception.ApiExceptionMessage;
import com.desafio.api.dto.PagamentoDTO;
import com.desafio.api.repository.PagamentoRepository;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

public class PagamentoCartaoCreditoStrategy implements PagamentoStrategy {


    public Pagamento realizarPagamento(PagamentoDTO pagamentoDTO, String cpf, String cnpj,
            @NotNull PagamentoRepository pagamentoRepository) throws ApiExceptionMessage {


        if (pagamentoDTO.numeroCartao().isPresent() && pagamentoDTO.numeroCartao().get().length() != 16) {
            throw new ApiExceptionMessage(HttpStatus.BAD_REQUEST,
                    "Número de cartão inválido, por favor reveja os itens e tente novamente");
        }

        return pagamentoRepository.save(new Pagamento(pagamentoDTO, cpf, cnpj));
    }

}
