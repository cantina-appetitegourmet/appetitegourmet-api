package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Unidade;
import br.com.appetitegourmet.api.repositories.UnidadeRepository;

@Service
public class UnidadeService {
    private final UnidadeRepository unidadeRepository;
    
    public UnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }
    
    public List<Unidade> listarUnidades() {
        return unidadeRepository.findAll();
    }
    
    public List<Unidade> listarUnidadesPorColegio(Long colegioId) {
        return unidadeRepository.findByColegioId(colegioId);
    }
    
    public Unidade buscarUnidadePorId(Long id) {
        return unidadeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Unidade não encontrada"));
    }
    
    public Unidade salvarUnidade(Unidade unidade) {
        return unidadeRepository.save(unidade);
    }
    
    public void excluirUnidade(Long id) {
        unidadeRepository.deleteById(id);
    }
}
