package utils.pagamentos.gerencianet;

import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;
import utils.Retorno;

import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.appetitegourmet.api.exception.ErroFormatacaoDataException;

public class OperacoesPix {
	
	private String erro;
	
	public OperacoesPix() {
		erro = "";
	}
	
	public void limparErro() {
		erro = "";
	}
	
	public String getErro() {
		return erro;
	}
	
	public boolean criarChave(Retorno dados) {
		
		boolean retorno = false;
		JSONObject response = null;
		Credentials credentials = new Credentials(Credentials.PIX, Credentials.PRODUCAO);

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());
		
		try {
			Gerencianet gn = new Gerencianet(options);
			response = gn.call("pixCreateEvp", new HashMap<String,String>(), new JSONObject());
			dados.setRetornoString(response.toString());
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
	
	public boolean removerChave(Retorno dados, String chave) {
		
		boolean retorno = false;
		JSONObject response = null;
		Credentials credentials = new Credentials(Credentials.PIX, Credentials.PRODUCAO);

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
	
	public boolean listarChaves(Retorno dados) {
		
		boolean retorno = false;
		JSONObject response = null;
		Credentials credentials = new Credentials(Credentials.PIX, Credentials.PRODUCAO);

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());
		
		try {
			Gerencianet gn = new Gerencianet(options);
			response = gn.call("pixListEvp", new HashMap<String, String>(), new JSONObject());
			dados.setRetornoString(response.toString());
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
	
	public boolean criarCobrancaImediataSemTxid(Retorno dados, 
												int expiracao,
												String cpf,
												String nome,
												String valor,
												String chave,
												String dadosItegracaoPix,
												String solicitacao) {
		
		boolean retorno = false;
		JSONObject response = null;
		//Credentials credentials = new Credentials(Credentials.PIX, Credentials.PRODUCAO);

		JSONObject options = new JSONObject(dadosItegracaoPix);
		//options.put("client_id", credentials.getClientId());
		//options.put("client_secret", credentials.getClientSecret());
		//options.put("certificate", credentials.getCertificate());
		//options.put("sandbox", credentials.isSandbox());
		
		JSONObject body = new JSONObject();
        body.put("calendario", new JSONObject().put("expiracao", expiracao));
        body.put("devedor", new JSONObject().put("cpf", cpf).put("nome", nome));
        body.put("valor", new JSONObject().put("original", valor));
        body.put("chave", chave);
        body.put("solicitacaoPagador", solicitacao);
        
        System.out.println("body = " + body.toString());

        //JSONArray infoAdicionais = new JSONArray();
        //infoAdicionais.put(new JSONObject().put("nome", "Campo 1").put("valor", "Informação Adicional1 do PSP-Recebedor"));
        //infoAdicionais.put(new JSONObject().put("nome", "Campo 2").put("valor", "Informação Adicional2 do PSP-Recebedor"));
        //body.put("infoAdicionais", infoAdicionais);
		
		try {
			Gerencianet gn = new Gerencianet(options);
			response = gn.call("pixCreateImmediateCharge", new HashMap<String,String>(), body);
			System.out.println("Response = " + response.toString());
			
			JSONObject loc = response.getJSONObject("loc");
			System.out.println("Loc = " + loc.toString());
			String dadosRetorno = "";
			dadosRetorno = "{ \"id\":" + Integer.toString(loc.getInt("id")) + ",";
			dadosRetorno += "\"txid\":\"" + response.getString("txid") + "\"}";
			dados.setRetornoString(dadosRetorno);
			
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
	
	public boolean criarQrCode(Retorno dados, 
							   Integer id) {

		boolean retorno = false;
		Map<String, Object> response;
		Credentials credentials = new Credentials(Credentials.PIX, Credentials.PRODUCAO);

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());
		
		HashMap<String, String> params = new HashMap<String, String>();
	    params.put("id", id.toString());

		try {
			Gerencianet gn = new Gerencianet(options);
			response = gn.call("pixGenerateQRCode", params, new HashMap<String, Object>());
			dados.setRetornoJson(new JSONObject(response));
			retorno = true;
		} catch (GerencianetException e){
			erro = e.getError();
			erro += " - " + e.getErrorDescription();
	    } catch (Exception e) {
			erro = e.getMessage();
		}
		return retorno;
	}
	
	public boolean listarCobrancas(Retorno dados, 
				   			       String sDtIncio,
				   			       String sDtFim,
				   			       String cpf,
				   			       String cnpj,
				   			       String status,
				   			       Integer paginalAtual,
				   			       Integer itensPorPagina) {
	
		boolean retorno = false;
		JSONObject response;
		Credentials credentials = new Credentials(Credentials.PIX, Credentials.PRODUCAO);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String complemento = "T00:00:00Z";
		Date dtInicial = null; 
		Date dtFinal = null;
		
		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());
		
		HashMap<String, String> params = new HashMap<String, String>();
		try {
			dtInicial = formatter.parse(sDtIncio);
		} catch (ParseException e) {
			throw new ErroFormatacaoDataException("formato = yyyy-mm-dd");
		}
		sDtIncio += complemento;
		params.put("inicio", sDtIncio);
		try {
			dtInicial = formatter.parse(sDtFim);
		} catch (ParseException e) {
			throw new ErroFormatacaoDataException("formato = yyyy-mm-dd");
		}
		sDtFim += complemento;
		params.put("fim", sDtFim);
		if(cpf != null) {
			params.put("cpf", cpf);
		} else if(cnpj != null) {
			params.put("cnpj", cnpj);
		}
		if(status != null) {
			params.put("status", status);
		}
		if(paginalAtual != null) {
			params.put("paginacao.paginaAtual", paginalAtual.toString());
		}
		if(itensPorPagina != null) {
			params.put("paginacao.itensPorPagina", itensPorPagina.toString());
		}
		
		try {
			Gerencianet gn = new Gerencianet(options);
			response = gn.call("pixListCharges", params, new JSONObject());
			dados.setRetornoString(response.toString());
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
	
	
	
	public boolean exibirCobranca(Retorno dados, 
		       					  String txid) {

		boolean retorno = false;
		JSONObject response;
		Credentials credentials = new Credentials(Credentials.PIX, Credentials.PRODUCAO);
		
		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("certificate", credentials.getCertificate());
		options.put("sandbox", credentials.isSandbox());
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("txid", txid);
		
		try {
			Gerencianet gn = new Gerencianet(options);
			response = gn.call("pixDetailCharge", params, new JSONObject());
			dados.setRetornoString(response.toString());
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
}
