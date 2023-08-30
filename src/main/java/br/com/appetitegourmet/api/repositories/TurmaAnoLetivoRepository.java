package br.com.appetitegourmet.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.TurmaAnoLetivo;

@Repository
public interface TurmaAnoLetivoRepository extends JpaRepository<TurmaAnoLetivo, Long> {
    List<TurmaAnoLetivo> findByTurmaId(Long turmaId);
    List<TurmaAnoLetivo> findByAnoLetivoId(Long anoLetivoId);
    List<TurmaAnoLetivo> findByTurmaIdAndAnoLetivoId(Long turmaId, Long anoLetivoId);
    //List<TurmaAnoLetivo> findByUnidadeIdAndAnoLetivoId(Long unidadeId, Long anoLetivoId);
    @Query(value = "SELECT TAL.* FROM "
    							+ "TURMA_ANOS_LETIVOS TAL, "
    							+ "TURMAS T, "
    							+ "ANOS_LETIVOS AL,  "
    							+ "ANO_SERIE_UNIDADE_TURNOS ASUT,"
    							+ "ANOS_SERIE_UNIDADE ASU "
    						  + "WHERE ASU.ID = ?1 "
    						    //+ "AND ASU.ATIVO = TRUE "
    						    + "AND ASU.ID = ASUT.ANO_SERIE_UNIDADE_ID "
    						    //+ "AND ASUT.ATIVO = TRUE "
    						    + "AND ASUT.ID = T.ANO_SERIE_UNIDADE_TURNO_ID "
    						    //+ "AND T.ATIVO = TRUE "
    						    + "AND TAL.ATIVO = TRUE "
    						    + "AND T.ID = TAL.TURMA_ID "
    						    + "AND TAL.ATIVO = TRUE "
    						    + "AND TAL.ANO_LETIVO_ID = AL.ID "
    						    + "AND AL.ID = ?2 "
    						    + "AND AL.ATIVO = TRUE", nativeQuery = true)
    List<TurmaAnoLetivo> findByAnoSerieUnidadeId(Long anoSerieUnidadeId, Long anoLetivoId);
}