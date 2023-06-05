package br.com.appetitegourmet.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.Desconto;

@Repository
public interface DescontoRepository extends JpaRepository<Desconto, Long> {
    List<Desconto> findByAnoLetivoId(Long anaLetivoId);
}

