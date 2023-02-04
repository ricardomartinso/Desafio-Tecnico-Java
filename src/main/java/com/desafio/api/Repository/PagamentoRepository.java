package com.desafio.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.desafio.api.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
