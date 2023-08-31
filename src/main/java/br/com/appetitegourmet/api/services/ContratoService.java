package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Contrato;
import br.com.appetitegourmet.api.models.ResponsavelAluno;
import br.com.appetitegourmet.api.models.TurmaAnoLetivo;
import br.com.appetitegourmet.api.repositories.ContratoRepository;
import br.com.appetitegourmet.api.repositories.ResponsavelAlunoRepository;
import br.com.appetitegourmet.api.repositories.TurmaAnoLetivoRepository;

@Service
public class ContratoService {

	private final ContratoRepository contratoRepository;
	private final ResponsavelAlunoRepository responsavelAlunoRepository;
	private final TurmaAnoLetivoRepository turmaAnoLetivoRepository;
	
	public ContratoService(TurmaAnoLetivoRepository turmaAnoLetivoRepository, ResponsavelAlunoRepository responsavelAlunoRepository, ContratoRepository contratoRepository) {
		this.contratoRepository = contratoRepository;
		this.responsavelAlunoRepository = responsavelAlunoRepository;
		this.turmaAnoLetivoRepository = turmaAnoLetivoRepository;
		
	}
	
	public List<Contrato> listarContratos() {
        return contratoRepository.findAll();
    }

    public Contrato buscarContratoPorId(Long id) {
        return contratoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Contrato não encontrado"));
    }

    public Contrato salvarContrato(Contrato contrato) {
    	Contrato retContrato;
    	List<Contrato> contratos;
    	
    	if(contrato.getResponsavelAluno() != null) {
    		ResponsavelAluno retorno;
    		Optional<ResponsavelAluno> opt;
    		opt = responsavelAlunoRepository.findById(contrato.getResponsavelAluno().getId());
    		if(!opt.isPresent()) {
    			new Exception("Falha ao consultar o responsavel_aluno");
    		}
    		retorno = opt.get();
    		contrato.setResponsavelAluno(retorno);
    	}
    	if(contrato.getTurmaAnoLetivo() != null) {
    		TurmaAnoLetivo retorno;
    		Optional<TurmaAnoLetivo> opt;
    		opt = turmaAnoLetivoRepository.findById(contrato.getTurmaAnoLetivo().getId());
    		if(!opt.isPresent()) {
    			new Exception("Falha ao consultar o turma_ano_letivo");
    		}
    		retorno = opt.get();
    		contrato.setTurmaAnoLetivo(retorno);
    	}
    	retContrato = contratoRepository.save(contrato);
    	contratos = contratoRepository.findAllByResponsavelId(retContrato.getResponsavelAluno().getResponsavel().getId());
    	if(contratos != null && contratos.size() > 1) {
    		for(Contrato contratoBD : contratos) {
	    		Boolean encontrou = false;
	    		List<ContratoDesconto> lista = contratoDescontoRepository.findByContratoId(contratoBD.getId());
	    		if(lista.size() > 0) {
	    			for(ContratoDesconto cd: lista) {
	    				if(cd.getDesconto().getTipo() == ValidacaoConstantes.DESCONTO_2_OU_MAIS_FILHOS) {
	    					encontrou = true;
	    					break;
	    				}
	    			}
	    		}
	    		if(!encontrou) {
	    			ContratoDesconto cd = new ContratoDesconto();
	    			cd.setContrato(contratoBD);
	    			cd.setDataInicio(contratoBD.getDataAdesao());
	    			List<Desconto> descontos = descontoRepository.findAll();
	    			for(Desconto des : descontos) {
	    				if(des.getTipo() == ValidacaoConstantes.DESCONTO_2_OU_MAIS_FILHOS) {
	    					cd.setDesconto(des);
	    				}
	    			}
	    			contratoDescontoRepository.save(cd);
	    		}
    		}
    	}
        return retContrato;
    }

    public void excluirContrato(Long id) {
        contratoRepository.deleteById(id);
    }

    public Contrato editarContrato(Long id, Contrato contrato) {
        Optional<Contrato> optionalContrato = contratoRepository.findById(id);
        if (optionalContrato.isPresent()) {
            Contrato novoContrato = optionalContrato.get();
            if(contrato.getDataAdesao() != null) {
            	novoContrato.setDataAdesao(contrato.getDataAdesao());
            }
            return contratoRepository.save(novoContrato);
        } else {
        	throw new NoSuchElementException("Contrato não encontrado");
        }
    }
}
