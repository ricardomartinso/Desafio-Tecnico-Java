package com.desafio.api.dto;

import java.util.Optional;

import org.hibernate.validator.constraints.CreditCardNumber;

import com.desafio.api.utils.MetodoPagamento;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PagamentoDTO(
                @Min(value = 1, message = "Código debito precisa de 3 ou mais digitos") @NotNull(message = "Cógido débito não pode ser nulo") int codigoDebito,

                @NotBlank @NotNull(message = "Cógido débito não pode ser nulo") String cpfCnpjPagador,

                @Valid @NotNull(message = "Método pagamento não pode ser nulo") MetodoPagamento metodoPagamento,

                Optional<@CreditCardNumber String> numeroCartao,

                @DecimalMin(value = "0.1") @NotNull(message = "Valor Pagamento não pode ser nulo") double valorPagamento) {

}
