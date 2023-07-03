package br.com.appetitegourmet.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.Contrato;
import br.com.appetitegourmet.api.models.ResponsavelAluno;
import br.com.appetitegourmet.api.models.TurmaAnoLetivo;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

	List<ResponsavelAluno> findByResponsavelAlunoId(Long responsavelAlunoId);
	List<TurmaAnoLetivo> findByTurmaAnoLetivoId(Long turmaAnoLetivoId);
}
