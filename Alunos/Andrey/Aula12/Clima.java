package Objeto;

import java.math.BigDecimal;

public class Clima {
	private String cidade;
	private BigDecimal tempMaxima;
	private BigDecimal tempMinima;
	private BigDecimal humidade;
	private String condicaoTempo;
	private BigDecimal precipitacao;
	private BigDecimal velocidadeAr;
	

	public Clima(String cidade, BigDecimal tempMaxima, BigDecimal tempMinima, BigDecimal humidade, String condicaoTempo,
			BigDecimal precipitacao, BigDecimal velocidadeAr) {
		super();
		this.cidade = cidade;
		this.tempMaxima = tempMaxima;
		this.tempMinima = tempMinima;
		this.humidade = humidade;
		this.condicaoTempo = condicaoTempo;
		this.precipitacao = precipitacao;
		this.velocidadeAr = velocidadeAr;
	}


	@Override
	public String toString() {
		return "cidade =" + cidade + "\n "
				+ "tempMaxima =" + tempMaxima + "\n"
				+ "tempMinima =" + tempMinima + "\n "
				+ "humidade =" + humidade + "\n"
				+ "condicaoTempo =" + condicaoTempo + "\n"
				+ "precipitacao =" + precipitacao + "\n"
				+ "velocidadeAr =" + velocidadeAr;
	}

	
	
	
}
