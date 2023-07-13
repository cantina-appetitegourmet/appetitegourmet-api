package utils.pagamentos.gerencianet;

import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.*;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;

import utils.Credentials;

public class ChavePix {
	
	private String erro;
	
	public ChavePix() {
		erro = "";
	}
	
	public boolean criar(String dados) {
		
		boolean retorno = false;
		
		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());
		
		try {
			Gerencianet gn = new Gerencianet(options);
			JSONObject response = gn.call("pixCreateEvp", new HashMap<String,String>(), new JSONObject());
			dados.concat(response.toString());
		}catch (GerencianetException e){
			erro = e.getError();
			erro += " - " + e.getErrorDescription();
		}
		catch (Exception e) {
			erro = e.getMessage();
		}
		return retorno;
	}
	
	public boolean remover(String chave) {
		
		boolean retorno = false;
		
		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("chave", chave);
		
		try {
			Gerencianet gn = new Gerencianet(options);
			JSONObject response = gn.call("pixDeleteEvp", params, new JSONObject());
			System.out.println(response);
		}catch (GerencianetException e){
			erro = e.getError();
			erro += " - " + e.getErrorDescription();
		}
		catch (Exception e) {
			erro = e.getMessage();
		}
		return retorno;
	}
	
	public boolean listar(List<String> chave) {
		
		boolean retorno = false;
		
		Credentials credentials = new Credentials();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());
		
		try {
			Gerencianet gn = new Gerencianet(options);
			JSONObject response = gn.call("pixListEvp", new HashMap<String, String>(), new JSONObject());
			System.out.println(response);
		}catch (GerencianetException e){
			erro = e.getError();
			erro += " - " + e.getErrorDescription();
		}
		catch (Exception e) {
			erro = e.getMessage();
		}
		return retorno;
	}
	
	public void limparErro() {
		erro = "";
	}
	
	public String getErro() {
		return erro;
	}

}
