package utils;

import java.util.NoSuchElementException;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import br.com.appetitegourmet.api.models.Endereco;

public class ConsultaEndereco {
	
	
	public Endereco consultaEnderecoPorCep(String cep) throws Exception {
		String uri = "https://viacep.com.br/ws/xxxxxxxx/json/";
		RestTemplate restTemplate = new RestTemplate();
		Endereco endereco = new Endereco();
		String resposta;
		JSONObject json;
		
		uri = uri.replace("xxxxxxxx", cep);
		System.out.println("URI - " + uri);
		resposta = restTemplate.getForObject(uri, String.class);
		System.out.println("RESPOSTA = " + resposta);
		json = new JSONObject(resposta);
		System.out.println("JSON RESPOSTA = " + json.toString());
		if(!json.isNull("erro")) {
			throw new NoSuchElementException("Endereço inválido!");
		} else {
			endereco.setBairro(json.getString("bairro"));
			endereco.setCep(cep);
			endereco.setCidade(json.getString("localidade"));
			endereco.setLogradouro(json.getString("logradouro"));
			endereco.setUf(json.getString("uf"));
		}
		
		return endereco;
	}

}
