package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.CategoriaTurma;
import br.com.appetitegourmet.api.repositories.CategoriaTurmaRepository;

@Service
public class CategoriaTurmaService {
    private final CategoriaTurmaRepository categoriaTurmaRepository;
    
    public CategoriaTurmaService(CategoriaTurmaRepository categoriaTurmaRepository) {
        this.categoriaTurmaRepository = categoriaTurmaRepository;
    }
    
    public List<CategoriaTurma> listarCategoriaTurmas() {
        return categoriaTurmaRepository.findAll();
    }
    
    public CategoriaTurma buscarCategoriaTurmaPorId(Long id) {
        return categoriaTurmaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoria Turma não encontrada"));
    }
    
    public CategoriaTurma salvarCategoriaTurma(CategoriaTurma categoriaTurma) {
        return categoriaTurmaRepository.save(categoriaTurma);
    }
    
    public void excluirCategoriaTurma(Long id) {
        categoriaTurmaRepository.deleteById(id);
    }
}
