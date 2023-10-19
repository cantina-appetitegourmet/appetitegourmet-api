package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Responsavel;
import br.com.appetitegourmet.api.exception.PessoaNaoEncontradaException;
import br.com.appetitegourmet.api.models.Endereco;
import br.com.appetitegourmet.api.models.Pessoa;
import br.com.appetitegourmet.api.repositories.EnderecoRepository;
import br.com.appetitegourmet.api.repositories.PessoaRepository;
import br.com.appetitegourmet.api.repositories.ResponsavelRepository;

@Service
public class ResponsavelService {
    private final ResponsavelRepository responsavelRepository;
    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;
    
    public ResponsavelService(ResponsavelRepository responsavelRepository, PessoaRepository pessoaRepository, EnderecoRepository enderecoRepository) {
        this.responsavelRepository = responsavelRepository;
		this.pessoaRepository = pessoaRepository;
		this.enderecoRepository = enderecoRepository;
    }
    
    public List<Responsavel> listarResponsaveis() {
        return responsavelRepository.findAll();
    }
    
    public Boolean consultaCpf(String cpf) {
    	return responsavelRepository.existsByPessoaCpf(cpf);
    }
    
    public Boolean consultaEmail(String email) {
    	return responsavelRepository.existsByPessoaEmail(email);
    }
    
    public Responsavel buscarResponsavelPorId(Long id) {
        return responsavelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Responsavel não encontrado"));
    }
    
    public Responsavel alterarResponsavel(Long id, Responsavel responsavel) {
    	Optional<Responsavel> responsavelDB = responsavelRepository.findById(id);

        if(responsavelDB.isPresent()) {
        	return responsavelRepository.save(responsavel);
        }
        return null;
    }
    
    public Responsavel salvarResponsavel(Responsavel responsavel) {
    	if(responsavel.getPessoa() != null) {
    		if(responsavel.getPessoa().getId() == null) {
    			if(responsavel.getPessoa().getEndereco() != null) {
    				if(responsavel.getPessoa().getEndereco().getId() == null) {
    					Endereco novoEndereco = enderecoRepository.save(responsavel.getPessoa().getEndereco());
    					responsavel.getPessoa().setEndereco(novoEndereco);
    				}
    			}
    			Pessoa novaPessoa = pessoaRepository.save(responsavel.getPessoa());
				responsavel.setPessoa(novaPessoa);
    		}
		}
    	
        return responsavelRepository.save(responsavel);
    }
    
    public void excluirResponsavel(Long id) {
        responsavelRepository.deleteById(id);
    }
    
    public Responsavel editarResponsavel(Long id, Responsavel responsavel) {
    	Optional<Responsavel> optionalResponsavel = responsavelRepository.findById(id);
    	if (optionalResponsavel.isPresent()) {
    		Responsavel novoResponsavel = optionalResponsavel.get();

    		if(responsavel.getPessoa() != null)
    		{
    			Optional<Pessoa> optionalPessoa = 
    					pessoaRepository.findById(responsavel.getPessoa().getId());
    			if (optionalPessoa.isPresent()) {
    				Pessoa novaPessoa = optionalPessoa.get();
    				novoResponsavel.setPessoa(novaPessoa);
    			} else {
    				throw new PessoaNaoEncontradaException("Pessoa não encontrada");
    			}
    		}
    		return responsavelRepository.save(novoResponsavel);
    	} else {
    		throw new NoSuchElementException("Responsavel não encontrado");
    	}
    }
}
