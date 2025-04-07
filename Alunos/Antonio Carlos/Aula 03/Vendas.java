package interfaceUsuario;

//Representa uma venda de plantas, armazenando informações como quantidade,
//preço unitário, valor total da venda e desconto aplicado.
public class Vendas {
	
	private int quantidade;
	private double precoUnitario;
	private double valorVenda;
	private double desconto;
	
	public Vendas(int quantidade, double precoUnitario, double valorVenda, double desconto) {
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
