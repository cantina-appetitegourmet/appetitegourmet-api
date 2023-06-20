package br.com.appetitegourmet.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.AnoSerieUnidade;

@Repository
public interface AnoSerieUnidadeRepository extends JpaRepository<AnoSerieUnidade, Long> {
    List<AnoSerieUnidade> findByAnoSerieId(Long anoSerieId);
    List<AnoSerieUnidade> findByUnidadeId(Long unidadeId);
    List<AnoSerieUnidade> findByAnoSerieIdAndUnidadeId(Long anoSerieId, Long unidadeId);
}
