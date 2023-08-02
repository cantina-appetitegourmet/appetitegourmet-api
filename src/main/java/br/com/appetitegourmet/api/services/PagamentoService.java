package br.com.appetitegourmet.api.services;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import br.com.appetitegourmet.api.models.Unidade;
import br.com.appetitegourmet.api.models.Contrato;
import br.com.appetitegourmet.api.models.ContratoPlano;
import br.com.appetitegourmet.api.models.Pagamento;
import br.com.appetitegourmet.api.models.Responsavel;
import br.com.appetitegourmet.api.models.ResponsavelAluno;
import br.com.appetitegourmet.api.exception.ErroCriacaoChavePixException;
import br.com.appetitegourmet.api.repositories.UnidadeRepository;
import br.com.appetitegourmet.api.repositories.ContratoPlanoRepository;
import br.com.appetitegourmet.api.repositories.ContratoRepository;
import br.com.appetitegourmet.api.repositories.PagamentoRepository;
import br.com.appetitegourmet.api.repositories.ResponsavelAlunoRepository;
import br.com.appetitegourmet.api.repositories.ResponsavelRepository;
import utils.RetornoString;
import utils.ValidacaoConstantes;
import utils.pagamentos.gerencianet.Cliente;
import utils.pagamentos.gerencianet.ItemPedido;
import utils.pagamentos.gerencianet.Metadata;
import utils.pagamentos.gerencianet.Multa;
import utils.pagamentos.gerencianet.OperacoesBoleto;
import utils.pagamentos.gerencianet.OperacoesPix;

@Service
public class PagamentoService {
	
	private final ResponsavelRepository responsavelRepository;
	private final ContratoRepository contratoRepository;
	private final ContratoPlanoRepository contratoPlanoRepository;
	private final PagamentoRepository pagamentoRepository;
	private final ResponsavelAlunoRepository responsavelAlunoRepository;
	@Autowired
    private Environment env;
	
