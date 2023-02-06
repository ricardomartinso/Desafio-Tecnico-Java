package com.desafio.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.desafio.api.config.exception.*;
import com.desafio.api.dto.*;
import com.desafio.api.model.Pagamento;
import com.desafio.api.repository.*;
import com.desafio.api.utils.MetodoPagamento;

import java.util.*;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Pagamento salvar(PagamentoDTO req) throws ApiExceptionMessage {

        MetodoPagamento metodoPagamento = req.metodoPagamento();
        String cpf = null;
        String cnpj = null;

        if (req.cpfCnpjPagador().length() == 11) {
            cpf = req.cpfCnpjPagador();
        } else if (req.cpfCnpjPagador().length() == 14) {
            cnpj = req.cpfCnpjPagador();
        } else {
            throw new ApiExceptionMessage(HttpStatus.BAD_REQUEST, "CPF/CNPJ inválidos!");
        }

        if ((metodoPagamento == MetodoPagamento.BOLETO || metodoPagamento == MetodoPagamento.PIX)) {
            if (req.numeroCartao().isEmpty()) {
                return pagamentoRepository.save(new Pagamento(req, cpf, cnpj));

            }

            throw new ApiExceptionMessage(HttpStatus.BAD_REQUEST,
                    "Número de cartão inválido com esse meio de pagamento");

        } else {
            if (req.numeroCartao().isEmpty()) {
                throw new ApiExceptionMessage(HttpStatus.BAD_REQUEST, "Número de cartão faltando");

            }
            return pagamentoRepository.save(new Pagamento(req, cpf, cnpj));

        }

    }

    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }

    public List<Pagamento> listarPorCodigoDebito(int codigoDebito) {
        return pagamentoRepository.findByCodigoDebito(codigoDebito);
    }

    public List<Pagamento> listarPorCpfCnpj(String cpfCnpj) {

        if (cpfCnpj.length() == 11) {
            return pagamentoRepository.findByCpf(cpfCnpj);
        } else if (cpfCnpj.length() == 14) {
            return pagamentoRepository.findByCnpj(cpfCnpj);
        }

        return null;

    }

    public List<Pagamento> listarPorStatus(String status) {
        return pagamentoRepository.findByStatus(status);
    }

    public Pagamento deletarPagamentoPorId(Long id) throws ApiExceptionMessage {

        Pagamento pagamento = pagamentoRepository.findById(id).orElse(null);

        if (pagamento == null) {
            throw new ApiExceptionMessage(HttpStatus.BAD_REQUEST, "ID do pagamento não encontrado");
        }

        if (!pagamento.getStatus().equals("pendente")) {
            throw new ApiExceptionMessage(HttpStatus.BAD_REQUEST, "Apenas pagamentos pendentes podem ser deletados.");
        }

        pagamentoRepository.deleteById(id);

        return pagamento;

    }
}
