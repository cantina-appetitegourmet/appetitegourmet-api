package utils.pagamentos.gerencianet;

import java.io.IOException;
import java.io.InputStream;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Credentials {
	private String clientId;
	private String clientSecret;
	private String certificate;
	private boolean sandbox;
	private boolean debug;
	public static final int PRODUCAO = 1;
	public static final int HOMOLOGACAO = 2;
	public static final int PIX = 3;
	public static final int PAGAMENTOS = 4;

	public Credentials(int tipo, int ambiente) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream credentialsFile;
		if(PIX == tipo) {
			if(PRODUCAO == ambiente) {
				credentialsFile = classLoader.getResourceAsStream("pagamentos/gerencianet/credentialsPixProducao.json");
			} else {
				credentialsFile = classLoader.getResourceAsStream("pagamentos/gerencianet/credentialsPixHomologacao.json");
			}
		} else {
			if(PRODUCAO == ambiente) {
				credentialsFile = classLoader.getResourceAsStream("pagamentos/gerencianet/credentialsPagamentosProducao.json");
			} else {
				credentialsFile = classLoader.getResourceAsStream("pagamentos/gerencianet/credentialsPagamentosHomologacao.json");
			}
		}
		JSONTokener tokener = new JSONTokener(credentialsFile);
		JSONObject credentials = new JSONObject(tokener);
		try {
			credentialsFile.close();
		} catch (IOException e) {
			System.out.println("Impossible to close file credentials.json");
		}

		this.clientId = credentials.getString("client_id");
		this.clientSecret = credentials.getString("client_secret");
		this.certificate = credentials.getString("certificate");
		this.sandbox = credentials.getBoolean("sandbox");
		this.debug = credentials.getBoolean("debug");
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public String getCertificate() {
		return certificate;
	}

	public boolean isSandbox() {
		return sandbox;
	}

	public boolean isDebug() {
		return debug;
	}

}
