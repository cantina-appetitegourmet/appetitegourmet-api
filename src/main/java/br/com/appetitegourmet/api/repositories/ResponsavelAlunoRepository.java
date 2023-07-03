package br.com.appetitegourmet.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.appetitegourmet.api.models.Aluno;
import br.com.appetitegourmet.api.models.Parentesco;
import br.com.appetitegourmet.api.models.Responsavel;
import br.com.appetitegourmet.api.models.ResponsavelAluno;

@Repository
public interface ResponsavelAlunoRepository extends JpaRepository<ResponsavelAluno, Long>  {

	List<Aluno> findByAlunoId(Long alunoId);
    List<Responsavel> findByResponsavelId(Long responsavelId);
    List<Parentesco> findByParentescoId(Long parentescoId);
}
