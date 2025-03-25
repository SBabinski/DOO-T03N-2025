package vendinhaDaGabrielinha;

import java.util.Scanner;

public class VendinhaDaGabrielinha {
	
	static Scanner scan = new Scanner (System.in);

	public static void main(String[] args) {

		menuPrincipal();
		
	}
	
	public static void menuPrincipal() {
		
		System.out.println("------------ Menu Principal ------------");
		System.out.println("Informe o número correspondente à ação que deseja realizar:");
		System.out.println(" ");
		System.out.println("Digite 1 para calcular o preço total;");
		System.out.println("Digite 2 para calcular o troco");
		System.out.println("Digite 3 para sair do sistema");
		
		int escolhaUsuario = scan.nextInt();
		scan.nextLine();
		
		if (escolhaUsuario == 1) {
			calcularPreçoTotal();
		} else if (escolhaUsuario == 2) {
			calcularTroco();
		} else if (escolhaUsuario == 3) {
			System.out.println("Sistema encerrado.");
			System.exit(0);
		} else {
			System.out.println("Número informado é inválido!");
			System.out.println("Sistema encerrado.");
			System.exit(0);
		}
	}
	
	public static void calcularPreçoTotal() {
		
		System.out.println("------------ Calculando preço total ------------");
		System.out.println(" ");
		
		System.out.println("Informe a quantidade da planta:");
		int quantidadePlanta = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Você informou a quantidade: "+ quantidadePlanta);
		System.out.println(" ");
		
		System.out.println("Informe o valor da planta:");
		float precoPlanta = scan.nextFloat();
		scan.nextLine();
		
		System.out.println("Você informou o valor: R$"+ precoPlanta);
		System.out.println(" ");
		
		float valorTotal = (quantidadePlanta*precoPlanta);
		
		System.out.println("O valor total é: R$"+ valorTotal);
		System.out.println(" ");
		System.out.println(" ");
		
		menuPrincipal();
	}
	
	public static void calcularTroco() {
		
		System.out.println("------------ Calculando troco ------------");
		System.out.println(" ");
		
		System.out.println("Informe o valor recebido pelo cliente:");
		float valorRecebido = scan.nextFloat();
		scan.nextLine();

		
		System.out.println("Você informou a quantidade: R$"+ valorRecebido);
		System.out.println(" ");
		
		System.out.println("Informe o valor total da compra:");
		float valorCompra = scan.nextFloat();
		scan.nextLine();

		
		System.out.println("Você informou o valor total da compra: R$"+ valorCompra);
		System.out.println(" ");
		
		float valorTroco = (valorRecebido - valorCompra);
		
		if (valorTroco > 0) {
			System.out.println("O troco do cliente é de: R$"+ valorTroco);
		} else if (valorTroco == 0) {
			System.out.println("O valor recebido é correspondente ao cobrado, não possui troco.");
		} else {
			System.out.println("O valor recebido não é correspondente ao cobrado.");
		}
		
		System.out.println(" ");
		System.out.println(" ");
		menuPrincipal();
	}
}
