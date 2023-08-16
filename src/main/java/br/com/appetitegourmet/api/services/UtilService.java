package br.com.appetitegourmet.api.services;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.Endereco;
import utils.ConsultaEndereco;


@Service
public class UtilService {

	public UtilService() {
		
	}
	
	public Endereco consultaEnderecoPorCep(String cep) {
		ConsultaEndereco consulta = new ConsultaEndereco(); 
        return consulta.consultaEnderecoPorCep(cep);
    }
}
