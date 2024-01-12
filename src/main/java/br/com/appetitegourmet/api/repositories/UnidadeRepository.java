package br.com.appetitegourmet.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
    List<Unidade> findByColegioId(Long colegioId);
    List<Unidade> findByEmpresaId(Long empresaId);
    List<Unidade> findByEmpresaIdAndColegioId(Long empresaId, Long colegioId);
    List<Unidade> findByCidadeId(Long cidadeId);
    List<Unidade> findByCidadeIdAndColegioId(Long cidadeId, Long colegioId);
}
