package com.desafio.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.desafio.api.config.exception.*;
import com.desafio.api.dto.*;
import com.desafio.api.model.Pagamento;
import com.desafio.api.model.PagamentoBoletoStrategy;
import com.desafio.api.model.PagamentoCartaoCreditoStrategy;
import com.desafio.api.model.PagamentoCartaoDebitoStrategy;
import com.desafio.api.model.PagamentoPixStrategy;
import com.desafio.api.model.PagamentoStrategy;
import com.desafio.api.repository.*;
import com.desafio.api.utils.MetodoPagamento;
import com.desafio.api.utils.ValidadorCpfCnpj;

import java.util.*;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Pagamento salvar(PagamentoDTO req) throws ApiExceptionMessage {
        String cpf = new ValidadorCpfCnpj().verificaSeCpf(req.cpfCnpjPagador());
        String cnpj = new ValidadorCpfCnpj().verificaSeCnpj(req.cpfCnpjPagador());

        MetodoPagamento metodoPagamento = req.metodoPagamento();

        EnumMap<MetodoPagamento, PagamentoStrategy> mapStrategy = new EnumMap(MetodoPagamento.class);
        mapStrategy.put(MetodoPagamento.BOLETO, new PagamentoBoletoStrategy());
        mapStrategy.put(MetodoPagamento.CARTAO_CREDITO, new PagamentoCartaoCreditoStrategy());
        mapStrategy.put(MetodoPagamento.CARTAO_DEBITO, new PagamentoCartaoDebitoStrategy());
        mapStrategy.put(MetodoPagamento.PIX, new PagamentoPixStrategy());

        PagamentoStrategy pagamentoStrategy = mapStrategy.get(metodoPagamento);

        if (pagamentoStrategy == null) {
            throw new ApiExceptionMessage(HttpStatus.BAD_REQUEST, "Forma de pagamento inválida!");
        }

        if (cpf == null && cnpj == null) {
            throw new ApiExceptionMessage(HttpStatus.BAD_REQUEST, "CPF/CNPJ inválidos!");
        }

        return pagamentoStrategy.realizarPagamento(req, cpf, cnpj, pagamentoRepository);

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
