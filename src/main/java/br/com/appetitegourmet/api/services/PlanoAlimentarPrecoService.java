package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.PlanoAlimentar;
import br.com.appetitegourmet.api.models.PlanoAlimentarPreco;
import br.com.appetitegourmet.api.models.TurmaAnoLetivo;
import br.com.appetitegourmet.api.repositories.PlanoAlimentarRepository;
import br.com.appetitegourmet.api.repositories.PlanoAlimentarPrecoRepository;
import br.com.appetitegourmet.api.repositories.TurmaAnoLetivoRepository;

@Service
public class PlanoAlimentarPrecoService {
    private final PlanoAlimentarPrecoRepository planoAlimentarPrecoRepository;
    private final PlanoAlimentarRepository planoAlimentarRepository;
    private final TurmaAnoLetivoRepository turmaAnoLetivoRepository;
    
    public PlanoAlimentarPrecoService(PlanoAlimentarPrecoRepository planoAlimentarPrecoRepository, 
    					   		  PlanoAlimentarRepository planoAlimentarRepository,
    					   		  TurmaAnoLetivoRepository turmaAnoLetivoRepository) {
        this.planoAlimentarPrecoRepository = planoAlimentarPrecoRepository;
		this.planoAlimentarRepository = planoAlimentarRepository;
		this.turmaAnoLetivoRepository = turmaAnoLetivoRepository;
    }
    
    public List<PlanoAlimentarPreco> listarPlanoAlimentarPrecos() {
        return planoAlimentarPrecoRepository.findAll();
    }
    
    public List<PlanoAlimentarPreco> listarPlanoAlimentarPrecosPorPlanoAlimentar(Long planoAlimentarId) {
        return planoAlimentarPrecoRepository.findByPlanoAlimentarId(planoAlimentarId);
    }
    
    public List<PlanoAlimentarPreco> listarPlanoAlimentarPrecosPorTurmaAnoLetivo(Long turmaAnoLetivoId) {
        return planoAlimentarPrecoRepository.findByTurmaAnoLetivoId(turmaAnoLetivoId);
    }
    
    public List<PlanoAlimentarPreco> listarPlanoAlimentarPrecosPorPlanoAlimentarETurmaAnoLetivo(Long planoAlimentarId, Long turmaAnoLetivoId) {
        return planoAlimentarPrecoRepository.findByPlanoAlimentarIdAndTurmaAnoLetivoId(planoAlimentarId, turmaAnoLetivoId);
    }
    
    public PlanoAlimentarPreco buscarPlanoAlimentarPrecoPorId(Long id) {
        return planoAlimentarPrecoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Plano Alimentar Preço não encontrado"));
    }
    
    public PlanoAlimentarPreco salvarPlanoAlimentarPreco(PlanoAlimentarPreco planoAlimentarTurmaAnoLetivo) {
        return planoAlimentarPrecoRepository.save(planoAlimentarTurmaAnoLetivo);
    }
    
    public void excluirPlanoAlimentarPreco(Long id) {
    	planoAlimentarPrecoRepository.deleteById(id);
    }

    public PlanoAlimentarPreco editarPlanoAlimentarPreco(Long id, PlanoAlimentarPreco planoAlimentarPreco) {
    	Optional<PlanoAlimentarPreco> optionalPlanoAlimentarPreco = planoAlimentarPrecoRepository.findById(id);
    	if (optionalPlanoAlimentarPreco.isPresent()) {
    		PlanoAlimentarPreco novaPlanoAlimentarPreco = optionalPlanoAlimentarPreco.get();
    		
    		if(planoAlimentarPreco.getAtivo() != null) {
    			novaPlanoAlimentarPreco.setAtivo(planoAlimentarPreco.getAtivo());
    		}
    		
    		if(planoAlimentarPreco.getPrecoDia() != null) {
    			novaPlanoAlimentarPreco.setPrecoDia(planoAlimentarPreco.getPrecoDia());
    		}

    		if(planoAlimentarPreco.getPlanoAlimentar() != null)
    		{
    			Optional<PlanoAlimentar> optionalPlanoAlimentar = 
    					planoAlimentarRepository.findById(planoAlimentarPreco.getPlanoAlimentar().getId());
    			if (optionalPlanoAlimentar.isPresent()) {
    				PlanoAlimentar novoPlanoAlimentar = optionalPlanoAlimentar.get();
    				novaPlanoAlimentarPreco.setPlanoAlimentar(novoPlanoAlimentar);
    			} else {
    				throw new NoSuchElementException("Plano Alimentar não encontrado");
    			}
    		}
    		if(planoAlimentarPreco.getTurmaAnoLetivo() != null)
    		{
    			Optional<TurmaAnoLetivo> optionalTurmaAnoLetivo = 
    					turmaAnoLetivoRepository.findById(planoAlimentarPreco.getTurmaAnoLetivo().getId());
    			if (optionalTurmaAnoLetivo.isPresent()) {
    				TurmaAnoLetivo novaTurmaAnoLetivo = optionalTurmaAnoLetivo.get();
    				novaPlanoAlimentarPreco.setTurmaAnoLetivo(novaTurmaAnoLetivo);
    			} else {
    				throw new NoSuchElementException("Turma Ano Letivo não encontrada");
    			}
    		}
    		return planoAlimentarPrecoRepository.save(novaPlanoAlimentarPreco);
    	} else {
    		return null;
    	}
    }
}