	public PagamentoService(ResponsavelRepository responsavelRepository, 
			                UnidadeRepository unidadeRepository, ContratoRepository contratoRepository, ContratoPlanoRepository contratoPlanoRepository, PagamentoRepository pagamentoRepository, ResponsavelAlunoRepository responsavelAlunoRepository) {
		this.responsavelRepository = responsavelRepository;
		this.contratoRepository = contratoRepository;
		this.contratoPlanoRepository = contratoPlanoRepository;
		this.pagamentoRepository = pagamentoRepository;
		this.responsavelAlunoRepository = responsavelAlunoRepository;
		
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
	
	public String pixCobrancaImediataSemTxid(Long idContrato) {
		RetornoString dados = new RetornoString();
		boolean retorno;
		OperacoesPix operacoesPix;
		int expiracao = 3600*24*10;
		String cpf = null; 
		String nome = null;
		String valor = null; 
		String val = null;
		String chave = null;
		String solicitacao = null;
		Contrato contrato;
		Optional<Contrato> optContrato;
		List<ContratoPlano> listaContratoPlano;
		BigDecimal total;
		Pagamento pagamento;
		Integer idPix;
		JSONObject dadosRetorno;
		String retornoFinal;
		ContratoPlano contratoPlano;
		Optional<ResponsavelAluno> optRespAluno;
		ResponsavelAluno respAluno;
		
		optContrato = contratoRepository.findById(idContrato);
		if(!optContrato.isPresent()) {
			// @todo levantar excessao
		}
		contrato = optContrato.get();
		
		cpf = contrato.getResponsavelAluno().getResponsavel().getPessoa().getCpf();
		nome = contrato.getResponsavelAluno().getResponsavel().getPessoa().getNomeCompleto();
		
		listaContratoPlano = contratoPlanoRepository.findByContratoId(contrato.getId());
		if(listaContratoPlano.isEmpty()) {
			// @todo levantar excessao
			return "NOK";
		}
		
		total = ValidacaoConstantes.totalizaContratoPlano(listaContratoPlano);
		
		
		pagamento = new Pagamento();
		pagamento.setContrato(contrato);
		pagamento.setIdEmpresaIntegracao(ValidacaoConstantes.EMPRESA_GERENCIANET);
		pagamento.setIdMeioPagamento(ValidacaoConstantes.PAGAMENTO_TIPO_PIX);		
		pagamento.setIdStatus(ValidacaoConstantes.STATUS_GERADO);
		pagamento.setValor(total);
		
		total = total.multiply(new BigDecimal("100"));
		val = Integer.toString(total.intValue());
		
		System.out.println("Total = " + val);
		
		valor = val.substring(0, val.length() - 2) + "." + val.substring(val.length() - 2, val.length()); 
		
		chave = contrato.getUnidade().getEmpresa().getChavePix();
		
		solicitacao = "Pagamento Adesão";
		
		
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
		
		System.out.println("Retorno = " + dados.getRetornoString());
		pagamento.setDados(dados.getRetornoString());
		pagamentoRepository.save(pagamento);
		
		dadosRetorno = new JSONObject(dados.getRetornoString());
		
		idPix = dadosRetorno.getInt("id");
		
		dados = new RetornoString();
		operacoesPix = new OperacoesPix();
		retorno = operacoesPix.criarQrCode(dados, idPix);
		
		if(!retorno) {
			throw new ErroCriacaoChavePixException(operacoesPix.getErro());
		}
		
		retornoFinal = dados.getRetornoString();
		
		return retornoFinal; 
	}
	
	public String pixCriarQrCode(Integer id) {
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
	
	public String boletoGerarBoleto(Long idContrato) {
		RetornoString dados = new RetornoString();
		boolean retorno;
		OperacoesBoleto operacoesBoleto;
		ItemPedido item;
		List<ItemPedido> itens;
        Cliente cliente;
        String dataExpiracao;
        Metadata metadata;
        Multa multa;
        String mensagem;
        BigDecimal valorItem;
        Contrato contrato;
		Optional<Contrato> optContrato;
		List<ContratoPlano> listaContratoPlano;
		BigDecimal total;
		Pagamento pagamento;
		Pagamento pagamentoBD;
		ContratoPlano contratoPlano;
		JSONObject dadosRetorno;
		JSONObject data;
		
		optContrato = contratoRepository.findById(idContrato);
		if(!optContrato.isPresent()) {
			// @todo levantar excessao
		}
		contrato = optContrato.get();
        
        Responsavel responsavel = null;
        Optional<Responsavel> optResponsavel = responsavelRepository.findById(contrato.getResponsavelAluno().getResponsavel().getId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String urlNotification;
        boolean metadataPresente = false;
        Unidade unidade = null;
        BigDecimal valorMulta;
        BigDecimal valorJurosDias;
                
        itens = new ArrayList<ItemPedido>();
        item = new ItemPedido();
        item.setDescricao("Pagamento Adesão");
        item.setQuantidade(1);
        
        listaContratoPlano = contratoPlanoRepository.findByContratoId(contrato.getId());
		if(listaContratoPlano.isEmpty()) {
			// @todo levantar excessao
		}
		
		contratoPlano = new ContratoPlano();
		total = ValidacaoConstantes.totalizaContratoPlano(listaContratoPlano);
        
        valorItem = total.multiply(new BigDecimal("100.0"));
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
        
        LocalDate hoje = LocalDate.now();
        LocalDate amanha = hoje.plusDays(15);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        dataExpiracao = amanha.format(formatters);
        
        pagamento = new Pagamento();
		pagamento.setContrato(contrato);
		pagamento.setIdEmpresaIntegracao(ValidacaoConstantes.EMPRESA_GERENCIANET);
		pagamento.setIdMeioPagamento(ValidacaoConstantes.PAGAMENTO_TIPO_BOLETO);		
		pagamento.setIdStatus(ValidacaoConstantes.STATUS_GERADO);
		pagamento.setValor(total);
		pagamento.setDados(" ");		
		pagamentoBD = pagamentoRepository.save(pagamento);
        
        metadata = new Metadata();
        if(pagamentoBD.getId() != null) {
        	metadata.setIdBoleto(pagamentoBD.getId().toString());
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
        
        unidade = contrato.getUnidade();
        /*
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
        */
        
        multa = new Multa();
        valorMulta = unidade.getValorMultaBoleto().multiply(new BigDecimal("100.0"));
        multa.setMulta(valorMulta.longValue());
        valorJurosDias = unidade.getValorJurosDiaBoleto().multiply(new BigDecimal("100.0"));
        multa.setJurosDia(valorJurosDias.longValue());
        
        mensagem = "Pagamento Adesao";
		
		operacoesBoleto = new OperacoesBoleto();
		retorno = operacoesBoleto.gerarBoleto(dados, 
											  itens,
								              cliente,
								              dataExpiracao,
								              metadata,
								              null,
								              null,
								              multa,
								              mensagem);
		if(!retorno) {
			throw new ErroCriacaoChavePixException(operacoesBoleto.getErro());
		}
		
		dadosRetorno = new JSONObject(dados.getRetornoString());
		
		data = dadosRetorno.getJSONObject("data");
		
		pagamentoBD.setDados(Long.toString(data.getLong("charge_id")));
		pagamentoBD = pagamentoRepository.save(pagamentoBD);
		
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
