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
public class ProcessamentoService {

    @Autowired
    PagamentoRepository pagamentoRepository;
}
