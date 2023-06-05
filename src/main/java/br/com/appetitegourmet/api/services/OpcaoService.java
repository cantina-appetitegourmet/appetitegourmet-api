package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Opcao;
import br.com.appetitegourmet.api.repositories.OpcaoRepository;

@Service
public class OpcaoService {
    private final OpcaoRepository opcaoRepository;
    
    public OpcaoService(OpcaoRepository opcaoRepository) {
        this.opcaoRepository = opcaoRepository;
    }
    
    public List<Opcao> listarOpcaos() {
        return opcaoRepository.findAll();
    }
    
    public Opcao buscarOpcaoPorId(Long id) {
        return opcaoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Opção não encontrada"));
    }
    
    public Opcao salvarOpcao(Opcao opcao) {
        return opcaoRepository.save(opcao);
    }
    
    public void excluirOpcao(Long id) {
        opcaoRepository.deleteById(id);
    }
}
