package com.desafio.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.api.config.exception.ApiExceptionMessage;
import com.desafio.api.config.response.ResponseHandler;
import com.desafio.api.dto.ProcessamentoDTO;
import com.desafio.api.model.*;
import com.desafio.api.services.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/process")
@CrossOrigin(origins = "*")
public class ProcessamentoController {

    @Autowired
    ProcessamentoService processamentoService;

    @PutMapping
    public ResponseEntity<Object> atualizarProcessamentoDePagamento(
            @RequestBody @Valid ProcessamentoDTO processamentoDto) {
        try {

            Pagamento pagamento = processamentoService.atualizar(processamentoDto.id(),
                    processamentoDto.statusPagamento());

            Map<String, String> response = new HashMap<>();

            if (pagamento == null) {
                response.put("error", "Id do pagamento não existe!");

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            return ResponseHandler.responseBuilder("Pagamento atualizado com sucesso!", HttpStatus.OK, pagamento);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

    }
}
