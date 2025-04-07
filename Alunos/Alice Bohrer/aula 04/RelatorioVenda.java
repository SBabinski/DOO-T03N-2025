package classes;

public class RelatorioVenda {
	
	private double QuantPlantasVendidas = 0;
	private double QuantidadeDeDescontos = 0;
	private double ValorTotalVendas = 0;
	
	
	public RelatorioVenda() {
		super();
		// TODO Auto-generated constructor stub
	}


	public double getQuantPlantasVendidas() {
		return QuantPlantasVendidas;
	}


	public void setQuantPlantasVendidas(double quantPlantasVendidas) {
		QuantPlantasVendidas = quantPlantasVendidas;
	}


	public double getQuantidadeDeDescontos() {
		return QuantidadeDeDescontos;
	}


	public void setQuantidadeDeDescontos(double quantidadeDeDescontos) {
		QuantidadeDeDescontos = quantidadeDeDescontos;
	}


	public double getValorTotalVendas() {
		return ValorTotalVendas;
	}


	public void setValorTotalVendas(double valorTotalVendas) {
		ValorTotalVendas = valorTotalVendas;
	}


	@Override
	public String toString() {
		return "RelatorioVenda [QuantPlantasVendidas=" + QuantPlantasVendidas + ", QuantidadeDeDescontos="
				+ QuantidadeDeDescontos + ", ValorTotalVendas=" + ValorTotalVendas + "]";
	}
	
	
	

}
