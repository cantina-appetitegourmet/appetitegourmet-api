package br.com.appetitegourmet.api.services;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import br.com.appetitegourmet.api.models.*;
import br.com.appetitegourmet.api.spring.login.models.User;
import br.com.appetitegourmet.api.spring.login.payload.request.UserInfoRequest;
import br.com.appetitegourmet.api.spring.login.repository.UserRepository;
import br.com.appetitegourmet.api.spring.login.security.jwt.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.repositories.ContratoDescontoRepository;
import br.com.appetitegourmet.api.repositories.ContratoRepository;
import br.com.appetitegourmet.api.repositories.DescontoRepository;
import br.com.appetitegourmet.api.repositories.ResponsavelAlunoRepository;
import br.com.appetitegourmet.api.repositories.TurmaAnoLetivoRepository;
import utils.ValidacaoConstantes;

@Service
public class ContratoService {
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	UserRepository userRepository;

	@Autowired
	AssociacaoUsuarioService associacaoUsuarioService;

	private final ContratoRepository contratoRepository;
	private final ResponsavelAlunoRepository responsavelAlunoRepository;
	private final TurmaAnoLetivoRepository turmaAnoLetivoRepository;
	private final ContratoDescontoRepository contratoDescontoRepository;
	private final DescontoRepository descontoRepository;
	
	public ContratoService(TurmaAnoLetivoRepository turmaAnoLetivoRepository, ResponsavelAlunoRepository responsavelAlunoRepository, ContratoRepository contratoRepository, ContratoDescontoRepository contratoDescontoRepository, DescontoRepository descontoRepository) {
		this.contratoRepository = contratoRepository;
		this.responsavelAlunoRepository = responsavelAlunoRepository;
		this.turmaAnoLetivoRepository = turmaAnoLetivoRepository;
		this.contratoDescontoRepository = contratoDescontoRepository;
		this.descontoRepository = descontoRepository;
		
	}
	
	public List<Contrato> listarContratos() {
        return contratoRepository.findAll();
    }
	
	public List<Contrato> listarContratosResponsavel(Long id) {
        return contratoRepository.findAllByResponsavelId(id);
    }

	public List<Contrato> listarContratosResponsavel(HttpServletRequest request) {
		String jwt = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(jwt);
		Optional<User> user = userRepository.findByUsername(username);
		UserInfoRequest userInfo = new UserInfoRequest();
		userInfo.setId(user.get().getId());
		userInfo.setRole("ROLE_RESPONSAVEL");
		AssociacaoUsuario associacaoUsuario = associacaoUsuarioService.pegaAssociacaoUsuario(userInfo);

		return contratoRepository.findAllByResponsavelId(associacaoUsuario.getAssociado_id());
	}

    public Contrato buscarContratoPorId(Long id) {
        return contratoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Contrato não encontrado"));
    }

    public Contrato salvarContrato(HttpServletRequest request, Contrato contrato) {
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
    	contrato.setDataAdesao(new Date(System.currentTimeMillis()));
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
