package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.exception.EntidadeObrigatoriaException;
import br.com.appetitegourmet.api.exception.OperacaoInvalidaException;
import br.com.appetitegourmet.api.models.Aluno;
import br.com.appetitegourmet.api.models.Parentesco;
import br.com.appetitegourmet.api.models.Pessoa;
import br.com.appetitegourmet.api.models.Responsavel;
import br.com.appetitegourmet.api.models.ResponsavelAluno;
import br.com.appetitegourmet.api.repositories.AlunoRepository;
import br.com.appetitegourmet.api.repositories.ParentescoRepository;
import br.com.appetitegourmet.api.repositories.PessoaRepository;
import br.com.appetitegourmet.api.repositories.ResponsavelAlunoRepository;
import br.com.appetitegourmet.api.repositories.ResponsavelRepository;

@Service
public class ResponsavelAlunoService {
	private final ResponsavelAlunoRepository responsavelAlunoRepository;
	private final ResponsavelRepository responsavelRepository;
	private final AlunoRepository alunoRepository;
	private final PessoaRepository pessoaRepository;
	private final ParentescoRepository parentescoRepository;

	public ResponsavelAlunoService(ResponsavelAlunoRepository responsavelAlunoRepository, ResponsavelRepository responsavelRepository, AlunoRepository alunoRepository, ParentescoRepository parentescoRepository, PessoaRepository pessoaRepository) {
		this.responsavelAlunoRepository = responsavelAlunoRepository;
		this.responsavelRepository = responsavelRepository;
		this.alunoRepository = alunoRepository;
		this.pessoaRepository = pessoaRepository;
		this.parentescoRepository = parentescoRepository;
		
	}
	
	public List<ResponsavelAluno> listarResponsavelAlunos() {
        return responsavelAlunoRepository.findAll();
    }
	
	public ResponsavelAluno buscarResponsavelAlunoPorId(Long id) {
        return responsavelAlunoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Responsavel Aluno não encontrado"));
    }
	
	public ResponsavelAluno salvarResponsavelAluno(ResponsavelAluno responsavelAluno) {
		
		if(responsavelAluno.getAluno() == null) {
			throw new EntidadeObrigatoriaException("Obrigatório ter o aluno na relação");
		} else {
			if(responsavelAluno.getAluno().getId() == null) {
				if(responsavelAluno.getAluno().getPessoa().getNomeCompleto() == null) {
					throw new OperacaoInvalidaException("Não é possível criar Aluno a partir deste endpoint");
				} else {
					if(responsavelAluno.getAluno().getPessoa() != null) {
			    		if(responsavelAluno.getAluno().getPessoa().getId() == null) {
			    			Pessoa novaPessoa = pessoaRepository.save(responsavelAluno.getAluno().getPessoa());
			    			responsavelAluno.getAluno().setPessoa(novaPessoa);
			    		}
					}
			        alunoRepository.save(responsavelAluno.getAluno());
				}
			} else {
				Optional<Aluno> optional = alunoRepository.findById(responsavelAluno.getAluno().getId());
				if(optional.isPresent()) {
					responsavelAluno.setAluno(optional.get());
				} else {
					throw new NoSuchElementException("Aluno não encontrado");
				}
			}
		}
		
		if(responsavelAluno.getResponsavel() == null) {
			throw new EntidadeObrigatoriaException("Obrigatório ter o responsável na relação");
		} else {
			if(responsavelAluno.getResponsavel().getId() == null) {
				throw new OperacaoInvalidaException("Não é possível criar Responsável a partir deste endpoint");
			} else {
				Optional<Responsavel> optional = responsavelRepository.findById(responsavelAluno.getResponsavel().getId());
				if(optional.isPresent()) {
					responsavelAluno.setResponsavel(optional.get());
				} else {
					throw new NoSuchElementException("Responsável não encontrado");
				}
			}
		}
		
		if(responsavelAluno.getParentesco() == null) {
			throw new EntidadeObrigatoriaException("Obrigatório ter um parentesco na relação");
		} else {
			if(responsavelAluno.getParentesco().getId() == null) {
				throw new OperacaoInvalidaException("Não é possível criar Parentesco a partir deste endpoint");
			} else {
				Optional<Parentesco> optional = parentescoRepository.findById(responsavelAluno.getParentesco().getId());
				if(optional.isPresent()) {
					responsavelAluno.setParentesco(optional.get());
				} else {
					throw new NoSuchElementException("Parentesco não encontrado");
				}
			}
		}
		
		return responsavelAlunoRepository.save(responsavelAluno);
	}
	
	public void excluirResponsavelAluno(Long id) {
        responsavelAlunoRepository.deleteById(id);
    }
}
