package br.com.appetitegourmet.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.appetitegourmet.api.models.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

	List<Pagamento> findByContratoId(Long id);
}
