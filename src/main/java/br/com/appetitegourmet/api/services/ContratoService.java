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
        return contratoRepository.save(contrato);
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
