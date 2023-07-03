package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.AnoSerieUnidade;
import br.com.appetitegourmet.api.models.AnoSerieUnidadeTurno;
import br.com.appetitegourmet.api.repositories.AnoSerieUnidadeRepository;
import br.com.appetitegourmet.api.repositories.AnoSerieUnidadeTurnoRepository;
import utils.Turno_Enum;

@Service
public class AnoSerieUnidadeTurnoService {
    private final AnoSerieUnidadeTurnoRepository anoSerieUnidadeTurnoRepository;
    private final AnoSerieUnidadeRepository anoSerieUnidadeRepository;
    
    public AnoSerieUnidadeTurnoService(AnoSerieUnidadeTurnoRepository anoSerieUnidadeTurnoRepository, 
    					   AnoSerieUnidadeRepository anoSerieUnidadeRepository) {
        this.anoSerieUnidadeTurnoRepository = anoSerieUnidadeTurnoRepository;
		this.anoSerieUnidadeRepository = anoSerieUnidadeRepository;
    }
    
    public List<AnoSerieUnidadeTurno> listarAnoSerieUnidadeTurnos() {
        return anoSerieUnidadeTurnoRepository.findAll();
    }
    
    public List<AnoSerieUnidadeTurno> listarAnoSerieUnidadeTurnosPorAnoSerieUnidade(Long anoSerieUnidadeId) {
        return anoSerieUnidadeTurnoRepository.findByAnoSerieUnidadeId(anoSerieUnidadeId);
    }
    
    public AnoSerieUnidadeTurno buscarAnoSerieUnidadeTurnoPorId(Long id) {
        return anoSerieUnidadeTurnoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ano Série Unidade Turno não encontrado"));
    }
    
    public AnoSerieUnidadeTurno salvarAnoSerieUnidadeTurno(AnoSerieUnidadeTurno anoSerieUnidadeTurno) {
        return anoSerieUnidadeTurnoRepository.save(anoSerieUnidadeTurno);
    }
    
    public void excluirAnoSerieUnidadeTurno(Long id) {
    	anoSerieUnidadeTurnoRepository.deleteById(id);
    }

    public AnoSerieUnidadeTurno editarAnoSerieUnidadeTurno(Long id, 
    													   AnoSerieUnidadeTurno anoSerieUnidadeTurno) {
    	Optional<AnoSerieUnidadeTurno> optionalAnoSerieUnidadeTurno = anoSerieUnidadeTurnoRepository.findById(id);
    	if (optionalAnoSerieUnidadeTurno.isPresent()) {
    		AnoSerieUnidadeTurno novoAnoSerieUnidadeTurno = optionalAnoSerieUnidadeTurno.get();

    		if(anoSerieUnidadeTurno.getTurno() != null)
    		{
    			novoAnoSerieUnidadeTurno.setTurno(anoSerieUnidadeTurno.getTurno());
    		}
    		if(anoSerieUnidadeTurno.getAnoSerieUnidade() != null)
    		{
    			Optional<AnoSerieUnidade> optionalAnoSerieUnidade = 
    					anoSerieUnidadeRepository.findById(anoSerieUnidadeTurno.getAnoSerieUnidade().getId());
    			if (optionalAnoSerieUnidade.isPresent()) {
    				AnoSerieUnidade novoAnoSerieUnidade = optionalAnoSerieUnidade.get();
    				novoAnoSerieUnidadeTurno.setAnoSerieUnidade(novoAnoSerieUnidade);
    			} else {
    				throw new NoSuchElementException("Ano Série Unidade não encontrado");
    			}
    		}
    		return anoSerieUnidadeTurnoRepository.save(novoAnoSerieUnidadeTurno);
    	} else {
    		return null;
    	}
    }
}