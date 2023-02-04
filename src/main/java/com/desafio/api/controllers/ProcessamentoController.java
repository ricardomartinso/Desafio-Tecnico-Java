package com.desafio.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.api.model.*;
import com.desafio.api.services.*;

@RestController
@RequestMapping("/api/process")
@CrossOrigin(origins = "*")
public class ProcessamentoController {

    @Autowired
    ProcessamentoService processamentoService;

    @PutMapping
    public void atualizarProcessamentoDePagamento(@RequestParam(value = "id") Long paramId,
            @RequestParam(value = "status") String status) {

    }
}
