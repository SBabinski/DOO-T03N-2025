package interfaceUsuario;

import tratamentos.TratamentoDeErros; 
import calculadora.Calculadora;
import java.util.Scanner;

public class MenuPrincipal {
	
	static Scanner scan = new Scanner(System.in);
	static Calculadora calculadora = new Calculadora();

	public static void main(String[] args) {
		
		int opcaoUsuario;
		
		do {
			menuPrincipal();
			opcaoUsuario = TratamentoDeErros.lerInteiro(scan, "Escolha uma opção:");
			scan.nextLine();
			
			try {
				switch (opcaoUsuario) {
				case 1:
					calcularPrecoTotal();
					break;
				case 2:
					calcularTroco();
					break;
				case 3:
					System.out.println("Saindo do sistema...");
					System.out.println("Sistema encerrado!");
					break;
					default:
						System.out.println("Opção invlálida!");
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Erro: " + e.getMessage());
			}

	} while (opcaoUsuario !=3);
		scan.close();
	
	}
	
	static void menuPrincipal() {
		
		
		System.out.println("\n|-----------------------------|");
        System.out.println("|    Calculadora da Dona G.   |");
        System.out.println("|-----------------------------|");
        System.out.println("| 1. Calcular Preço Total     |");
        System.out.println("| 2. Calcular Troco           |");
        System.out.println("| 3. Sair                     |");
        System.out.println("|-----------------------------|");
	}
	
	static void calcularPrecoTotal() {
		int quantidade = TratamentoDeErros.lerInteiro(scan,"Digite a quantidade: ");
		double precoUnitario = TratamentoDeErros.lerDouble(scan, "Digite o preço unitário: ");
		double precoTotal = calculadora.calcularPrecoTotal(quantidade, precoUnitario);
		System.out.printf("Preço total: R$%.2f\n", precoTotal);
	}
	
	static void calcularTroco() {
        double recebido = TratamentoDeErros.lerDouble(scan,"Digite o valor recebido: ");
        double total = TratamentoDeErros.lerDouble(scan, "Digite o valor da compra: ");
        double troco = calculadora.calcularTroco(recebido, total);
        System.out.printf("Troco: R$%.2f\n", troco);

    }
	
	

}
