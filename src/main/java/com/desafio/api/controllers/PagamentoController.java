package com.desafio.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.api.config.exception.*;
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
    public ResponseEntity<Object> listarPagamentosPorCodigoDebito(@PathVariable int codigoDebito) {

        List<Pagamento> pagamentos = pagamentoService.listarPorCodigoDebito(codigoDebito);

        return ResponseHandler.responseBuilder("Pagamentos por código débito filtrados com sucesso", HttpStatus.OK,
                pagamentos);

    }

    @GetMapping("/cpf-cnpj/{cpfCnpj}")
    public ResponseEntity<Object> listarPagamentosPorCpfCnpj(@PathVariable String cpfCnpj) throws Exception {
        List<Pagamento> pagamentos = pagamentoService.listarPorCpfCnpj(cpfCnpj);

        if (pagamentos == null) {

            Map<String, String> response = new HashMap<>();
            response.put("error", "CPF/CNPJ inválido!");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return ResponseHandler.responseBuilder("Pagamentos por CPF/CNPJ filtrados com sucesso", HttpStatus.OK,
                pagamentos);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Object> listarPagamentosPorStatus(@PathVariable String status) {

        List<Pagamento> pagamentos = pagamentoService.listarPorStatus(status);

        return ResponseHandler.responseBuilder("Pagamentos por status filtrados com sucesso", HttpStatus.OK,
                pagamentos);
    }

    @PostMapping()
    public ResponseEntity<Object> criarPagamento(@RequestBody @Valid PagamentoDTO req) {

        try {
            Pagamento pagamento = pagamentoService.salvar(req);

            return ResponseHandler.responseBuilder("Pagamento criado com sucesso!", HttpStatus.CREATED, pagamento);
        } catch (ApiExceptionMessage e) {

            e.printStackTrace();

            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarPagamento(@PathVariable Long id) {

        try {
            Pagamento pagamento = pagamentoService.deletarPagamentoPorId(id);

            return ResponseHandler.responseBuilder("Pagamento deletado com sucesso!", HttpStatus.OK, pagamento);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> error = new HashMap<>();

            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

    }
}
