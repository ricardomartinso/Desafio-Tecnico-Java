package com.desafio.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.desafio.api.config.exception.ControllersException;
import com.desafio.api.model.Pagamento;
import com.desafio.api.repository.*;

@Service
public class ProcessamentoService {

    @Autowired
    PagamentoRepository pagamentoRepository;

    public Pagamento atualizar(Long pagamentoId, String status) throws ControllersException {
        Pagamento pagamento = pagamentoRepository.findById(pagamentoId).orElse(null);

        boolean statusDiferentePendente = !status.equals("pendente");
        boolean statusDiferenteSucesso = !status.equals("sucesso");
        boolean statusDiferenteFalha = !status.equals("falha");

        if (pagamento == null) {
            return null;
        }

        String pagamentoStatus = pagamento.getStatus();

        if (statusDiferenteSucesso && statusDiferenteFalha && statusDiferentePendente) {
            throw new ControllersException(HttpStatus.BAD_REQUEST, "processamento não aceita este tipo de valor");

        }

        if (pagamentoStatus.equals("pendente") && statusDiferentePendente) {
            pagamento.setStatus(status);

            pagamentoRepository.save(pagamento);

        } else if (pagamentoStatus.equals("sucesso")) {
            throw new ControllersException(HttpStatus.BAD_REQUEST, "Pagamento já aprovado não pode ser alterado!");

        } else if (pagamentoStatus.equals("falha")) {

            if (statusDiferentePendente) {
                throw new ControllersException(HttpStatus.BAD_REQUEST,
                        "Não pode ser alterado para outro valor além de pendente");

            }
            pagamento.setStatus(status);
            pagamentoRepository.save(pagamento);
        }

        return pagamento;

    }
}
