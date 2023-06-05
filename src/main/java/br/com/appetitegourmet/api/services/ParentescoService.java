package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Parentesco;
import br.com.appetitegourmet.api.repositories.ParentescoRepository;

@Service
public class ParentescoService {
    private final ParentescoRepository parentescoRepository;
    
    public ParentescoService(ParentescoRepository parentescoRepository) {
        this.parentescoRepository = parentescoRepository;
    }
    
    public List<Parentesco> listarParentescos() {
        return parentescoRepository.findAll();
    }
    
    public Parentesco buscarParentescoPorId(Long id) {
        return parentescoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Parentesco não encontrado"));
    }
    
    public Parentesco salvarParentesco(Parentesco parentesco) {
        return parentescoRepository.save(parentesco);
    }
    
    public void excluirParentesco(Long id) {
        parentescoRepository.deleteById(id);
    }
}
