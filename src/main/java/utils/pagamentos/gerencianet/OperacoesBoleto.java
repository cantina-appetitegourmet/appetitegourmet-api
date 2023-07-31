package utils.pagamentos.gerencianet;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.gerencianet.gnsdk.Gerencianet;
import br.com.gerencianet.gnsdk.exceptions.GerencianetException;
import utils.RetornoString;

public class OperacoesBoleto {
	
	private String erro;
	
	public OperacoesBoleto() {
		erro = "";
	}
	
	public void limparErro() {
		erro = "";
	}
	
	public String getErro() {
		return erro;
	}
	
	private void adicionarItens(JSONArray items, List<ItemPedido> itens) {
		
		for(ItemPedido item : itens) {
			JSONObject jItem = new JSONObject();
			
			jItem.put("name", item.getDescricao());
			jItem.put("amount", item.getQuantidade());
			jItem.put("value", item.getValor());

			items.put(jItem);
		}
	}
	
	private void adicionarCliente(JSONObject customer, Cliente cliente) {
		if(cliente.getTipoPessoa() == Cliente.PESSOA_FISICA) {
			customer.put("name", cliente.getNome());
			customer.put("cpf", cliente.getCpf());
		} else {
			JSONObject juridical_person = new JSONObject();
			juridical_person.put("corporate_name", cliente.getRazaoSocial());
			juridical_person.put("cnpj", cliente.getCnpj());
			customer.put("juridical_person", juridical_person);
		}
		
		if(cliente.getEmail() != null) {
			customer.put("email", cliente.getEmail());
		}
		
		if(cliente.getTelefone() != null) {
			customer.put("phone_number", cliente.getTelefone());
		}
		
		if(cliente.getDataNascimento() != null) {
			customer.put("birth", cliente.getDataNascimento());
		}
		
		JSONObject endereco = new JSONObject();
		boolean enderecoPresente = false;
		
		if(cliente.getCep() != null) {
			endereco.put("zipcode", cliente.getCep());
		}
		
		if(cliente.getRua() != null) {
			endereco.put("street", cliente.getRua());
		}
		
		if(cliente.getNumero() != null) {
			endereco.put("number", cliente.getNumero());
		}
		
		if(cliente.getComplemento() != null) {
			endereco.put("complement", cliente.getComplemento());
		}
		
		if(cliente.getBairro() != null) {
			endereco.put("neighborhood", cliente.getBairro());
		}
		
		if(cliente.getCidade() != null) {
			endereco.put("city", cliente.getCidade());
		}
		
		if(cliente.getEstado() != null) {
			endereco.put("state", cliente.getEstado());
		}
		
		if(enderecoPresente) {
			customer.put("address", endereco);
		}
	}
	
	private boolean adicionarMetadata(JSONObject jMetadata, Metadata metadata) {
		boolean retorno = false;
		
		if(metadata.getUrlNotificacao() != null) {
			jMetadata.put("notification_url", metadata.getUrlNotificacao());
			retorno = true;
		}
		if(metadata.getIdBoleto() != null) {
			jMetadata.put("custom_id", metadata.getIdBoleto());
			retorno = true;
		}
		return retorno;
	}
	
	private void adicionarDesconto(JSONObject jDiscount, Desconto desconto) {
		
		jDiscount.put("type", desconto.getTipo());
		jDiscount.put("value", desconto.getValor());
	}
	
	private void adicionarDescontoCondicional(JSONObject jConditionalDiscount, DescontoCondicional condicional) {
		
		jConditionalDiscount.put("type", condicional.getTipo());
		jConditionalDiscount.put("value", condicional.getValor());
		jConditionalDiscount.put("until_date", condicional.getDataMaxima());
	}
	
	private void adicionarMulta(JSONObject jMulta, Multa multa) {
		
		jMulta.put("fine", multa.getMulta());
		jMulta.put("interest", multa.getJurosDia());
	}
	
	public boolean gerarBoleto(RetornoString dados,
			                   List<ItemPedido> itens,
			                   Cliente cliente,
			                   String dataExpiracao,
			                   Metadata metadata,
			                   Desconto desconto,
			                   DescontoCondicional condicional,
			                   Multa multa,
			                   String mensagem) {
		boolean retorno = false;
		Credentials credentials = new Credentials(Credentials.PAGAMENTOS, Credentials.PRODUCAO);
		JSONArray jItems = new JSONArray();
		JSONObject jCustomer = new JSONObject();
		boolean metadataPresente = false;
		JSONObject jMetadata = new JSONObject();
		JSONObject jDiscount = new JSONObject();
		JSONObject jConditionalDiscount = new JSONObject();
		JSONObject jMulta = new JSONObject();
		JSONObject jBoletoBancario = new JSONObject();
		JSONObject jPayment = new JSONObject();
		JSONObject jBody = new JSONObject();

		JSONObject options = new JSONObject();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		// items
		adicionarItens(jItems, itens);
		//notification url
		metadataPresente = adicionarMetadata(jMetadata, metadata);
		//customer
		adicionarCliente(jCustomer, cliente);
		if(desconto != null) {
			//discount		
			adicionarDesconto(jDiscount, desconto);
		}
		if(condicional != null) {
			//conditional discount
			adicionarDescontoCondicional(jConditionalDiscount, condicional);
		}
		//configurations
		adicionarMulta(jMulta, multa);
		
		jBoletoBancario.put("expire_at", dataExpiracao);
		jBoletoBancario.put("customer", jCustomer);
		
		if(desconto != null) {
			jBoletoBancario.put("discount", jDiscount);
		}
		
		jBoletoBancario.put("configurations", jMulta);
		
		if(condicional != null) {
			jBoletoBancario.put("conditional_discount", jConditionalDiscount);
		}
		
		if(mensagem != null) {
			jBoletoBancario.put("message", mensagem);
		}

		jPayment.put("banking_billet", jBoletoBancario);

		jBody.put("payment", jPayment);
		jBody.put("items", jItems);
		
		if(metadataPresente) {
			jBody.put("metadata", jMetadata);
		}
		
		try {
		    Gerencianet gn = new Gerencianet(options);
		    JSONObject response = gn.call("oneStep", new HashMap<String,String>(), jBody);
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
	
	public boolean cancelarBoleto(RetornoString dados,
            					  String idBoleto) {
		boolean retorno = false;
		Credentials credentials = new Credentials(Credentials.PAGAMENTOS, Credentials.PRODUCAO);
		HashMap<String, String> params = new HashMap<String, String>();
		JSONObject options = new JSONObject();
		
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());
		
		params.put("id", idBoleto);
		
		try {
			Gerencianet gn = new Gerencianet(options);
			JSONObject response = gn.call("cancelCharge", params, new JSONObject());
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

	
	public boolean exibirBoleto(RetornoString dados,
            					  String idBoleto) {
		boolean retorno = false;
		Credentials credentials = new Credentials(Credentials.PAGAMENTOS, Credentials.PRODUCAO);
		HashMap<String, String> params = new HashMap<String, String>();
		JSONObject options = new JSONObject();
		
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());
		
		params.put("id", idBoleto);
		
		try {
			Gerencianet gn = new Gerencianet(options);
			JSONObject response = gn.call("detailCharge", params, new JSONObject());
			dados.setRetornoString(response.toString());
			retorno = true;
		}catch (GerencianetException e){
			erro = Integer.toString(e.getCode());
			erro += " - " + e.getError();
			erro += " - " + e.getErrorDescription();
		}
		catch (Exception e) {
			erro = e.getMessage();
		}
		return retorno;
	}


}
