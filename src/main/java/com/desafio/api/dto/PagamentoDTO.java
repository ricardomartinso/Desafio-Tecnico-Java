package com.desafio.api.dto;

import java.util.Optional;

import org.hibernate.validator.constraints.CreditCardNumber;

import com.desafio.api.utils.MetodoPagamento;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;

public record PagamentoDTO(
        @NotNull int codigoDebito,

        @NotBlank String cpfCnpjPagador,

        @NotNull MetodoPagamento metodoPagamento,

        Optional<@CreditCardNumber String> numeroCartao,

        @NotNull double valorPagamento) {

}
