package br.com.appetitegourmet.api.controllers;

import java.util.Map;
import br.com.appetitegourmet.api.exception.ErroParametroObrigatorioException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.appetitegourmet.api.dto.BoletoGerarBoletoRequest;
import br.com.appetitegourmet.api.dto.PixCobrancaImediataSemTxidRequest;
import br.com.appetitegourmet.api.services.PagamentoService;

@RestController
//@RequestMapping("/pagamentos")
public class PagamentoController {

	private final PagamentoService pagamentoService;
	
	public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }
	
	@GetMapping
	@RequestMapping("/pagamentos/listarChavesPix")
    public String listarChavesPix() {
        return pagamentoService.listarChavesPix();
    }
	
	@PostMapping
	@RequestMapping("/pagamentos/criarChavePix")
    public String criarChavePix() {
        return pagamentoService.criarChavePix();
    }
	
	@DeleteMapping
	@RequestMapping("/pagamentos/removerChavePix/{chave}")
    public String removerChavePix(@PathVariable String chave) {
        return pagamentoService.removerChavePix(chave);
    }
	
	@PostMapping
	@RequestMapping("/pagamentos/pixCobrancaImediataSemTxid")
    public String pixCobrancaImediataSemTxid(@RequestBody PixCobrancaImediataSemTxidRequest request) {
        return pagamentoService.pixCobrancaImediataSemTxid(request.getCpf(), 
        												   request.getNome(), 
        												   request.getValor(),
        												   request.getChave(),
        												   request.getSolicitacao());
    }
	
	@GetMapping
	@RequestMapping("/pagamentos/pixListaCobrancas")
    public String pixListaCobrancas(@RequestParam Map<String, String> query) {
		String  sDtInicial = null; 
		String sDtFinal = null; 
		String cpf = null;
		String cnpj = null;
		String status = null;
		Integer paginaAtual = null;
		Integer itensPorPagina = null;
		
		if(query.containsKey("dtInicial")) {
			sDtInicial = query.get("dtInicial");
		} else {
			throw new ErroParametroObrigatorioException("dtInicial");
		}
		
		if(query.containsKey("dtFinal")) {
			sDtFinal = query.get("dtFinal");
		} else {
			throw new ErroParametroObrigatorioException("dtFinal");
		}
		
		if(query.containsKey("cpf")) {
			cpf = query.get("cpf");
		}
		
		if(query.containsKey("cnpj")) {
			cnpj = query.get("cnpj");
		}
		
		if(query.containsKey("status")) {
			status = query.get("status");
		}
		
		if(query.containsKey("paginaAtual")) {
			paginaAtual = Integer.parseInt(query.get("paginaAtual"));
		}
		
		if(query.containsKey("itensPorPagina")) {
			itensPorPagina = Integer.parseInt(query.get("itensPorPagina"));
		}
		
        return pagamentoService.pixListarCobrancas(sDtInicial, 
        										   sDtFinal, 
        										   cpf, 
        										   cnpj, 
        										   status, 
        										   paginaAtual, 
        										   itensPorPagina);
    }
	
	@GetMapping
	@RequestMapping("/pagamentos/pixExibirCobranca")
    public String pixExibirCobranca(@RequestParam Map<String, String> query) {
		String  txid = null; 
		
		if(query.containsKey("txid")) {
			txid = query.get("txid");
		} else {
			throw new ErroParametroObrigatorioException("txid");
		}
		
		
        return pagamentoService.pixExibirCobranca(txid);
    }
	
	@PostMapping
	@RequestMapping("/pagamentos/boletoGerarBoleto")
    public String boletoGerarBoleto(@RequestBody BoletoGerarBoletoRequest request) {
        return pagamentoService.boletoGerarBoleto(request);
    }
	
	@DeleteMapping
	@RequestMapping("/pagamentos/boletoCancelarBoleto/{id}")
    public String boletoCancelarBoleto(@PathVariable String id) {
        return pagamentoService.boletoCancelarBoleto(id);
    }
	
	@GetMapping
	@RequestMapping("/pagamentos/boletoExibirBoleto/{id}")
    public String boletoExibirBoleto(@PathVariable String id) {
        return pagamentoService.boletoExibirBoleto(id);
    }
}
