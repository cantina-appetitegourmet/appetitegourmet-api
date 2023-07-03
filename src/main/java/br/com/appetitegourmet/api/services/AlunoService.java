package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Aluno;
import br.com.appetitegourmet.api.models.Pessoa;
import br.com.appetitegourmet.api.repositories.AlunoRepository;
import br.com.appetitegourmet.api.repositories.PessoaRepository;

@Service
public class AlunoService {
	private final AlunoRepository alunoRepository;
	private final PessoaRepository pessoaRepository;
	
	public AlunoService(AlunoRepository alunoRepository, PessoaRepository pessoaRepository) {
		this.alunoRepository = alunoRepository;
		this.pessoaRepository = pessoaRepository;
		
	}
	
	public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }
    
    public Aluno buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Aluno não encontrado"));
    }
    
    public Aluno salvarAluno(Aluno aluno) {
    	if(aluno.getPessoa() != null) {
    		if(aluno.getPessoa().getId() == null) {
    			Pessoa novaPessoa = pessoaRepository.save(aluno.getPessoa());
				aluno.setPessoa(novaPessoa);
    		}
		}
        return alunoRepository.save(aluno);
    }
    
    public void excluirAluno(Long id) {
    	alunoRepository.deleteById(id);
    }

    public Aluno editarAluno(Long id, Aluno aluno) {
    	Optional<Aluno> optionalAluno = alunoRepository.findById(id);
    	if (optionalAluno.isPresent()) {
    		Aluno novoAluno = optionalAluno.get();

    		if(aluno.getPessoa() != null)
    		{
    			Optional<Pessoa> optionalPessoa = 
    					pessoaRepository.findById(aluno.getPessoa().getId());
    			if (optionalPessoa.isPresent()) {
    				Pessoa novaPessoa = optionalPessoa.get();
    				novoAluno.setPessoa(novaPessoa);
    			} else {
    				throw new NoSuchElementException("Pessoa não encontrada");
    			}
    		}
    		return alunoRepository.save(novoAluno);
    	} else {
    		throw new NoSuchElementException("Aluno não encontrado");
    	}
    }

}
