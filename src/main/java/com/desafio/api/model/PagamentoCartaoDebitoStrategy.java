package com.desafio.api.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.desafio.api.config.exception.ApiExceptionMessage;
import com.desafio.api.dto.PagamentoDTO;
import com.desafio.api.repository.PagamentoRepository;

public class PagamentoCartaoDebitoStrategy implements PagamentoStrategy {


    public Pagamento realizarPagamento(PagamentoDTO pagamentoDTO, String cpf, String cnpj,
            PagamentoRepository pagamentoRepository) throws ApiExceptionMessage {

        if (pagamentoDTO.numeroCartao().isPresent() && pagamentoDTO.numeroCartao().get().length() != 16) {
            throw new ApiExceptionMessage(HttpStatus.BAD_REQUEST,
                    "Número de cartão inválido, por favor reveja os itens e tente novamente");
        }

        Pagamento pagamento = new Pagamento(pagamentoDTO, cpf, cnpj);

        return pagamentoRepository.save(pagamento);
    }

}
