package com.desafio.api.dto;

import com.desafio.api.utils.StatusPagamento;

public record ProcessamentoDTO(Long id, StatusPagamento statusPagamento) {
}
