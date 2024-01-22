package br.com.appetitegourmet.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.ResponsavelAluno;

@Repository
public interface ResponsavelAlunoRepository extends JpaRepository<ResponsavelAluno, Long>  {

}
