package classes;
import java.time.LocalDate;

public class Venda {
	
	private double ValorUnitario;
	private double QuantProduto;
	private double ValorDaCompra;
	private double valorDaCompraComDesc;
	private LocalDate dataVenda;
	
	
	
	public Venda() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public double getValorUnitario() {
		return ValorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		ValorUnitario = valorUnitario;
	}

	public double getQuantProduto() {
		return QuantProduto;
	}

	public void setQuantProduto(double quantProduto) {
		QuantProduto = quantProduto;
	}

	public double getValorDaCompra() {
		return ValorDaCompra;
	}

	public void setValorDaCompra(double valorDaCompra) {
		ValorDaCompra = valorDaCompra;
	}

	public double getValorDaCompraComDesc() {
		return valorDaCompraComDesc;
	}

	public void setValorDaCompraComDesc(double valorDaCompraComDesc) {
		this.valorDaCompraComDesc = valorDaCompraComDesc;
	}
	
	public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    @Override
    public String toString() {
        return "Venda [ValorUnitario=" + ValorUnitario + ", QuantProduto=" + QuantProduto + ", ValorDaCompra="
                + ValorDaCompra + ", valorDaCompraComDesc=" + valorDaCompraComDesc + ", dataVenda=" + dataVenda + "]";
    }
}

