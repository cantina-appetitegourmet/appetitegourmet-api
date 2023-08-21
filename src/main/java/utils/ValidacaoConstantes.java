package utils;

import java.math.BigDecimal;
import java.util.List;

import br.com.appetitegourmet.api.models.ContratoPlano;

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

    private ValidacaoConstantes() {
    }
    
    static public BigDecimal totalizaContratoPlano(List<ContratoPlano> listaContratoPlano) {
		
		BigDecimal total = BigDecimal.ZERO;
		
		for(ContratoPlano cp : listaContratoPlano) {
			BigDecimal dias = BigDecimal.ZERO;
			if(cp.getSegunda()) {
				dias = dias.add(BigDecimal.ONE);
			}
			if(cp.getTerca()) {
				dias = dias.add(BigDecimal.ONE);
			}
			if(cp.getQuarta()) {
				dias = dias.add(BigDecimal.ONE);
			}
			if(cp.getQuinta()) {
				dias = dias.add(BigDecimal.ONE);
			}
			if(cp.getSexta()) {
				dias = dias.add(BigDecimal.ONE);
			}
			if(cp.getSabado()) {
				dias = dias.add(BigDecimal.ONE);
			}
			if(cp.getDomingo()) {
				dias = dias.add(BigDecimal.ONE);
			}
			total = total.add(dias.multiply(cp.getPrecoDia())); 
		}
		
		return total;
	}
}
