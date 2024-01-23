package br.com.appetitegourmet.api.controllers;

import java.math.BigDecimal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.appetitegourmet.api.services.CalendarioService;

@RestController
@RequestMapping("/calendarios")
@CrossOrigin(origins = "http://localhost:4200,https://nice-beach-01dafa610.3.azurestaticapps.net", maxAge = 3600, allowCredentials="true")
public class CalendarioController {

	private final CalendarioService calendarioService;
    
    public CalendarioController(CalendarioService calendarioService) {
        this.calendarioService = calendarioService;
    }
    
    @GetMapping("/preco2/{dataInicial}/{dataFinal}/{seg}/{ter}/{qua}/{qui}/{sex}/{sab}/{dom}/{plano_alimentar_precoId}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public BigDecimal calculaTotalMes2(@PathVariable String dataInicial,
    								   @PathVariable String dataFinal,
    								   @PathVariable boolean seg, 
    								   @PathVariable boolean ter, 
    								   @PathVariable boolean qua, 
    								   @PathVariable boolean qui, 
    								   @PathVariable boolean sex,
    								   @PathVariable boolean sab,
    								   @PathVariable boolean dom,
    								   @PathVariable Long plano_alimentar_precoId) {
        return calendarioService.calculaTotalMes2(dataInicial,
        										  dataFinal,
        										  seg,
        										  ter,
        										  qua,
        										  qui,
        										  sex,
        										  sab,
        	    								  dom,
        	    								  plano_alimentar_precoId);
    }
}
