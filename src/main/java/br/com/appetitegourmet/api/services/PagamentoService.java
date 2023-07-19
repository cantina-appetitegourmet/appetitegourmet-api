package br.com.appetitegourmet.api.services;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.exception.ErroCriacaoChavePixException;
import utils.RetornoString;
import utils.pagamentos.gerencianet.ChavePix;

@Service
public class PagamentoService {

	public String listarChavesPix() {
		RetornoString dados = new RetornoString();
		boolean retorno;
		ChavePix chavePix;
		
		chavePix = new ChavePix();
		retorno = chavePix.listar(dados);
		if(!retorno) {
			throw new ErroCriacaoChavePixException(chavePix.getErro());
		}
		return dados.getRetornoString();
	}
	
	public String criarChavePix() {
		RetornoString dados = new RetornoString();
		boolean retorno;
		ChavePix chavePix;
		
		chavePix = new ChavePix();
		retorno = chavePix.criar(dados);
		if(!retorno) {
			throw new ErroCriacaoChavePixException(chavePix.getErro());
		}
		return dados.getRetornoString();
	}
	
	public String removerChavePix(String chave) {
		RetornoString dados = new RetornoString();
		boolean retorno;
		ChavePix chavePix;
		
		chavePix = new ChavePix();
		retorno = chavePix.remover(dados, chave);
		if(!retorno) {
			throw new ErroCriacaoChavePixException(chavePix.getErro());
		}
		return dados.getRetornoString();
	}
}
