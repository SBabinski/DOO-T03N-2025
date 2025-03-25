package calculadora;

public class Calculadora {
	
	public double calcularPrecoTotal(int quantidade, double precoUnitario) {
		if (quantidade < 0 || precoUnitario < 0) {
			throw new IllegalArgumentException("Quantidade e preço unitário devem ser positivos.");
		}
		return quantidade * precoUnitario;
	}
	
	public double calcularTroco(double valorRecebido, double valorTotal) {
		if (valorRecebido < 0 || valorTotal < 0) {
			throw new IllegalArgumentException("Valores para cálculo do troco devem ser positivos.");
		}
		
		if (valorRecebido < valorTotal) {
			throw new IllegalArgumentException("Valor recebido insuficiente.");
		}
		return valorRecebido - valorTotal;
	}
	
}

