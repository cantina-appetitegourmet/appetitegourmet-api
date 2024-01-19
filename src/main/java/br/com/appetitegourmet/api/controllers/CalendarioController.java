package br.com.appetitegourmet.api.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.appetitegourmet.api.models.AnoLetivo;
import br.com.appetitegourmet.api.models.Calendario;
import br.com.appetitegourmet.api.services.CalendarioService;

@RestController
@RequestMapping("/calendarios")
@CrossOrigin(origins = "http://localhost:4200,https://nice-beach-01dafa610.3.azurestaticapps.net", maxAge = 3600, allowCredentials="true")
public class CalendarioController {

	private final CalendarioService calendarioService;
    
    public CalendarioController(CalendarioService calendarioService) {
        this.calendarioService = calendarioService;
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public List<Calendario> listarCalendarios() {
        return calendarioService.listarCalendarios();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public Calendario buscarCalendarioPorId(@PathVariable Long id) {
        return calendarioService.buscarCalendarioPorId(id);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public Calendario salvarCalendario(@RequestBody Calendario calendario) {
        return calendarioService.salvarCalendario(calendario);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void excluirCalendario(@PathVariable Long id) {
        calendarioService.excluirCalendario(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void editarCalendario(@PathVariable Long id, @RequestBody Calendario calendario) {
        calendarioService.editarCalendario(id, calendario);
    }
    
    @PostMapping("/criarGeral")
    // @todo remover responsavel quando criar usuario admin
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public void criarCalendario(@RequestBody AnoLetivo anoLetivo) {
        calendarioService.criarCalendarioGeral(anoLetivo);
    }
    
    @GetMapping("/preco/{cidadeId}/{mes}/{anoLetivoId}/{valorDia}/{seg}/{ter}/{qua}/{qui}/{sex}/{sab}/{dom}/{unidadeId}/{turmaId}/{serieId}/{plano_alimentarId}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public BigDecimal calculaTotalMes(@PathVariable Long cidadeId,
    								  @PathVariable int mes, 
    								  @PathVariable Long anoLetivoId, 
    								  @PathVariable BigDecimal valorDia, 
    								  @PathVariable boolean seg, 
    								  @PathVariable boolean ter, 
    								  @PathVariable boolean qua, 
    								  @PathVariable boolean qui, 
    								  @PathVariable boolean sex,
    								  @PathVariable boolean sab,
    								  @PathVariable boolean dom,
    								  @PathVariable Long unidadeId, 
    								  @PathVariable Long turmaId, 
    								  @PathVariable Long serieId, 
    								  @PathVariable Long plano_alimentarId) {
        return calendarioService.calculaTotalMes(cidadeId,
        										 mes,
        										 anoLetivoId,
        										 valorDia,
        										 seg,
        										 ter,
        										 qua,
        										 qui,
        										 sex,
        										 sab,
        	    								 dom,
        	    								 unidadeId, 
        	    								 turmaId, 
        	    								 serieId, 
        	    								 plano_alimentarId);
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
