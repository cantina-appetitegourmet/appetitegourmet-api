package br.com.appetitegourmet.api.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.appetitegourmet.api.models.Responsavel;
import br.com.appetitegourmet.api.services.EmailService;
import br.com.appetitegourmet.api.services.ResponsavelService;
import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/responsaveis")
@PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
public class ResponsavelController {

	private final ResponsavelService responsavelService;
	private final EmailService emailService;
    
    public ResponsavelController(ResponsavelService responsavelService, EmailService emailService) {
        this.responsavelService = responsavelService;
		this.emailService = emailService;
    }
    
    @GetMapping
    public List<Responsavel> listarResponsaveis() {
        return responsavelService.listarResponsaveis();
    }
    
    @GetMapping("/{id}")
    public Responsavel buscarResponsavelPorId(@PathVariable Long id) {
        return responsavelService.buscarResponsavelPorId(id);
    }
    
    @PostMapping
    public Responsavel salvarResponsavel(@RequestBody Responsavel responsavel) throws MessagingException, IOException {
    	Responsavel cadastrado = responsavelService.salvarResponsavel(responsavel);
    	String mensagem = "Clique aqui para ativar a sua conta";
    	//emailService.sendHtmlEmail("ricardooliveira@dot7", 
    	//						   cadastrado.getPessoa().getEmail(), 
    	//						   "Ativação da conta Appetite Gourmet", mensagem, null);
        return cadastrado;
    }
    
    @DeleteMapping("/{id}")
    public void excluirResponsavel(@PathVariable Long id) {
        responsavelService.excluirResponsavel(id);
    }

    @PutMapping("/{id}")
    public void editarResponsavel(@PathVariable Long id, @RequestBody Responsavel responsavel) {
        responsavelService.editarResponsavel(id, responsavel);
    }
}
