package br.com.appetitegourmet.api.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.appetitegourmet.api.models.CalendarioExcecao;

public interface CalendarioExcecaoRepository extends JpaRepository<CalendarioExcecao, Long>  {
	List<CalendarioExcecao> findByDataAndAnoLetivoId(Date data, Long anoLetivoId);
	List<CalendarioExcecao> findByDataAndAnoLetivoIdAndUnidadeId(Date data, Long anoLetivoId, Long unidadeId);
}
