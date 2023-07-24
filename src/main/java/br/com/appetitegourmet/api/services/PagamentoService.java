package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import br.com.appetitegourmet.api.models.Unidade;
import br.com.appetitegourmet.api.models.Responsavel;

import br.com.appetitegourmet.api.dto.BoletoGerarBoletoRequest;
import br.com.appetitegourmet.api.exception.ErroCriacaoChavePixException;
import br.com.appetitegourmet.api.repositories.UnidadeRepository;
import br.com.appetitegourmet.api.repositories.ResponsavelRepository;
import utils.RetornoString;
import utils.ValidacaoConstantes;
import utils.pagamentos.gerencianet.Cliente;
import utils.pagamentos.gerencianet.Desconto;
import utils.pagamentos.gerencianet.DescontoCondicional;
import utils.pagamentos.gerencianet.ItemPedido;
import utils.pagamentos.gerencianet.Metadata;
import utils.pagamentos.gerencianet.Multa;
import utils.pagamentos.gerencianet.OperacoesBoleto;
import utils.pagamentos.gerencianet.OperacoesPix;

@Service
public class PagamentoService {
	
	private final ResponsavelRepository responsavelRepository;
	private final UnidadeRepository unidadeRepository;
	@Autowired
    private Environment env;
	
	public PagamentoService(ResponsavelRepository responsavelRepository, 
			                UnidadeRepository unidadeRepository) {
		this.responsavelRepository = responsavelRepository;
		this.unidadeRepository = unidadeRepository;
		
	}

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
	
