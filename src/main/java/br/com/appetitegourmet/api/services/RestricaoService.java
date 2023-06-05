package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Restricao;
import br.com.appetitegourmet.api.repositories.RestricaoRepository;

@Service
public class RestricaoService {
    private final RestricaoRepository restricaoRepository;
    
    public RestricaoService(RestricaoRepository restricaoRepository) {
        this.restricaoRepository = restricaoRepository;
    }
    
    public List<Restricao> listarRestricaos() {
        return restricaoRepository.findAll();
    }
    
    public Restricao buscarRestricaoPorId(Long id) {
        return restricaoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Restricao não encontrado"));
    }
    
    public Restricao salvarRestricao(Restricao restricao) {
        return restricaoRepository.save(restricao);
    }
    
    public void excluirRestricao(Long id) {
        restricaoRepository.deleteById(id);
    }
}
