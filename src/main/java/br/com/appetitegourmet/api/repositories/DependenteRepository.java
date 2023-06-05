package br.com.appetitegourmet.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.Dependente;

@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Long> {
}