package br.com.appetitegourmet.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
