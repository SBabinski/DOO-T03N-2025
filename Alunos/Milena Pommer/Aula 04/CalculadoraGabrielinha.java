package vendaGabrielinha;

public class CalculadoraGabrielinha {

	private int quantidade;
	private double precoUnitario;
	private double valorVenda;
	private double desconto;
	private int dia;
	private int mes;
	
	public CalculadoraGabrielinha(int quantidade, double precoUnitario, double valorVenda, double desconto, int dia, int mes) {
		  this.quantidade = quantidade;
	      this.precoUnitario = precoUnitario;
	      this.valorVenda = valorVenda;
	      this.desconto = desconto;
	      this.dia = dia;
	      this.mes = mes;
	}
	
	public int getDia() {
		return dia;
	}
	
	public int getMes() {
		return mes;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	
	@Override
	public String toString() {
		return "Vendas [quantidade=" + quantidade + ", precoUnitario=" + precoUnitario + ", valorVenda=" + valorVenda
				+ ", desconto=" + desconto + ", dia="+ dia +", mês="+ mes + "]";
	}	
}
