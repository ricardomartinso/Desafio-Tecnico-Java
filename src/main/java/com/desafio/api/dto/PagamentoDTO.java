package com.desafio.api.dto;

import java.util.Optional;

import org.hibernate.validator.constraints.CreditCardNumber;

import com.desafio.api.utils.MetodoPagamento;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PagamentoDTO(
                @NotBlank(message = "Cógido débito não pode ser vazio") @NotNull(message = "Cógido débito não pode ser nulo") Integer codigoDebito,

                @NotBlank(message = "CPF/CNPJ não pode ser vazio") @NotNull(message = "Cógido débito não pode ser nulo") String cpfCnpjPagador,

                @Valid @NotNull(message = "Método pagamento não pode ser nulo") MetodoPagamento metodoPagamento,

                Optional<@CreditCardNumber @NotNull String> numeroCartao,

                @NotBlank(message = "Valor pagamento não pode ser vazio") @NotNull(message = "Valor Pagamento não pode ser nulo") Double valorPagamento) {

}
