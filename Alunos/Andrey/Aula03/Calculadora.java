package LojaGabi;

import java.util.Scanner;

public class Calculadora {
	private static double precoTotal;
	private static double regValorVenda;
	private static int regQntVendida;
	private static double regValorDesc;
	
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {	
		Menu();
	}

	private static void Menu() {
		Integer resp = 0;
		while(resp != 4) {
			
			System.out.println("digeite \n"
					+ "1 - Calcular preco total \n"
					+ "2 - Calcular troco \n"
					+ "3 - registro de venda diario \n"
					+ "4 - sair");
			resp = scan.nextInt();
			if(resp == 1){
				CalcularPreco();
			} else if(resp == 2) {
				CalcularTroco();
			}else if(resp == 3) {
				Registro();
			}else {
					resp = 4;
				}
			}
		}

	private static void Registro() {
		System.out.println("quantidade vendida no dia  " + regQntVendida + "\n");
		System.out.println("valor das vendas no dia  " + regValorVenda + "\n");
		System.out.println("valor do desconto no dia  " + regValorDesc + "\n");
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
			if(qnt >= 10) {
				double valorDesc = ((qnt * valor) * 0.05);
				precoTotal = precoTotal + ((qnt * valor) * 0.95);
				regValorDesc = regValorDesc + valorDesc;
				regQntVendida = regQntVendida + qnt;
				System.out.println("digite \n 1 para adicionar mais produto \n 2 para sair");
				resp = scan.nextInt();
				} else {
		precoTotal = precoTotal + (qnt * valor);
		regQntVendida = regQntVendida + qnt;
		System.out.println("digite \n 1 para adicionar mais produto \n 2 para sair");
		resp = scan.nextInt();
	}}
		System.out.println(precoTotal);
		regValorVenda = regValorVenda + precoTotal;
	}
}
