package br.com.appetitegourmet.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.ContratoDesconto;
import br.com.appetitegourmet.api.models.Desconto;

@Repository
public interface ContratoDescontoRepository extends JpaRepository<ContratoDesconto, Long> {

	List<Desconto> findByDescontoId(Long descontoId);
	List<ContratoDesconto> findByContratoId(Long contratoId);
}
