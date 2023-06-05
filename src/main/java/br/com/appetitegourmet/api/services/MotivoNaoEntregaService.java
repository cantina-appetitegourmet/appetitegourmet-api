package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.MotivoNaoEntrega;
import br.com.appetitegourmet.api.repositories.MotivoNaoEntregaRepository;

@Service
public class MotivoNaoEntregaService {
    private final MotivoNaoEntregaRepository motivoNaoEntregaRepository;
    
    public MotivoNaoEntregaService(MotivoNaoEntregaRepository motivoNaoEntregaRepository) {
        this.motivoNaoEntregaRepository = motivoNaoEntregaRepository;
    }
    
    public List<MotivoNaoEntrega> listarMotivoNaoEntregas() {
        return motivoNaoEntregaRepository.findAll();
    }
    
    public MotivoNaoEntrega buscarMotivoNaoEntregaPorId(Long id) {
        return motivoNaoEntregaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("MotivoNaoEntrega não encontrado"));
    }
    
    public MotivoNaoEntrega salvarMotivoNaoEntrega(MotivoNaoEntrega motivoNaoEntrega) {
        return motivoNaoEntregaRepository.save(motivoNaoEntrega);
    }
    
    public void excluirMotivoNaoEntrega(Long id) {
        motivoNaoEntregaRepository.deleteById(id);
    }
}