package br.com.appetitegourmet.api.services;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.exception.ErroCriacaoChavePixException;
import utils.pagamentos.gerencianet.ChavePix;

@Service
public class PagamentoService {

	public String listarChavesPix() {
		String dados = "";
		boolean retorno;
		ChavePix chavePix;
		
		chavePix = new ChavePix();
		retorno = chavePix.listar(dados);
		if(!retorno) {
			throw new ErroCriacaoChavePixException(chavePix.getErro());
		}
		return dados;
	}
	
	public String criarChavePix() {
		String dados = "";
		boolean retorno;
		ChavePix chavePix;
		
		chavePix = new ChavePix();
		retorno = chavePix.criar(dados);
		if(!retorno) {
			throw new ErroCriacaoChavePixException(chavePix.getErro());
		}
		return dados;
	}
}
