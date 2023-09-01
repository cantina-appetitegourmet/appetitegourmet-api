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
    
    public List<ContratoDesconto> buscarContratoDescontoPorContratoId(Long id) {
        return contratoDescontoRepository.findByContratoId(id);
    }

    public ContratoDesconto salvarContratoDesconto(ContratoDesconto contratoDesconto) {
    	if(contratoDesconto.getContrato() != null) {
    		if(contratoDesconto.getContrato().getId() != null) {
    			Optional<Contrato> opt;
    			opt = contratoRepository.findById(contratoDesconto.getContrato().getId());
    			if(opt.isPresent()) {
		    		contratoDesconto.setContrato(opt.get());
    			} else {
            		new Exception("Contrato inválido");
            	}
    		} else {
        		new Exception("Contrato inválido");
        	}
    	} else {
    		new Exception("Contrato inválido");
    	}
    	
    	if(contratoDesconto.getDesconto() != null) {
    		if(contratoDesconto.getDesconto().getId() != null) {
    			Optional<Desconto> opt;
    			opt = descontoRepository.findById(contratoDesconto.getDesconto().getId());
    			if(opt.isPresent()) {
		    		contratoDesconto.setDesconto(opt.get());
    			} else {
            		new Exception("Desconto inválido");
            	}
    		} else {
        		new Exception("Desconto inválido");
        	}
    	} else {
    		new Exception("Desconto inválido");
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
