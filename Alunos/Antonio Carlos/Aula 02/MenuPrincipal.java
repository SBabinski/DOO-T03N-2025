package interfaceUsuario;

import tratamentos.TratamentoDeErros; 
import calculadora.Calculadora;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class MenuPrincipal {
	
	static Scanner scan = new Scanner(System.in);
	static Calculadora calculadora = new Calculadora();
	static List<Vendas> listaVendas = new ArrayList<>();//Lista para armazenar as vendas.

	public static void main(String[] args) {
		
		int opcaoUsuario;
		
		do {
			exibirMenu();
			opcaoUsuario = TratamentoDeErros.lerInteiro(scan, "Escolha uma opção:");
			scan.nextLine();
			
			try {
				switch (opcaoUsuario) {
				case 1:
					calcularPrecoTotal();
					break;
				case 2:
					registrarVenda();
					break;
				case 3:
					calcularTroco();
					break;
				case 4:
					exibirVenda();
					break;
				case 5:
					System.out.println("Saindo do sistema...");
					System.out.println("Sistema encerrado!");
					break;
					default:
						System.out.println("Opção invlálida!");
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Erro: " + e.getMessage());
			}

	} while (opcaoUsuario !=5);
		scan.close();
	
	}
	
	static void exibirMenu() {
		
		
		System.out.println("\n|-----------------------------|");
        System.out.println("|    Calculadora da Dona G.   |");
        System.out.println("|-----------------------------|");
        System.out.println("| 1. Calcular Preço Total     |");
        System.out.println("| 2. Registrar Venda          |");
        System.out.println("| 3. Calcular Troco           |");
        System.out.println("| 4. Exibir Vendas            |");
        System.out.println("| 5. Sair                     |");
        System.out.println("|-----------------------------|");
	}
	
    // Calcula e exibe o preço total
	static void calcularPrecoTotal() {
		int quantidade = TratamentoDeErros.lerInteiro(scan,"Digite a quantidade: ");
		double precoUnitario = TratamentoDeErros.lerDouble(scan, "Digite o preço unitário: ");
		double precoTotal = calculadora.calcularPrecoTotal(quantidade, precoUnitario);
		System.out.printf("Preço total: R$%.2f\n", precoTotal);
	}
	
    // Registra uma nova venda
	static void registrarVenda() {
		int quantidade = TratamentoDeErros.lerInteiro(scan, "Digite a quantidade de plantas: ");
		double precoUnitario = TratamentoDeErros.lerDouble(scan, "Digite o preço unitário: ");
		double valorVenda = calculadora.calcularPrecoTotal(quantidade, precoUnitario);
		double desconto = calculadora.calcularDesconto(quantidade,  valorVenda); // Calcula o desconto
		double valorFinal = valorVenda - desconto; // Valor final com desconto
		
		Vendas venda = new Vendas(quantidade, precoUnitario, valorVenda, desconto);
		listaVendas.add(venda); // Adiciona a venda à lista
		
		System.out.printf("Venda registrada com sucesso! Valor final: R$%.2f\n", valorFinal);
		
	}
	
	// Calcula e exibe o troco
	static void calcularTroco() {
        double recebido = TratamentoDeErros.lerDouble(scan,"Digite o valor recebido: ");
        double total = TratamentoDeErros.lerDouble(scan, "Digite o valor da compra: ");
        double troco = calculadora.calcularTroco(recebido, total);
        System.out.printf("Troco: R$%.2f\n", troco);

    }
	
	// Exibe o histórico de vendas
	static void exibirVenda() {
		if (listaVendas.isEmpty()) {
			System.out.println("Nenhuma venda registrada ainda.");
			return;
		}
		System.out.println("-----Histórico de Vendas-----");
		for (Vendas venda : listaVendas) { // Itera sobre as vendas e imprime os detalhes
			System.out.println(venda);
		}
	}
}
