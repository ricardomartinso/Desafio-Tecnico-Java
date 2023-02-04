package com.desafio.api.model;

import com.desafio.api.dto.PagamentoDTO;
import com.desafio.api.utils.MetodoPagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@Entity
public class Pagamento {
    public Pagamento(PagamentoDTO pagamentoDTO, String cpf, String cnpj) {

        this.codigoDebito = pagamentoDTO.codigoDebito();
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.metodoPagamento = pagamentoDTO.metodoPagamento();
        this.numeroCartao = pagamentoDTO.numeroCartao().orElse(null);
        this.valorPagamento = pagamentoDTO.valorPagamento();
        this.processamento = "Pendente";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    int codigoDebito;

    @Column(length = 11, nullable = true)
    String cpf;

    @Column(length = 14, nullable = true)
    String cnpj;

    @Column(length = 50, nullable = false)
    MetodoPagamento metodoPagamento;

    @Column(length = 50, nullable = true)
    String numeroCartao;

    @Column(length = 50, nullable = false)
    double valorPagamento;

    @Column
    String processamento;

}
