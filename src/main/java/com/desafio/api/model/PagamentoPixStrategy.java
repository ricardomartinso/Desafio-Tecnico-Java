package com.desafio.api.model;

import com.desafio.api.config.exception.ApiExceptionMessage;
import com.desafio.api.dto.PagamentoDTO;
import com.desafio.api.repository.PagamentoRepository;
import com.desafio.api.utils.ValidadorNumeroCartaoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PagamentoPixStrategy implements PagamentoStrategy {

    @Autowired
    private ValidadorNumeroCartaoImpl  validadorNumeroCartao;

    public Pagamento realizarPagamento(PagamentoDTO pagamentoDTO, String cpf, String cnpj,
            PagamentoRepository pagamentoRepository) throws ApiExceptionMessage {

        String cartaoCredito = pagamentoDTO.numeroCartao().orElse("");

        boolean validadorCartao = validadorNumeroCartao.validaNumeroCartao(cartaoCredito);

        if (validadorCartao) {
            throw new ApiExceptionMessage(HttpStatus.BAD_REQUEST,
                    "Número de cartão não aceito com esse método de pagamento");
        }
        // Integração com a Api do pix
        return pagamentoRepository.save(new Pagamento(pagamentoDTO, cpf, cnpj));
    }

}
