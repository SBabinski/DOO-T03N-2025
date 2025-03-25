package calculadoraGabrielinha;

public class VendaGabrielinha {

	private int quantidade;
	private double precoUnitario;
	private double valorVenda;
	private double desconto;
	
	public VendaGabrielinha(int quantidade, double precoUnitario, double valorVenda, double desconto) {
		  this.quantidade = quantidade;
	        this.precoUnitario = precoUnitario;
	        this.valorVenda = valorVenda;
	        this.desconto = desconto;
	}
	@Override
	public String toString() {
		return "Vendas [quantidade=" + quantidade + ", precoUnitario=" + precoUnitario + ", valorVenda=" + valorVenda
				+ ", desconto=" + desconto + "]";
	}	
}
