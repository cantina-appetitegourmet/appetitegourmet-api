package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Turma;
import br.com.appetitegourmet.api.repositories.TurmaRepository;

@Service
public class TurmaService {
    private final TurmaRepository turmaRepository;
    
    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }
    
    public List<Turma> listarTurmas() {
        return turmaRepository.findAll();
    }
    
    public Turma buscarTurmaPorId(Long id) {
        return turmaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Turma não encontrado"));
    }
    
    public Turma salvarTurma(Turma turma) {
        return turmaRepository.save(turma);
    }
    
    public void excluirTurma(Long id) {
        turmaRepository.deleteById(id);
    }
}
