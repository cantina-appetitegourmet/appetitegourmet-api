package br.com.appetitegourmet.api.services;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.exception.ErroCriacaoChavePixException;
import utils.RetornoString;
import utils.pagamentos.gerencianet.OperacoesPix;

@Service
public class PagamentoService {

	public String listarChavesPix() {
		RetornoString dados = new RetornoString();
		boolean retorno;
		OperacoesPix operacoesPix;
		
		operacoesPix = new OperacoesPix();
		retorno = operacoesPix.listarChaves(dados);
		if(!retorno) {
			throw new ErroCriacaoChavePixException(operacoesPix.getErro());
		}
		return dados.getRetornoString();
	}
	
	public String criarChavePix() {
		RetornoString dados = new RetornoString();
		boolean retorno;
		OperacoesPix operacoesPix;
		
		operacoesPix = new OperacoesPix();
		retorno = operacoesPix.criarChave(dados);
		if(!retorno) {
			throw new ErroCriacaoChavePixException(operacoesPix.getErro());
		}
		return dados.getRetornoString();
	}
	
	public String removerChavePix(String chave) {
		RetornoString dados = new RetornoString();
		boolean retorno;
		OperacoesPix operacoesPix;
		
		operacoesPix = new OperacoesPix();
		retorno = operacoesPix.removerChave(dados, chave);
		if(!retorno) {
			throw new ErroCriacaoChavePixException(operacoesPix.getErro());
		}
		return dados.getRetornoString();
	}
	
	public String pixCobrancaImediataSemTxid(String cpf, 
											 String nome, 
											 String valor, 
											 String chave,
											 String solicitacao) {
		RetornoString dados = new RetornoString();
		boolean retorno;
		OperacoesPix operacoesPix;
		int expiracao = 3600*24*10;
		
		operacoesPix = new OperacoesPix();
		retorno = operacoesPix.criarCobrancaImediataSemTxid(dados, 
															expiracao, 
															cpf, 
															nome, 
															valor, 
															chave, 
															solicitacao);  
		if(!retorno) {
			throw new ErroCriacaoChavePixException(operacoesPix.getErro());
		}
		return dados.getRetornoString();
	}
	
	public String criarQrCode(Integer id) {
		RetornoString dados = new RetornoString();
		boolean retorno;
		OperacoesPix operacoesPix;
		
		operacoesPix = new OperacoesPix();
		retorno = operacoesPix.criarQrCode(dados, id);  
		if(!retorno) {
			throw new ErroCriacaoChavePixException(operacoesPix.getErro());
		}
		return dados.getRetornoString();
	}
	
	public String pixListarCobrancas(String sDtInicial, 
									 String sDtFinal, 
									 String cpf, 
									 String cnpj,
									 String status,
									 Integer paginaAtual,
									 Integer itensPorPagina) {
		RetornoString dados = new RetornoString();
		boolean retorno;
		OperacoesPix operacoesPix;
		
		operacoesPix = new OperacoesPix();
		retorno = operacoesPix.listarCobrancas(dados, 
											   sDtInicial, 
											   sDtFinal, 
											   cpf, 
											   cnpj, 
											   status, 
											   paginaAtual,
											   itensPorPagina);  
		if(!retorno) {
			throw new ErroCriacaoChavePixException(operacoesPix.getErro());
		}
		return dados.getRetornoString();
	}
	
	public String pixExibirCobranca(String txid) {
		RetornoString dados = new RetornoString();
		boolean retorno;
		OperacoesPix operacoesPix;
		
		operacoesPix = new OperacoesPix();
		retorno = operacoesPix.exibirCobranca(dados, 
							   				  txid);  
		if(!retorno) {
			throw new ErroCriacaoChavePixException(operacoesPix.getErro());
		}
		return dados.getRetornoString();
	}
}
