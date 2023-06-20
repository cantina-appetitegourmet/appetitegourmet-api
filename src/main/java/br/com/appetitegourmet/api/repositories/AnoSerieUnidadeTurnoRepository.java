package br.com.appetitegourmet.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.appetitegourmet.api.models.AnoSerieUnidadeTurno;

public interface AnoSerieUnidadeTurnoRepository extends JpaRepository<AnoSerieUnidadeTurno, Long> {
    List<AnoSerieUnidadeTurno> findByAnoSerieUnidadeId(Long anoSerieUnidadeId);
}
