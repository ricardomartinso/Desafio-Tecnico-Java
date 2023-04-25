package com.desafio.api.model;

import com.desafio.api.config.exception.ApiExceptionMessage;
import com.desafio.api.dto.PagamentoDTO;
import com.desafio.api.repository.PagamentoRepository;
import org.springframework.stereotype.Service;

@Service
public interface PagamentoStrategy {
    Pagamento realizarPagamento(PagamentoDTO pagamentoDTO, String cpf, String cpnj,
            PagamentoRepository pagamentoRepository) throws ApiExceptionMessage;

}
