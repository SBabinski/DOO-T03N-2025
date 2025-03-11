package lojinha;

import java.util.Scanner;

public class Lojinha_teste {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int option;

		do {
			System.out.println("[1] - Calcular Preço Total");
			System.out.println("[2] - Calcular Troco");
			System.out.println("[3] - Sair");
			System.out.print("Escolha uma opção: ");
			option = scanner.nextInt();

			switch (option) {
				case 1:
					calcularPrecoTotal(scanner);
					break;
				case 2:
					calcularTroco(scanner);
					break;
				case 3:
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
			}
		} while (option != 3);

		scanner.close();
	}

	private static void calcularPrecoTotal(Scanner scanner) {
		System.out.print("Digite a quantidade da planta: ");
		int quantidade = scanner.nextInt();
		System.out.print("Digite o preço unitário da planta: ");
		double precoUnitario = scanner.nextDouble();
		double precoTotal = quantidade * precoUnitario;
		System.out.println("O preço total é: " + precoTotal);
	}

	private static void calcularTroco(Scanner scanner) {
		System.out.print("Digite o valor recebido do cliente: ");
		double valorRecebido = scanner.nextDouble();
		System.out.print("Digite o valor total da compra: ");
		double valorTotal = scanner.nextDouble();
		double troco = valorRecebido - valorTotal;
		System.out.println("O troco a ser dado é: " + troco);
	}
}
