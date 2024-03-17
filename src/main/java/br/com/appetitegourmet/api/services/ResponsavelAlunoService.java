package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import br.com.appetitegourmet.api.dto.ResponsavelAlunoRequest;
import br.com.appetitegourmet.api.mapper.ResponsavelAlunoMapper;
import br.com.appetitegourmet.api.models.*;
import br.com.appetitegourmet.api.spring.login.models.User;
import br.com.appetitegourmet.api.spring.login.repository.UserRepository;
import br.com.appetitegourmet.api.spring.login.security.jwt.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.exception.EntidadeObrigatoriaException;
import br.com.appetitegourmet.api.exception.OperacaoInvalidaException;
import br.com.appetitegourmet.api.repositories.AlunoRepository;
import br.com.appetitegourmet.api.repositories.ParentescoRepository;
import br.com.appetitegourmet.api.repositories.PessoaRepository;
import br.com.appetitegourmet.api.repositories.ResponsavelAlunoRepository;
import br.com.appetitegourmet.api.repositories.ResponsavelRepository;

@Service
@AllArgsConstructor
public class ResponsavelAlunoService {
	private JwtUtils jwtUtils;
	UserRepository userRepository;
	ResponsavelAlunoMapper responsavelAlunoMapper;
	private final ResponsavelAlunoRepository responsavelAlunoRepository;
	private final ResponsavelRepository responsavelRepository;
	private final AlunoRepository alunoRepository;
	private final PessoaRepository pessoaRepository;
	private final ParentescoRepository parentescoRepository;
	
	public List<ResponsavelAluno> listarResponsavelAlunos() {
        return responsavelAlunoRepository.findAll();
    }
	
	public ResponsavelAluno buscarResponsavelAlunoPorId(Long id) {
        return responsavelAlunoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Responsavel Aluno não encontrado"));
    }
	
	public ResponsavelAluno salvarResponsavelAluno(HttpServletRequest request, ResponsavelAlunoRequest responsavelAlunoRequest) throws UsernameNotFoundException {
		String username = jwtUtils.getUserNameFromJwtToken(jwtUtils.getJwtFromCookies(request));
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));;
		Responsavel responsavel = user.getPessoa().getResponsavel();
		ResponsavelAluno responsavelAluno = responsavelAlunoMapper.INSTANCE.responsavelAlunoRequestToResponsavel(responsavelAlunoRequest);
		responsavelAluno.setResponsavel(responsavel);
		if(responsavelAluno.getAluno() == null) {
			throw new EntidadeObrigatoriaException("Obrigatório ter o aluno na relação");
		} else {
			if(responsavelAluno.getAluno().getId() == null) {
				if(responsavelAluno.getAluno().getPessoa().getNomeCompleto() == null) {
					throw new OperacaoInvalidaException("Não é possível criar Aluno a partir deste endpoint");
				} else {
					if(responsavelAluno.getAluno().getPessoa() != null) {
			    		if(responsavelAluno.getAluno().getPessoa().getId() == null) {
			    			Pessoa novaPessoa = responsavelAluno.getAluno().getPessoa();
			    			if((novaPessoa.getCpf() != null) && (novaPessoa.getCpf().trim() == "")) {
			    				novaPessoa.setCpf(null);
			    			}
			    			novaPessoa = pessoaRepository.save(novaPessoa);
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

	public List<ResponsavelAluno> buscarAlunosPorResponsavel(Long responsavelId) {
		return responsavelAlunoRepository.findByResponsavelId(responsavelId);
	}
	
	public void excluirResponsavelAluno(Long id) {
        responsavelAlunoRepository.deleteById(id);
    }
}
