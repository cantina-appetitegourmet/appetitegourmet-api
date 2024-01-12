package br.com.appetitegourmet.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.appetitegourmet.api.models.CidadeAnoLetivo;

public interface CidadeAnoLetivoRepository extends JpaRepository<CidadeAnoLetivo, Long> {

	List<CidadeAnoLetivo> findAllByAnoLetivoId(Long anoLetivoId);
	Optional <CidadeAnoLetivo> findByAnoLetivoIdAndCidadeId(Long anoLetivoId, Long cidadeId);
}
