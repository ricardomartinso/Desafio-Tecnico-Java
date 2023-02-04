package com.desafio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.desafio.api.model.Pagamento;
import java.util.*;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByCodigoDebito(int codigoDebito);

    List<Pagamento> findByCpf(String cpf);

    List<Pagamento> findByCnpj(String cnpj);

    List<Pagamento> findByStatus(String status);
}
