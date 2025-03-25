package atividade02;

import java.util.Scanner;

public class Principal {
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[]args) {
		menuPrincipal();
	}
	
	public static void menuPrincipal(){
		System.out.println("Bem-vindo ao sistem de gerenciamento de loja de vendas de plantas!");
		System.out.println("Escolha a opção desejada:\n1-Calcular preço.\n2-Calcular troco.\n3-sair");
		int op= scanner.nextInt();
		if(op==1) { calcularPreco();
		}
		else if (op==2) { calcularTroco();
		}
		else if(op==3) {
			System.out.println("Sistema encerrado!");
			System.exit(3);
		}
		else {
			System.out.println("ERRO- digite uma opção de 1 a 3!");
		}
	}
	
	public static void calcularPreco() {
	
	System.out.println("Dona Gabrielinha, insira por gentileza a quntidade da planta desejada vendida:");
	int planta = scanner.nextInt();
	System.out.println("Certo!\nAgora insira o valor unitário:");
	double valoruni= scanner.nextDouble();
	System.out.println("Segue o resultado do calculo:");
	double resul = planta*valoruni;
	System.out.println(resul);
	menuPrincipal();
	
	}
	
	public static void calcularTroco() {
		System.out.println("Dona Gabrielinha, insira por gentileza o valor pago pelo cliente:");
		double valorp = scanner.nextDouble();
		System.out.println("Certo!\nAgora insira o valor total da compra:");
		double valorc = scanner.nextDouble();
		double troco = valorp-valorc;
		System.out.printf("Troco: %.2f\n",troco);
	
		menuPrincipal();
	}
	
	
}
	