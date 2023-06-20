package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.PlanoAlimentar;
import br.com.appetitegourmet.api.repositories.PlanoAlimentarRepository;

@Service
public class PlanoAlimentarService {
    private final PlanoAlimentarRepository planoAlimentarRepository;
    
    public PlanoAlimentarService(PlanoAlimentarRepository planoAlimentarRepository) {
        this.planoAlimentarRepository = planoAlimentarRepository;
    }
    
    public List<PlanoAlimentar> listarCardapios() {
        return planoAlimentarRepository.findAll();
    }
    
    public PlanoAlimentar buscarCardapioPorId(Long id) {
        return planoAlimentarRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("PlanoAlimentar não encontrado"));
    }
    
    public PlanoAlimentar salvarCardapio(PlanoAlimentar planoAlimentar) {
        return planoAlimentarRepository.save(planoAlimentar);
    }
    
    public void excluirCardapio(Long id) {
        planoAlimentarRepository.deleteById(id);
    }
}
