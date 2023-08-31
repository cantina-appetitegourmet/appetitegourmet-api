package br.com.appetitegourmet.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.Contrato;
import br.com.appetitegourmet.api.models.ResponsavelAluno;
import br.com.appetitegourmet.api.models.TurmaAnoLetivo;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

	List<ResponsavelAluno> findByResponsavelAlunoId(Long responsavelAlunoId);
	List<TurmaAnoLetivo> findByTurmaAnoLetivoId(Long turmaAnoLetivoId);
	
	@Query("select con from Responsavel res, "
			             + "ResponsavelAluno ra, "
			             + "Contrato con "
		 + "where res.id = ra.responsavel_id and "
		    + "ra.id = con.responsavel_aluno.id and "
		    + "res.id = ?1")
	List<Contrato> findAllByResponsavelId(Long responsavelId);
}
