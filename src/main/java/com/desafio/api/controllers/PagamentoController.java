package com.desafio.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.api.dto.PagamentoDTO;
import com.desafio.api.services.*;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping()
    public Object criarPagamento(@RequestBody @Valid PagamentoDTO req) {

        return pagamentoService.salvar(req);

    }

}
