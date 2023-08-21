package br.com.appetitegourmet.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.ContratoPlano;
import br.com.appetitegourmet.api.models.PlanoAlimentarPreco;

@Repository
public interface ContratoPlanoRepository extends JpaRepository<ContratoPlano, Long> {

	@Query("select cp from Responsavel res, "
			           + "ResponsavelAluno ra, "
			           + "Contrato con, "
			           + "ContratoPlano cp "
		 + "where res.id = ra.responsavel_id and "
		       + "ra.id = con.responsavel_aluno.id and "
		       + "con.id = cp.contrato_id and "
		       + "res.id = ?1")
	List<ContratoPlano> findAllContratoPlanoByResponsavelId(Long id);
	List<ContratoPlano> findByContratoId2(Long responsavelId);
	List<PlanoAlimentarPreco> findByPlanoAlimentarPrecoId(Long planoAlimentarPrecoId);
	List<ContratoPlano> findByContratoId(Long contratoId);
}
