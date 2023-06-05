package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Responsavel;
import br.com.appetitegourmet.api.repositories.ResponsavelRepository;

@Service
public class ResponsavelService {
    private final ResponsavelRepository responsavelRepository;
    
    public ResponsavelService(ResponsavelRepository responsavelRepository) {
        this.responsavelRepository = responsavelRepository;
    }
    
    public List<Responsavel> listarResponsaveis() {
        return responsavelRepository.findAll();
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
        return responsavelRepository.save(responsavel);
    }
    
    public void excluirResponsavel(Long id) {
        responsavelRepository.deleteById(id);
    }
}
