package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Colegio;
import br.com.appetitegourmet.api.repositories.ColegioRepository;

@Service
public class ColegioService {
    private final ColegioRepository colegioRepository;
    
    public ColegioService(ColegioRepository colegioRepository) {
        this.colegioRepository = colegioRepository;
    }
    
    public List<Colegio> listarColegios() {
        return colegioRepository.findAll();
    }
    
    public Colegio buscarColegioPorId(Long id) {
        return colegioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Colegio não encontrado"));
    }
    
    public Colegio salvarColegio(Colegio colegio) {
        return colegioRepository.save(colegio);
    }
    
    public void excluirColegio(Long id) {
        colegioRepository.deleteById(id);
    }
}