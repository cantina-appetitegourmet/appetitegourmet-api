package br.com.appetitegourmet.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    List<Turma> findByColegioId(Long colegioId);
}
