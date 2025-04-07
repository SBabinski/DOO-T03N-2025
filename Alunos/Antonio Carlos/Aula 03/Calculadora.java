package calculadora;

public class Calculadora {
	
	//Calcula o preço total com base na quantidade e no preço unitário.
	//throw new IllegalArgumentException se a quantidade ou o preço forem negativos.
	// Lança exceção para valores inválidos
	public double calcularPrecoTotal(int quantidade, double precoUnitario) {
		if (quantidade < 0 || precoUnitario < 0) {
			throw new IllegalArgumentException("Quantidade e preço unitário devem ser positivos.");
		}
		return quantidade * precoUnitario;
	}
	
	//Calcula o desconto para compras acima de 10 unidades.
	public double calcularDesconto(int quantidade, double valorTotal) {
		if (quantidade > 10) {
			return valorTotal * 0.05; // Desconto de 5% para mais de 10 unidades
		}
		return 0; // Sem desconto
	}
	
	// Calcula o troco.
	// Lança exceção para valores inválidos
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

