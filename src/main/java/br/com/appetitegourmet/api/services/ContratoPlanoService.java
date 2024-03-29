package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import br.com.appetitegourmet.api.dto.ContratoPlanoReq;
import br.com.appetitegourmet.api.dto.PessoaResponse;
import br.com.appetitegourmet.api.mapper.ContratoPlanoMapper;
import br.com.appetitegourmet.api.mapper.PessoaMapper;
import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Contrato;
import br.com.appetitegourmet.api.models.ContratoPlano;
import br.com.appetitegourmet.api.models.PlanoAlimentarPreco;
import br.com.appetitegourmet.api.repositories.ContratoPlanoRepository;
import br.com.appetitegourmet.api.repositories.ContratoRepository;
import br.com.appetitegourmet.api.repositories.PlanoAlimentarPrecoRepository;

@Service
public class ContratoPlanoService {
	
	private final ContratoPlanoRepository contratoPlanoRepository;
	private final ContratoRepository contratoRepository;
	private final PlanoAlimentarPrecoRepository planoAlimentarPrecoRepository;
	
	public ContratoPlanoService(ContratoPlanoRepository contratoPlanoRepository, 
			ContratoRepository contratoRepository, 
			PlanoAlimentarPrecoRepository planoAlimentarPrecoRepository) {
		this.contratoPlanoRepository = contratoPlanoRepository;
		this.contratoRepository = contratoRepository;
		this.planoAlimentarPrecoRepository = planoAlimentarPrecoRepository;
		
	}
	
	public List<ContratoPlano> listarContratoPlanos() {
        return contratoPlanoRepository.findAll();
    }

    public ContratoPlano buscarContratoPlanoPorId(Long id) {
        return contratoPlanoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Contrato Plano não encontrado"));
    }
    
    public List<ContratoPlano> buscarContratoPlanoPorContratoId(Long id) {
        return contratoPlanoRepository.findByContratoId(id);
    }

    public ContratoPlano salvarContratoPlano(ContratoPlanoReq contratoPlano) {
        return contratoPlanoRepository.save(ContratoPlanoMapper.INSTANCE.contratoPlanoReqtoContratoPlano(contratoPlano));
    }

    public void excluirContratoPlano(Long id) {
        contratoPlanoRepository.deleteById(id);
    }

    public ContratoPlano editarContratoPlano(Long id, ContratoPlano contratoPlano) {
        Optional<ContratoPlano> optionalContratoPlano = contratoPlanoRepository.findById(id);
        if (optionalContratoPlano.isPresent()) {
            ContratoPlano novoContratoPlano = optionalContratoPlano.get();
            
            if(contratoPlano.getDataInicio() != null) {
            	novoContratoPlano.setDataInicio(contratoPlano.getDataInicio());
            }
            if(contratoPlano.getDataFim() != null) {
            	novoContratoPlano.setDataFim(contratoPlano.getDataFim());
            }
            if(contratoPlano.getPrecoDia() != null) {
            	novoContratoPlano.setPrecoDia(contratoPlano.getPrecoDia());
            }
            if(contratoPlano.getSegunda() != null) {
            	novoContratoPlano.setSegunda(contratoPlano.getSegunda());
            }
            if(contratoPlano.getTerca() != null) {
            	novoContratoPlano.setTerca(contratoPlano.getTerca());
            }
            if(contratoPlano.getQuarta() != null) {
            	novoContratoPlano.setQuarta(contratoPlano.getQuarta());
            }
            if(contratoPlano.getQuinta() != null) {
            	novoContratoPlano.setQuinta(contratoPlano.getQuinta());
            }
            if(contratoPlano.getSexta() != null) {
            	novoContratoPlano.setSexta(contratoPlano.getSexta());
            }
            if(contratoPlano.getSabado() != null) {
            	novoContratoPlano.setSabado(contratoPlano.getSabado());
            }
            if(contratoPlano.getDomingo() != null) {
            	novoContratoPlano.setDomingo(contratoPlano.getDomingo());
            }
            
            return contratoPlanoRepository.save(novoContratoPlano);
        } else {
        	throw new NoSuchElementException("Contrato Plano não encontrado");
        }
    }

}
