package br.com.appetitegourmet.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.Parentesco;

@Repository
public interface ParentescoRepository extends JpaRepository<Parentesco, Long> {
}
