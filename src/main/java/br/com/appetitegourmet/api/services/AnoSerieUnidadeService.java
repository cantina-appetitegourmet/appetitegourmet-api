package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.AnoSerie;
import br.com.appetitegourmet.api.models.AnoSerieUnidade;
import br.com.appetitegourmet.api.models.Unidade;
import br.com.appetitegourmet.api.repositories.AnoSerieRepository;
import br.com.appetitegourmet.api.repositories.AnoSerieUnidadeRepository;
import br.com.appetitegourmet.api.repositories.UnidadeRepository;

@Service
public class AnoSerieUnidadeService {
    private final AnoSerieUnidadeRepository anoSerieUnidadeRepository;
    private final AnoSerieRepository anoSerieRepository;
    private final UnidadeRepository unidadeRepository;
    
    public AnoSerieUnidadeService(AnoSerieUnidadeRepository anoSerieUnidadeRepository, 
    					   		  AnoSerieRepository anoSerieRepository,
    					   		  UnidadeRepository unidadeRepository) {
        this.anoSerieUnidadeRepository = anoSerieUnidadeRepository;
		this.anoSerieRepository = anoSerieRepository;
		this.unidadeRepository = unidadeRepository;
    }
    
    public List<AnoSerieUnidade> listarAnoSerieUnidades() {
        return anoSerieUnidadeRepository.findAll();
    }
    
    public List<AnoSerieUnidade> listarAnoSerieUnidadesPorAnoSerie(Long anoSerieId) {
        return anoSerieUnidadeRepository.findByAnoSerieId(anoSerieId);
    }
    
    public List<AnoSerieUnidade> listarAnoSerieUnidadesPorUnidade(Long unidadeId) {
        return anoSerieUnidadeRepository.findByUnidadeId(unidadeId);
    }
    
    public List<AnoSerieUnidade> listarAnoSerieUnidadesPorAnoSerieEUnidade(Long anoSerieId, Long unidadeId) {
        return anoSerieUnidadeRepository.findByAnoSerieIdAndUnidadeId(anoSerieId, unidadeId);
    }
    
    public AnoSerieUnidade buscarAnoSerieUnidadePorId(Long id) {
        return anoSerieUnidadeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ano Série Unidade não encontrado"));
    }
    
    public AnoSerieUnidade salvarAnoSerieUnidade(AnoSerieUnidade anoSerieUnidade) {
        return anoSerieUnidadeRepository.save(anoSerieUnidade);
    }
    
    public void excluirAnoSerieUnidade(Long id) {
    	anoSerieUnidadeRepository.deleteById(id);
    }

    public AnoSerieUnidade editarAnoSerieUnidade(Long id, AnoSerieUnidade anoSerieUnidade) {
    	Optional<AnoSerieUnidade> optionalAnoSerieUnidade = anoSerieUnidadeRepository.findById(id);
    	if (optionalAnoSerieUnidade.isPresent()) {
    		AnoSerieUnidade novoAnoSerieUnidade = optionalAnoSerieUnidade.get();

    		if(anoSerieUnidade.getAnoSerie() != null)
    		{
    			Optional<AnoSerie> optionalAnoSerie = 
    					anoSerieRepository.findById(anoSerieUnidade.getAnoSerie().getId());
    			if (optionalAnoSerie.isPresent()) {
    				AnoSerie novoAnoSerie = optionalAnoSerie.get();
    				novoAnoSerieUnidade.setAnoSerie(novoAnoSerie);
    			} else {
    				throw new NoSuchElementException("Ano Série não encontrado");
    			}
    		}
    		if(anoSerieUnidade.getUnidade() != null)
    		{
    			Optional<Unidade> optionalUnidade = 
    					unidadeRepository.findById(anoSerieUnidade.getUnidade().getId());
    			if (optionalUnidade.isPresent()) {
    				Unidade novaUnidade = optionalUnidade.get();
    				novoAnoSerieUnidade.setUnidade(novaUnidade);
    			} else {
    				throw new NoSuchElementException("Unidade não encontrada");
    			}
    		}
    		return anoSerieUnidadeRepository.save(novoAnoSerieUnidade);
    	} else {
    		return null;
    	}
    }
}