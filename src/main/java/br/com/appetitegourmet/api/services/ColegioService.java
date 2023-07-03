package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Colegio;
import br.com.appetitegourmet.api.models.Endereco;
import br.com.appetitegourmet.api.repositories.ColegioRepository;
import br.com.appetitegourmet.api.repositories.EnderecoRepository;

@Service
public class ColegioService {
    private final ColegioRepository colegioRepository;
    private final EnderecoRepository enderecoRepository;

    public ColegioService(ColegioRepository colegioRepository, EnderecoRepository enderecoRepository) {
        this.colegioRepository = colegioRepository;
		this.enderecoRepository = enderecoRepository;
    }

    public List<Colegio> listarColegios() {
        return colegioRepository.findAll();
    }

    public Colegio buscarColegioPorId(Long id) {
        return colegioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Colegio não encontrado"));
    }

    public Colegio salvarColegio(Colegio colegio) {
    	if(colegio.getEndereco() != null) {
    		Endereco retorno;
    		retorno = enderecoRepository.save(colegio.getEndereco());
    		if(retorno == null) {
    			new Exception("Falha ao inserir o endereço");
    		}
    		colegio.setEndereco(retorno);
    	}
        return colegioRepository.save(colegio);
    }

    public void excluirColegio(Long id) {
        colegioRepository.deleteById(id);
    }

    public Colegio editarColegio(Long id, Colegio colegio) {
        Optional<Colegio> optionalColegio = colegioRepository.findById(id);
        if (optionalColegio.isPresent()) {
            Colegio novoColegio = optionalColegio.get();
            if(colegio.getNome() != null) {
            	novoColegio.setNome(colegio.getNome());
            }
            if(colegio.getAtivo() != null) {
            	novoColegio.setAtivo(colegio.getAtivo());
            }
            return colegioRepository.save(novoColegio);
        } else {
        	throw new NoSuchElementException("Colegio não encontrado");
        }
    }
}