package interfaceUsuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Representa uma venda de plantas, armazenando informações como quantidade,
//preço unitário, valor total da venda e desconto aplicado.
public class Vendas {
	
	private int quantidade;
	private double precoUnitario;
	private double valorVenda;
	private double desconto;
	private LocalDate dataVenda;
	
	public Vendas(int quantidade, double precoUnitario, double valorVenda, double desconto,
			LocalDate dataVenda) {
		  this.quantidade = quantidade;
	        this.precoUnitario = precoUnitario;
	        this.valorVenda = valorVenda;
	        this.desconto = desconto;
	        this.dataVenda = dataVenda;
	}
	
	public double getValorVenda() {
		return valorVenda;
	}
	
	public double getDesconto() {
		return desconto;
	}

	 @Override
	    public String toString() {
	        return  "Data da Venda: " + dataVenda.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
	                ", Quantidade: " + quantidade +
	                ", Preço Unitário: R$" + String.format("%.2f", precoUnitario) +
	                ", Valor da Venda: R$" + String.format("%.2f", valorVenda) +
	                ", Desconto: R$" + String.format("%.2f", desconto);
	    }
	
	

}
