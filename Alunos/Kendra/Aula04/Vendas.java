package objetos04;

public class Vendas {
	
	private int quant;
	private double valoruni;
	private double total;
	private double desc;
	private int dia;
	private int mes;
	
	
	
	public int getQuant() {
		return quant;
	}
	public void setQuant(int quant) {
		this.quant = quant;
	}
	public double getValoruni() {
		return valoruni;
	}
	public void setValoruni(double valoruni) {
		this.valoruni = valoruni;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getDesc() {
		return desc;
	}
	public void setDesc(double desc) {
		this.desc = desc;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	
	
	public Vendas(int quant, double valoruni, double total, double desc, int dia, int mes) {
		this.quant = quant;
		this.valoruni = valoruni;
		this.total = total;
		this.desc = desc;
		this.dia = dia;
		this.mes = mes; 	}
		
		
		
}
