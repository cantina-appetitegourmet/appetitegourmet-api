package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Cidade;
import br.com.appetitegourmet.api.repositories.CidadeRepository;

@Service
public class CidadeService {
    private final CidadeRepository cidadeRepository;
    
    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }
    
    public List<Cidade> listarCidades() {
        return cidadeRepository.findAll();
    }
    
    public Cidade buscarCidadePorId(Long id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cidade não encontrada"));
    }
    
    public Cidade salvarCidade(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }
    
    public void excluirCidade(Long id) {
        cidadeRepository.deleteById(id);
    }
}