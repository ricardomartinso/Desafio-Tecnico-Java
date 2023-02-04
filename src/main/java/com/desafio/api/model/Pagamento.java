package com.desafio.api.model;

import com.desafio.api.dto.PagamentoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pagamentos")
public class Pagamento {
    public Pagamento(PagamentoDTO pagamentoDTO, String cpf, String cnpj) {

        this.codigoDebito = pagamentoDTO.codigoDebito();
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.metodoPagamento = pagamentoDTO.metodoPagamento().toString();
        this.numeroCartao = pagamentoDTO.numeroCartao().orElse(null);
        this.valorPagamento = pagamentoDTO.valorPagamento();
        this.status = "pendente";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(updatable = false)
    int codigoDebito;

    @Column(length = 11, nullable = true, updatable = false)
    String cpf;

    @Column(length = 14, nullable = true, updatable = false)
    String cnpj;

    @Column(length = 50, nullable = false, updatable = false)
    String metodoPagamento;

    @Column(length = 50, nullable = true, updatable = false)
    String numeroCartao;

    @Column(length = 50, nullable = false, updatable = false)
    double valorPagamento;

    @Column(length = 50, nullable = false)
    String status;

}
