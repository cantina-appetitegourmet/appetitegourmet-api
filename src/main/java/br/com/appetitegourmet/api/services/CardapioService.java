package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Cardapio;
import br.com.appetitegourmet.api.repositories.CardapioRepository;

@Service
public class CardapioService {
    private final CardapioRepository cardapioRepository;
    
    public CardapioService(CardapioRepository cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }
    
    public List<Cardapio> listarCardapios() {
        return cardapioRepository.findAll();
    }
    
    public Cardapio buscarCardapioPorId(Long id) {
        return cardapioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cardapio não encontrado"));
    }
    
    public Cardapio salvarCardapio(Cardapio cardapio) {
        return cardapioRepository.save(cardapio);
    }
    
    public void excluirCardapio(Long id) {
        cardapioRepository.deleteById(id);
    }
}
