package br.com.appetitegourmet.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.ContratoPlano;
import br.com.appetitegourmet.api.models.PlanoAlimentarPreco;

@Repository
public interface ContratoPlanoRepository extends JpaRepository<ContratoPlano, Long> {

	List<PlanoAlimentarPreco> findByPlanoAlimentarPrecoId(Long planoAlimentarPrecoId);
	List<ContratoPlano> findByContratoId(Long contratoId);
}
