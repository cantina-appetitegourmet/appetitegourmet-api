package br.com.appetitegourmet.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.appetitegourmet.api.models.PlanoAlimentarPreco;

public interface PlanoAlimentarPrecoRepository extends JpaRepository<PlanoAlimentarPreco, Long> {
    List<PlanoAlimentarPreco> findByPlanoAlimentarId(Long planoAlimentarId);
    List<PlanoAlimentarPreco> findByTurmaAnoLetivoId(Long turmaAnoLetivoId);
    List<PlanoAlimentarPreco> findByPlanoAlimentarIdAndTurmaAnoLetivoId(Long planoAlimentarId, Long turmaAnoLetivoId);
}