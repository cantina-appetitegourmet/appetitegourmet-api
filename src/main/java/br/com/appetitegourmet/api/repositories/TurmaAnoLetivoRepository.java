package br.com.appetitegourmet.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.TurmaAnoLetivo;

@Repository
public interface TurmaAnoLetivoRepository extends JpaRepository<TurmaAnoLetivo, Long> {
    List<TurmaAnoLetivo> findByTurmaId(Long turmaId);
    List<TurmaAnoLetivo> findByAnoLetivoId(Long anoLetivoId);
    List<TurmaAnoLetivo> findByTurmaIdAndAnoLetivoId(Long turmaId, Long anoLetivoId);
}