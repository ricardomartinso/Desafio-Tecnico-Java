package com.desafio.api.services;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.desafio.api.Repository.*;
import com.desafio.api.config.exception.ApiErrorMessage;
import com.desafio.api.dto.*;
import com.desafio.api.model.Pagamento;
import com.desafio.api.utils.MetodoPagamento;
import com.desafio.api.*;
import java.util.*;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Object salvar(PagamentoDTO req) {

        MetodoPagamento metodoPagamento = req.metodoPagamento();
        String cpf = null;
        String cnpj = null;

        if (req.cpfCnpjPagador().length() == 11) {
            cpf = req.cpfCnpjPagador();
        } else if (req.cpfCnpjPagador().length() == 14) {
            cnpj = req.cpfCnpjPagador();
        } else {

            return new ApiErrorMessage(HttpStatus.BAD_REQUEST, "CPF/CNPJ inválido");
        }

        if ((metodoPagamento == MetodoPagamento.BOLETO || metodoPagamento == MetodoPagamento.PIX)) {
            if (req.numeroCartao().isEmpty()) {
                Pagamento pag = pagamentoRepository.save(new Pagamento(req, cpf, cnpj));
                return pag;

            }
            return null;

        } else {

            if (req.numeroCartao().isEmpty()) {
                return null;
            }
            Pagamento pag = pagamentoRepository.save(new Pagamento(req, cpf, cnpj));

            return pag;

        }

    }

    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }
}
