package br.com.appetitegourmet.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.Responsavel;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
	boolean existsByPessoaCpf(String cpf);
	
	boolean existsByPessoaEmail(String email);
}
