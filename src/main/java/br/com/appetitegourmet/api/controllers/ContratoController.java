package br.com.appetitegourmet.api.controllers;

import java.util.List;

import br.com.appetitegourmet.api.dto.ContratoRequest;
import br.com.appetitegourmet.api.dto.ContratoResponse;
import br.com.appetitegourmet.api.mapper.ContratoMappler;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
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

import br.com.appetitegourmet.api.models.Contrato;
import br.com.appetitegourmet.api.services.ContratoService;

@RestController
@AllArgsConstructor
@RequestMapping("/contratos")
@CrossOrigin(origins = "http://localhost:4200,https://nice-beach-01dafa610.3.azurestaticapps.net,https://menukids.appetitegourmet.com.br", maxAge = 3600, allowCredentials="true")
public class ContratoController {

	private final ContratoService contratoService;
    private final ContratoMappler contratoMappler;
    
    @GetMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public List<Contrato> listarContratos() {
        return contratoService.listarContratos();
    }

    @GetMapping("/responsavel/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public List<Contrato> listarContratosDeUmResponsavel(HttpServletRequest request, @PathVariable Long id) {
        return contratoService.listarContratosResponsavel(request);
    }

    @GetMapping("/responsavel")
    @PreAuthorize("hasRole('ROLE_RESPONSAVEL')")
    public List<Contrato> listarContratosResponsavel(HttpServletRequest request) {
        return contratoService.listarContratosResponsavel(request);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public ContratoResponse buscarContratoPorId(@PathVariable Long id) {
        Contrato contrato = contratoService.buscarContratoPorId(id);
        ContratoResponse contratoResponse = contratoMappler.INSTANCE.contratoToContratoResponse(contrato);
        return contratoResponse;
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public Contrato salvarContrato(HttpServletRequest request, @RequestBody ContratoRequest contrato) {
        return contratoService.salvarContrato(request, contrato);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void excluirContrato(@PathVariable Long id) {
        contratoService.excluirContrato(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void editarContrato(@PathVariable Long id, @RequestBody Contrato contrato) {
        contratoService.editarContrato(id, contrato);
    }
}
