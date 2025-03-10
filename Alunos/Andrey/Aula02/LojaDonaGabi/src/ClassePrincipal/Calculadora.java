package ClassePrincipal;

import java.util.Scanner;

public class Calculadora {
	private static double precoTotal;
	
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {	
		Menu();
	}

	private static void Menu() {
		Integer resp = 0;
		while(resp != 3) {
			
			System.out.println("digeite \n"
					+ "1 - Calcular preco total \n"
					+ "2 - Calcular troco \n"
					+ "3 - sair");
			resp = scan.nextInt();
			if(resp == 1){
				CalcularPreco();
			} else if(resp == 2) {
				CalcularTroco();
			}	else {
					resp = 3;
				}
			}
		}

	private static void CalcularTroco() {
		System.out.println("digite valor pago pelo cliente");
		double valorPago = scan.nextDouble();
		double troco = valorPago - precoTotal;
		System.out.println("valor do troco " + troco);
	}

	private static void CalcularPreco() {
		Integer resp = 0;
		while(resp != 2) {
		System.out.println("digite quantidade vendida \n");
		Integer qnt = scan.nextInt();
		System.out.println("digite valor do produto \n");
		double valor = scan.nextDouble();
		precoTotal = precoTotal + (qnt * valor);
		System.out.println("digite \n 1 para adicionar mais produto \n 2 para sair");
		resp = scan.nextInt();
	}
		System.out.println(precoTotal);
	}
}
