package com.desafio.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.api.config.exception.ControllersException;
import com.desafio.api.config.response.ResponseHandler;
import com.desafio.api.model.*;
import com.desafio.api.services.*;

@RestController
@RequestMapping("/api/process")
@CrossOrigin(origins = "*")
public class ProcessamentoController {

    @Autowired
    ProcessamentoService processamentoService;

    @PutMapping
    public ResponseEntity<Object> atualizarProcessamentoDePagamento(@RequestParam(value = "id") Long pagamentoId,
            @RequestParam(value = "status") String status) throws ControllersException {
        try {
            Pagamento pagamento = processamentoService.atualizar(pagamentoId, status);

            Map<String, String> response = new HashMap<>();

            if (pagamento == null) {
                response.put("error", "Id do pagamento não existe!");

                return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }

            return ResponseHandler.responseBuilder("Pagamento atualizado com sucesso!", HttpStatus.OK, pagamento);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
