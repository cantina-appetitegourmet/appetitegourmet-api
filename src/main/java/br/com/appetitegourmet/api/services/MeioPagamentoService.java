package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.MeioPagamento;
import br.com.appetitegourmet.api.repositories.MeioPagamentoRepository;

@Service
public class MeioPagamentoService {
    private final MeioPagamentoRepository meioPagamentoRepository;
    
    public MeioPagamentoService(MeioPagamentoRepository meioPagamentoRepository) {
        this.meioPagamentoRepository = meioPagamentoRepository;
    }
    
    public List<MeioPagamento> listarMeioPagamentos() {
        return meioPagamentoRepository.findAll();
    }
    
    public MeioPagamento buscarMeioPagamentoPorId(Long id) {
        return meioPagamentoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Meio de Pagamento não encontrado"));
    }
    
    public MeioPagamento salvarMeioPagamento(MeioPagamento meioPagamento) {
        return meioPagamentoRepository.save(meioPagamento);
    }
    
    public void excluirMeioPagamento(Long id) {
        meioPagamentoRepository.deleteById(id);
    }
}