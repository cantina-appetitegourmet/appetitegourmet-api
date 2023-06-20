package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.PlanoAlimentar;
import br.com.appetitegourmet.api.repositories.PlanoAlimentarRepository;

@Service
public class PlanoAlimentarService {
    private final PlanoAlimentarRepository planoAlimentarRepository;
    
    public PlanoAlimentarService(PlanoAlimentarRepository planoAlimentarRepository) {
        this.planoAlimentarRepository = planoAlimentarRepository;
    }
    
    public List<PlanoAlimentar> listarPlanosAlimentares() {
        return planoAlimentarRepository.findAll();
    }
    
    public PlanoAlimentar buscarPlanoAlimentarPorId(Long id) {
        return planoAlimentarRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Plano Alimentar não encontrado"));
    }
    
    public PlanoAlimentar salvarPlanoAlimentar(PlanoAlimentar planoAlimentar) {
        return planoAlimentarRepository.save(planoAlimentar);
    }
    
    public void excluirPlanoAlimentar(Long id) {
        planoAlimentarRepository.deleteById(id);
    }
    
    public PlanoAlimentar editarPlanoAlimentar(Long id, PlanoAlimentar planoAlimentar) {
        Optional<PlanoAlimentar> optionalPlanoAlimentar = planoAlimentarRepository.findById(id);
        if (optionalPlanoAlimentar.isPresent()) {
            PlanoAlimentar novoPlanoAlimentar = optionalPlanoAlimentar.get();
            if(planoAlimentar.getDescricao() != null) {
            	novoPlanoAlimentar.setDescricao(planoAlimentar.getDescricao());
            }
            if(planoAlimentar.getDescritivo() != null) {
            	novoPlanoAlimentar.setDescritivo(planoAlimentar.getDescritivo());
            }
            if(planoAlimentar.getAtivo() != null) {
            	novoPlanoAlimentar.setAtivo(planoAlimentar.getAtivo());
            }
            return planoAlimentarRepository.save(novoPlanoAlimentar);
        } else {
        	throw new NoSuchElementException("Plano Alimentar não encontrado");
        }
    }
}
