package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Turno;
import br.com.appetitegourmet.api.repositories.TurnoRepository;

@Service
public class TurnoService {
    private final TurnoRepository turnoRepository;
    
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }
    
    public List<Turno> listarTurnos() {
        return turnoRepository.findAll();
    }
    
    public Turno buscarTurnoPorId(Long id) {
        return turnoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Turno não encontrado"));
    }
    
    public Turno salvarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }
    
    public void excluirTurno(Long id) {
        turnoRepository.deleteById(id);
    }
}
