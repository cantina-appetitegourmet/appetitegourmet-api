package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Contrato;
import br.com.appetitegourmet.api.models.ContratoDesconto;
import br.com.appetitegourmet.api.models.Desconto;
import br.com.appetitegourmet.api.repositories.ContratoDescontoRepository;
import br.com.appetitegourmet.api.repositories.ContratoRepository;
import br.com.appetitegourmet.api.repositories.DescontoRepository;

@Service
public class ContratoDescontoService {
	
	private final ContratoDescontoRepository contratoDescontoRepository;
	private final ContratoRepository contratoRepository;
	private final DescontoRepository descontoRepository;
	
	public ContratoDescontoService (DescontoRepository descontoRepository, 
			ContratoRepository contratoRepository, 
			ContratoDescontoRepository contratoDescontoRepository) {
		this.contratoDescontoRepository = contratoDescontoRepository;
		this.contratoRepository = contratoRepository;
		this.descontoRepository = descontoRepository;
		
	}
	
	public List<ContratoDesconto> listarContratoDescontos() {
        return contratoDescontoRepository.findAll();
    }

    public ContratoDesconto buscarContratoDescontoPorId(Long id) {
        return contratoDescontoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Contrato Desconto não encontrado"));
    }

    public ContratoDesconto salvarContratoDesconto(ContratoDesconto contratoDesconto) {
    	if(contratoDesconto.getContrato() != null) {
    		Contrato retorno;
    		retorno = contratoRepository.save(contratoDesconto.getContrato());
    		if(retorno == null) {
    			new Exception("Falha ao inserir o Contrato");
    		}
    		contratoDesconto.setContrato(retorno);
    	}
    	if(contratoDesconto.getDesconto() != null) {
    		Desconto retorno;
    		retorno = descontoRepository.save(contratoDesconto.getDesconto());
    		if(retorno == null) {
    			new Exception("Falha ao inserir o Desconto");
    		}
    		contratoDesconto.setDesconto(retorno);
    	}
        return contratoDescontoRepository.save(contratoDesconto);
    }

    public void excluirContratoDesconto(Long id) {
        contratoDescontoRepository.deleteById(id);
    }

    public ContratoDesconto editarContratoDesconto(Long id, ContratoDesconto contratoDesconto) {
        Optional<ContratoDesconto> optionalContratoDesconto = contratoDescontoRepository.findById(id);
        if (optionalContratoDesconto.isPresent()) {
            ContratoDesconto novoContratoDesconto = optionalContratoDesconto.get();
            if(contratoDesconto.getDataInicio() != null) {
            	novoContratoDesconto.setDataInicio(contratoDesconto.getDataInicio());
            }
            if(contratoDesconto.getDataFim() != null) {
            	novoContratoDesconto.setDataFim(contratoDesconto.getDataFim());
            }
            return contratoDescontoRepository.save(novoContratoDesconto);
        } else {
        	throw new NoSuchElementException("Contrato Desconto não encontrado");
        }
    }

}
