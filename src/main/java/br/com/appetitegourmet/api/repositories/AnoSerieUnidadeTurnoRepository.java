package br.com.appetitegourmet.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.AnoSerieUnidadeTurno;

@Repository
public interface AnoSerieUnidadeTurnoRepository extends JpaRepository<AnoSerieUnidadeTurno, Long> {
    List<AnoSerieUnidadeTurno> findByAnoSerieUnidadeId(Long anoSerieUnidadeId);
}
