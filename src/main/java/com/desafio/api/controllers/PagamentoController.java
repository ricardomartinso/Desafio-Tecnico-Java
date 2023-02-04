package com.desafio.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.api.config.exception.ControllersException;
import com.desafio.api.config.response.ResponseHandler;
import com.desafio.api.dto.PagamentoDTO;
import com.desafio.api.services.*;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.desafio.api.model.*;

import java.util.*;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "*")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping()
    public List<Pagamento> listarPagamentos() {

        return pagamentoService.listarTodos();

    }

    @GetMapping("/codigo-debito/{codigoDebito}")
    public List<Pagamento> listarPagamentosPorCodigoDebito(@PathVariable int codigoDebito) {

        return pagamentoService.listarPorCodigoDebito(codigoDebito);

    }

    @GetMapping("/cpf-cnpj/{cpfCnpj}")
    public List<Pagamento> listarPagamentosPorCpfCnpj(@PathVariable String cpfCnpj) {

        return pagamentoService.listarPorCpfCnpj(cpfCnpj);

    }

    @GetMapping("/status/{status}")
    public List<Pagamento> listarPagamentosPorStatus(@PathVariable String status) {

        return pagamentoService.listarPorStatus(status);

    }

    @PostMapping()
    public ResponseEntity<Object> criarPagamento(@RequestBody @Valid PagamentoDTO req) throws ControllersException {

        try {
            Pagamento pagamento = pagamentoService.salvar(req);

            return ResponseHandler.responseBuilder("Pagamento criado com sucesso!", HttpStatus.CREATED, pagamento);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarPagamento(@PathVariable Long id) {

        try {
            Pagamento pagamento = pagamentoService.deletarPagamentoPorId(id);

            Map<String, String> response = new HashMap<>();

            if (pagamento == null) {
                response.put("error", "Id do pagamento não existe!");

                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }

            return ResponseHandler.responseBuilder("Pagamento deletado com sucesso!", HttpStatus.OK, pagamento);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
