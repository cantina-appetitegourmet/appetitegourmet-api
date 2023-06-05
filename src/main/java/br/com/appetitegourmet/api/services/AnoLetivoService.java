package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.AnoLetivo;
import br.com.appetitegourmet.api.repositories.AnoLetivoRepository;

@Service
public class AnoLetivoService {
    private final AnoLetivoRepository anoLetivoRepository;
    
    public AnoLetivoService(AnoLetivoRepository anoLetivoRepository) {
        this.anoLetivoRepository = anoLetivoRepository;
    }
    
    public List<AnoLetivo> listarAnoLetivos() {
        return anoLetivoRepository.findAll();
    }
    
    public AnoLetivo buscarAnoLetivoPorId(Long id) {
        return anoLetivoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ano Letivo não encontrado"));
    }
    
    public AnoLetivo salvarAnoLetivo(AnoLetivo anoLetivo) {
        return anoLetivoRepository.save(anoLetivo);
    }
    
    public void excluirAnoLetivo(Long id) {
        anoLetivoRepository.deleteById(id);
    }
}