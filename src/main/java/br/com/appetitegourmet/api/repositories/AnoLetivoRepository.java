package br.com.appetitegourmet.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.AnoLetivo;

@Repository
public interface AnoLetivoRepository extends JpaRepository<AnoLetivo, Long> {
	
	List<AnoLetivo> queryByAtivoIsTrue();
}
