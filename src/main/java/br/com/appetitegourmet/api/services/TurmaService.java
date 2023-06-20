package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.AnoSerieUnidadeTurno;
import br.com.appetitegourmet.api.models.Turma;
import br.com.appetitegourmet.api.repositories.AnoSerieUnidadeTurnoRepository;
import br.com.appetitegourmet.api.repositories.TurmaRepository;

@Service
public class TurmaService {
    private final TurmaRepository turmaRepository;
    private final AnoSerieUnidadeTurnoRepository anoSerieUnidadeTurnoRepository;
    
    public TurmaService(TurmaRepository turmaRepository, 
    					   AnoSerieUnidadeTurnoRepository anoSerieUnidadeTurnoRepository) {
        this.turmaRepository = turmaRepository;
		this.anoSerieUnidadeTurnoRepository = anoSerieUnidadeTurnoRepository;
    }
    
    public List<Turma> listarTurmas() {
        return turmaRepository.findAll();
    }
    
    public List<Turma> listarTurmasPorAnoSerieUnidadeTurno(Long anoSerieUnidadeTurnoId) {
        return turmaRepository.findByAnoSerieUnidadeTurnoId(anoSerieUnidadeTurnoId);
    }
    
    public Turma buscarTurmaPorId(Long id) {
        return turmaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Turma não encontrada"));
    }
    
    public Turma salvarTurma(Turma turma) {
        return turmaRepository.save(turma);
    }
    
    public void excluirTurma(Long id) {
    	turmaRepository.deleteById(id);
    }

    public Turma editarTurma(Long id, Turma turma) {
    	Optional<Turma> optionalTurma = turmaRepository.findById(id);
    	if (optionalTurma.isPresent()) {
    		Turma novoTurma = optionalTurma.get();

    		if(turma.getTurma() != null)
    		{
    			novoTurma.setTurma(turma.getTurma());
    		}
    		if(turma.getAnoSerieUnidadeTurno() != null)
    		{
    			Optional<AnoSerieUnidadeTurno> optionalAnoSerieUnidadeTurno = 
    					anoSerieUnidadeTurnoRepository.findById(turma.getAnoSerieUnidadeTurno().getId());
    			if (optionalAnoSerieUnidadeTurno.isPresent()) {
    				AnoSerieUnidadeTurno novoAnoSerieUnidadeTurno = optionalAnoSerieUnidadeTurno.get();
    				novoTurma.setAnoSerieUnidadeTurno(novoAnoSerieUnidadeTurno);
    			} else {
    				throw new NoSuchElementException("Ano Série Unidade Turno não encontrado");
    			}
    		}
    		return turmaRepository.save(novoTurma);
    	} else {
    		return null;
    	}
    }
}