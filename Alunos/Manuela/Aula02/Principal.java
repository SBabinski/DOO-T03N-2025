package fag;
import java.util.Scanner;


public class Principal {
	
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		menuPrincipal();
	}

	public static void menuPrincipal(){

		System.out.println("Bem-vindo ao sistema de gerenciamento de floricultura!");

		System.out.println("Escolha a opção que deseja executar:\n1-Calcular o preço.\n2-Calcular o troco.\n3-Sair");

		int op= scanner.nextInt();

		if(op==1) { calcularPreco();

		}

		else if (op==2) { calcularTroco();

		}

		else if(op==3) {

			System.out.println("Sistema fechado!");

			System.exit(3);

		}

		else {

			System.out.println("ERRO- digite uma opção de 1 a 3!");

		}

	}

	

	public static void calcularPreco() {

	

	System.out.println("Dona Gabrielinha, insira a quantidade de plantas que foram vendidas:");

	int planta = scanner.nextInt();

	System.out.println("Ok!\nAgora, insira o valor unitário da planta:");

	double valoruni= scanner.nextDouble();

	System.out.println("Esse foi o valor final calculado:");

	double resul = planta*valoruni;

	System.out.println(resul);

	menuPrincipal();

	

	}

	

	public static void calcularTroco() {

		System.out.println("Dona Gabrielinha, insira qual foi o valor pago pelo cliente:");

		double valorp = scanner.nextDouble();

		System.out.println("Ok!\nAgora, insira o valor total da compra:");

		double valorc = scanner.nextDouble();

		double troco = valorp-valorc;

		System.out.printf("Troco: %.2f\n",troco);

	

		menuPrincipal();

	}
}
