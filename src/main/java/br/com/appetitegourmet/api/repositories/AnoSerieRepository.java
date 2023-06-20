package br.com.appetitegourmet.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.AnoSerie;

@Repository
public interface AnoSerieRepository extends JpaRepository<AnoSerie, Long> {
    List<AnoSerie> findBySerieId(Long serieId);
}
