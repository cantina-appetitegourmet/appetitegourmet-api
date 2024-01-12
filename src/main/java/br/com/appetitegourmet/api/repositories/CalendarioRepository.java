package br.com.appetitegourmet.api.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.Calendario;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, Long> {
	List<Calendario> findByAnoLetivoId(Long anoLetivoId);
    List<Calendario> findByData(Date data);
    List<Calendario> findByCidadeId(Long cidadeId);
    List<Calendario> findByCidadeIdAndAnoLetivoId(Long cidadeId, Long anoLetivoId);
    List<Calendario> findByCidadeIdAndAnoLetivoIdAndData(Long cidadeId, Long anoLetivoId, Date data);
    List<Calendario> findByCidadeIdAndAnoLetivoIdAndDataBetween(Long cidadeId, Long anoLetivoId, Date startDate, Date endDate);
}
