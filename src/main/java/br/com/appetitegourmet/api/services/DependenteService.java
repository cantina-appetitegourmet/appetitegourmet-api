package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Dependente;
import br.com.appetitegourmet.api.repositories.DependenteRepository;

@Service
public class DependenteService {
    private final DependenteRepository dependenteRepository;
    
    public DependenteService(DependenteRepository dependenteRepository) {
        this.dependenteRepository = dependenteRepository;
    }
    
    public List<Dependente> listarDependentes() {
        return dependenteRepository.findAll();
    }
    
    public Dependente buscarDependentePorId(Long id) {
        return dependenteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Dependente não encontrado"));
    }
    
    public Dependente alterarDependente(Long id, Dependente dependente) {
    	Optional<Dependente> dependenteDB = dependenteRepository.findById(id);

        if(dependenteDB.isPresent()) {
        	return dependenteRepository.save(dependente);
        }
        return null;
    }
    
    public Dependente salvarDependente(Dependente dependente) {
        return dependenteRepository.save(dependente);
    }
    
    public void excluirDependente(Long id) {
        dependenteRepository.deleteById(id);
    }
}
