package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.AnoSerie;
import br.com.appetitegourmet.api.models.Serie;
import br.com.appetitegourmet.api.repositories.AnoSerieRepository;
import br.com.appetitegourmet.api.repositories.SerieRepository;

@Service
public class AnoSerieService {
    private final AnoSerieRepository anoSerieRepository;
    private final SerieRepository serieRepository;
    
    public AnoSerieService(AnoSerieRepository anoSerieRepository, 
    					   SerieRepository serieRepository) {
        this.anoSerieRepository = anoSerieRepository;
		this.serieRepository = serieRepository;
    }
    
    public List<AnoSerie> listarAnoSeries() {
        return anoSerieRepository.findAll();
    }
    
    public List<AnoSerie> listarAnoSeriesPorSerie(Long serieId) {
        return anoSerieRepository.findBySerieId(serieId);
    }
    
    public AnoSerie buscarAnoSeriePorId(Long id) {
        return anoSerieRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ano Série não encontrado"));
    }
    
    public AnoSerie salvarAnoSerie(AnoSerie anoSerie) {
        return anoSerieRepository.save(anoSerie);
    }
    
    public void excluirAnoSerie(Long id) {
    	anoSerieRepository.deleteById(id);
    }

    public AnoSerie editarAnoSerie(Long id, AnoSerie anoSerie) {
    	Optional<AnoSerie> optionalAnoSerie = anoSerieRepository.findById(id);
    	if (optionalAnoSerie.isPresent()) {
    		AnoSerie novoAnoSerie = optionalAnoSerie.get();

    		if(!anoSerie.getNome().isBlank() && 
    		   !anoSerie.getNome().isEmpty())
    		{
    			novoAnoSerie.setNome(anoSerie.getNome());
    		}
    		if(anoSerie.getSerie() != null)
    		{
    			Optional<Serie> optionalSerie = 
    					serieRepository.findById(anoSerie.getSerie().getId());
    			if (optionalSerie.isPresent()) {
    				Serie novaSerie = optionalSerie.get();
    				novoAnoSerie.setSerie(novaSerie);
    			} else {
    				throw new NoSuchElementException("Série não encontrada");
    			}
    		}
    		return anoSerieRepository.save(novoAnoSerie);
    	} else {
    		return null;
    	}
    }
}