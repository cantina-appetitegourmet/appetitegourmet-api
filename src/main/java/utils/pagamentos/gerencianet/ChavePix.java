package utils.pagamentos.gerencianet;

import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;
import utils.RetornoString;

import org.json.JSONObject;
import java.util.HashMap;

public class ChavePix {
	
	private String erro;
	
	public ChavePix() {
		erro = "";
	}
	
	public boolean criar(RetornoString dados) {
		
		boolean retorno = false;
		JSONObject response = null;
		Credentials credentials = new Credentials(Credentials.PRODUCAO);

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());
		
		try {
			Gerencianet gn = new Gerencianet(options);
			response = gn.call("pixCreateEvp", new HashMap<String,String>(), new JSONObject());
			dados.setRetornoString(response.toString());;
			retorno = true;
		}catch (GerencianetException e){
			erro = e.getError();
			erro += " - " + e.getErrorDescription();
		}
		catch (Exception e) {
			erro = e.getMessage();
		}
		return retorno;
	}
	
	public boolean remover(RetornoString dados, String chave) {
		
		boolean retorno = false;
		JSONObject response = null;
		Credentials credentials = new Credentials(Credentials.PRODUCAO);

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("chave", chave);
		
		try {
			Gerencianet gn = new Gerencianet(options);
			response = gn.call("pixDeleteEvp", params, new JSONObject());
			
			if(response == null)
			{
				dados.setRetornoString("Chave : " + chave + " - excluida com sucesso");
			}
			retorno = true;
		}catch (GerencianetException e){
			erro = e.getError();
			erro += " - " + e.getErrorDescription();
		}
		catch (Exception e) {
			erro = e.getMessage();
			if(erro.compareTo("A JSONObject text must begin with '{' at 1 [character 2 line 1]") == 0)
			{
				dados.setRetornoString("Chave : " + chave + " - excluida com sucesso");
				retorno = true;
			}
		}
		return retorno;
	}
	
	public boolean listar(RetornoString dados) {
		
		boolean retorno = false;
		JSONObject response = null;
		Credentials credentials = new Credentials(Credentials.PRODUCAO);

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());
		
		try {
			Gerencianet gn = new Gerencianet(options);
			response = gn.call("pixListEvp", new HashMap<String, String>(), new JSONObject());
			dados.setRetornoString(response.toString());;
			retorno = true;
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
