package utils;

import java.math.BigDecimal;
import java.util.List;

import br.com.appetitegourmet.api.models.ContratoPlano;
import br.com.appetitegourmet.api.models.Feriado;

public final class ValidacaoConstantes {
    public static final int TAMANHO_MAXIMO_NOME_COMPLETO = 100;
    public static final int TAMANHO_MAXIMO_CPF = 14;
    public static final int TAMANHO_MAXIMO_TELEFONE = 20;
    public static final int TAMANHO_MAXIMO_EMAIL = 255;
    public static final int TAMANHO_MAXIMO_GRAU_PARENTESCO = 20;
    
    public static final int TP_DESCONTO_VALOR = 1;
    public static final int TP_DESCONTO_PERCENTUAL = 2;
    
    public static final int PAGAMENTO_TIPO_PIX = 1;
    public static final int PAGAMENTO_TIPO_BOLETO = 2;
    
    public static final int EMPRESA_GERENCIANET = 1;
    
    public static final int STATUS_GERADO = 1;
    public static final int STATUS_PAGO = 2;
    public static final int STATUS_CANCELADO = 3;
    
    public static final int DESCONTO_2_OU_MAIS_FILHOS = 1;
    
    public static final int FERIADO_NACIONAL = 1;
    public static final int FERIADO_ESTADUAL = 2;
    public static final int FERIADO_MUNICIPAL = 3;

    private ValidacaoConstantes() {
    }
    
    static public int getTipoFeriado(Feriado feriado) {
    	int tipo = FERIADO_NACIONAL;
    	
    	if (feriado.getCidade() != null) {
    		tipo = FERIADO_MUNICIPAL;
    	} else if(feriado.getEstado() != null) {
    		tipo = FERIADO_ESTADUAL;
    	}
    	
    	return tipo;
    }
}
