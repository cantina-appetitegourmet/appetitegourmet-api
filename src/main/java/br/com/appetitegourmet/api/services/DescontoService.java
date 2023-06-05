package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Desconto;
import br.com.appetitegourmet.api.repositories.DescontoRepository;

@Service
public class DescontoService {
    private final DescontoRepository descontoRepository;
    
    public DescontoService(DescontoRepository descontoRepository) {
        this.descontoRepository = descontoRepository;
    }
    
    public List<Desconto> listarDescontos() {
        return descontoRepository.findAll();
    }
    
    public List<Desconto> listarUnidadesPorColegio(Long anoLetivoId) {
        return descontoRepository.findByAnoLetivoId(anoLetivoId);
    }
    
    public Desconto buscarDescontoPorId(Long id) {
        return descontoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Desconto não encontrado"));
    }
    
    public Desconto salvarDesconto(Desconto desconto) {
        return descontoRepository.save(desconto);
    }
    
    public void excluirDesconto(Long id) {
        descontoRepository.deleteById(id);
    }
}