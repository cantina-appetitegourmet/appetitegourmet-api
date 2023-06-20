package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.AnoLetivo;
import br.com.appetitegourmet.api.models.TurmaAnoLetivo;
import br.com.appetitegourmet.api.models.Turma;
import br.com.appetitegourmet.api.repositories.AnoLetivoRepository;
import br.com.appetitegourmet.api.repositories.TurmaAnoLetivoRepository;
import br.com.appetitegourmet.api.repositories.TurmaRepository;

@Service
public class TurmaAnoLetivoService {
    private final TurmaAnoLetivoRepository turmaAnoLetivoRepository;
    private final AnoLetivoRepository anoLetivoRepository;
    private final TurmaRepository turmaRepository;
    
    public TurmaAnoLetivoService(TurmaAnoLetivoRepository turmaAnoLetivoRepository, 
    					   		  AnoLetivoRepository anoLetivoRepository,
    					   		  TurmaRepository turmaRepository) {
        this.turmaAnoLetivoRepository = turmaAnoLetivoRepository;
		this.anoLetivoRepository = anoLetivoRepository;
		this.turmaRepository = turmaRepository;
    }
    
    public List<TurmaAnoLetivo> listarTurmaAnoLetivos() {
        return turmaAnoLetivoRepository.findAll();
    }
    
    public List<TurmaAnoLetivo> listarTurmaAnoLetivosPorAnoLetivo(Long anoLetivoId) {
        return turmaAnoLetivoRepository.findByAnoLetivoId(anoLetivoId);
    }
    
    public List<TurmaAnoLetivo> listarTurmaAnoLetivosPorTurma(Long turmaId) {
        return turmaAnoLetivoRepository.findByAnoLetivoId(turmaId);
    }
    
    public List<TurmaAnoLetivo> listarTurmaAnoLetivosPorTurmaEAnoLetivo(Long anoLetivoId, Long turmaId) {
        return turmaAnoLetivoRepository.findByTurmaIdAndAnoLetivoId(anoLetivoId, turmaId);
    }
    
    public TurmaAnoLetivo buscarTurmaAnoLetivoPorId(Long id) {
        return turmaAnoLetivoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Turma Ano Letivo não encontrada"));
    }
    
    public TurmaAnoLetivo salvarTurmaAnoLetivo(TurmaAnoLetivo anoLetivoTurma) {
        return turmaAnoLetivoRepository.save(anoLetivoTurma);
    }
    
    public void excluirTurmaAnoLetivo(Long id) {
    	turmaAnoLetivoRepository.deleteById(id);
    }

    public TurmaAnoLetivo editarTurmaAnoLetivo(Long id, TurmaAnoLetivo turmaAnoLetivo) {
    	Optional<TurmaAnoLetivo> optionalTurmaAnoLetivo = turmaAnoLetivoRepository.findById(id);
    	if (optionalTurmaAnoLetivo.isPresent()) {
    		TurmaAnoLetivo novaTurmaAnoLetivo = optionalTurmaAnoLetivo.get();
    		
    		if(turmaAnoLetivo.getAtivo() != null) {
    			novaTurmaAnoLetivo.setAtivo(turmaAnoLetivo.getAtivo());
    		}

    		if(turmaAnoLetivo.getAnoLetivo() != null)
    		{
    			Optional<AnoLetivo> optionalAnoLetivo = 
    					anoLetivoRepository.findById(turmaAnoLetivo.getAnoLetivo().getId());
    			if (optionalAnoLetivo.isPresent()) {
    				AnoLetivo novoAnoLetivo = optionalAnoLetivo.get();
    				novaTurmaAnoLetivo.setAnoLetivo(novoAnoLetivo);
    			} else {
    				throw new NoSuchElementException("Ano Letivo não encontrado");
    			}
    		}
    		if(turmaAnoLetivo.getTurma() != null)
    		{
    			Optional<Turma> optionalTurma = 
    					turmaRepository.findById(turmaAnoLetivo.getTurma().getId());
    			if (optionalTurma.isPresent()) {
    				Turma novaTurma = optionalTurma.get();
    				novaTurmaAnoLetivo.setTurma(novaTurma);
    			} else {
    				throw new NoSuchElementException("Turma não encontrada");
    			}
    		}
    		return turmaAnoLetivoRepository.save(novaTurmaAnoLetivo);
    	} else {
    		return null;
    	}
    }
}