	public String boletoGerarBoleto(BoletoGerarBoletoRequest request) {
		RetornoString dados = new RetornoString();
		boolean retorno;
		OperacoesBoleto operacoesBoleto;
		ItemPedido item;
		List<ItemPedido> itens;
        Cliente cliente;
        String dataExpiracao;
        Metadata metadata;
        Desconto desconto;
        DescontoCondicional condicional;
        Multa multa;
        String mensagem;
        BigDecimal valorItem;
        
        Responsavel responsavel = null;
        Optional<Responsavel> optResponsavel = responsavelRepository.findById(request.getIdResonsavel());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String urlNotification;
        boolean metadataPresente = false;
        Unidade unidade = null;
        Optional<Unidade> optUnidade;
        String tipoDesconto;
        String tipoDescontoCondicional;
        BigDecimal valorDesconto;
        BigDecimal valorDescontoCondicional;
        BigDecimal valorMulta;
        BigDecimal valorJurosDias;
                
        itens = new ArrayList<ItemPedido>();
        item = new ItemPedido();
        item.setDescricao(request.getDescricao());
        item.setQuantidade(1);
        valorItem = request.getValor().multiply(new BigDecimal("100.0"));
        item.setValor(valorItem.longValue());
        itens.add(item);
        
        if(optResponsavel.isPresent()) {
        	responsavel = optResponsavel.get(); 
        } else {
        	// @todo levantar excessao
        }
        cliente = new Cliente();
        cliente.setTipoPessoa(Cliente.PESSOA_FISICA);
        cliente.setBairro(responsavel.getPessoa().getEndereco().getBairro());
        cliente.setCep(responsavel.getPessoa().getEndereco().getCep());
        cliente.setCidade(responsavel.getPessoa().getEndereco().getCidade());
        cliente.setComplemento(responsavel.getPessoa().getEndereco().getComplemento());
        cliente.setCpf(responsavel.getPessoa().getCpf());
        if(responsavel.getPessoa().getNascimento() != null) {
        	cliente.setDataNascimento(simpleDateFormat.format(responsavel.getPessoa().getNascimento()));
        }
        cliente.setEmail(responsavel.getPessoa().getEmail());
        cliente.setEstado(responsavel.getPessoa().getEndereco().getUf());
        cliente.setNome(responsavel.getPessoa().getNomeCompleto());
        cliente.setNumero(responsavel.getPessoa().getEndereco().getNumero().toString());
        cliente.setRua(responsavel.getPessoa().getEndereco().getLogradouro());
        cliente.setTelefone(responsavel.getPessoa().getTelefone());
        
        dataExpiracao = simpleDateFormat.format(request.getDataExpiracao());
        
        metadata = new Metadata();
        if(request.getIdPagamento() != null) {
        	metadata.setIdBoleto(request.getIdPagamento().toString());
        	metadataPresente = true;
        }
        urlNotification = env.getProperty("urlNotification");
        if(urlNotification != null) {
        	metadata.setUrlNotificacao(urlNotification);
        	metadataPresente = true;
        }        
        if(!metadataPresente) {
        	metadata = null;
        }
        
        optUnidade = unidadeRepository.findById(request.getIdUnidade());
        if(optUnidade.isPresent()) {
        	unidade = optUnidade.get();
        } else {
        	// @todo throw 
        }
        
        desconto = new Desconto();
        if(unidade.getTipoDescontoBoleto()==ValidacaoConstantes.TP_DESCONTO_VALOR) {
        	tipoDesconto = "currency";
        } else {
        	tipoDesconto = "percentage";
        }
        desconto.setTipo(tipoDesconto);
        valorDesconto = unidade.getValorDescontoBoleto().multiply(new BigDecimal("100.0"));
        desconto.setValor(valorDesconto.longValue());
        
        condicional = new DescontoCondicional();
        if(unidade.getTipoDescontoBoleto()==ValidacaoConstantes.TP_DESCONTO_VALOR) {
        	tipoDescontoCondicional = "currency";
        } else {
        	tipoDescontoCondicional = "percentage";
        }
        condicional.setTipo(tipoDescontoCondicional);
        valorDescontoCondicional = unidade.getValorDescontoCondicionalBoleto().multiply(new BigDecimal("100.0"));
        condicional.setValor(valorDescontoCondicional.longValue());
        condicional.setDataMaxima(simpleDateFormat.format(request.getDataMaximaDescontoCondicionalBoleto()));
        
        multa = new Multa();
        valorMulta = unidade.getValorMultaBoleto().multiply(new BigDecimal("100.0"));
        multa.setMulta(valorMulta.longValue());
        valorJurosDias = unidade.getValorJurosDiaBoleto().multiply(new BigDecimal("100.0"));
        multa.setJurosDia(valorJurosDias.longValue());
        
        mensagem = request.getMensagem();
		
		operacoesBoleto = new OperacoesBoleto();
		retorno = operacoesBoleto.gerarBoleto(dados, 
											  itens,
								              cliente,
								              dataExpiracao,
								              metadata,
								              desconto,
								              condicional,
								              multa,
								              mensagem);
		if(!retorno) {
			throw new ErroCriacaoChavePixException(operacoesBoleto.getErro());
		}
		return dados.getRetornoString();
	}
	
	public String boletoCancelarBoleto(String idBoleto) {
		RetornoString dados = new RetornoString();
		boolean retorno;
		OperacoesBoleto operacoesBoleto;
		
		operacoesBoleto = new OperacoesBoleto();
		retorno = operacoesBoleto.cancelarBoleto(dados, 
												 idBoleto);  
		if(!retorno) {
			throw new ErroCriacaoChavePixException(operacoesBoleto.getErro());
		}
		return dados.getRetornoString();
	}
	
	
	public String boletoExibirBoleto(String idBoleto) {
		RetornoString dados = new RetornoString();
		boolean retorno;
		OperacoesBoleto operacoesBoleto;
		
		operacoesBoleto = new OperacoesBoleto();
		retorno = operacoesBoleto.exibirBoleto(dados, 
											   idBoleto);  
		if(!retorno) {
			throw new ErroCriacaoChavePixException(operacoesBoleto.getErro());
		}
		return dados.getRetornoString();
	}

}
