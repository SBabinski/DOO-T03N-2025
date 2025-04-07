package interfaceUsuario;

import tratamentos.TratamentoDeErros;  
import calculadora.Calculadora;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;


public class MenuPrincipal {
	
	static Scanner scan = new Scanner(System.in);
	static Calculadora calculadora = new Calculadora();
	//Lista para armazenar as vendas com a data.
	static final Map<LocalDate, List<Vendas>> Vendas_Por_Data = new HashMap(); 
	
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
					buscarVendasPorData();
					break;
				case 5:
					buscarVendasPorMes();
					break;
				case 6:
					exibirVenda();
					break;
				case 7:
					System.out.println("Saindo do sistema...");
					System.out.println("Sistema encerrado!");
					break;
					default:
						System.out.println("Opção invlálida!");
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Erro: " + e.getMessage());
			}

	} while (opcaoUsuario !=7);
		scan.close();
	
	}
	
	static void exibirMenu() {
		
		
		System.out.println("\n|-----------------------------|");
        System.out.println("|    Calculadora da Dona G.   |");
        System.out.println("|-----------------------------|");
        System.out.println("| 1. Calcular Preço Total     |");
        System.out.println("| 2. Registrar Venda          |");
        System.out.println("| 3. Calcular Troco           |");
        System.out.println("| 4. Buscar Vendas por Data   |");
        System.out.println("| 5. Buscar Vendas por Mês    |");
        System.out.println("| 6. Exibir Todas as Vendas   |");
        System.out.println("| 7. Sair                     |");
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
		
		LocalDate dataVenda = lerDataVenda(); //Lê data da venda.
		
		double valorVenda = calculadora.calcularPrecoTotal(quantidade, precoUnitario);
		double desconto = calculadora.calcularDesconto(quantidade,  valorVenda); // Calcula o desconto
		double valorFinal = valorVenda - desconto; // Valor final com desconto
		
		Vendas venda = new Vendas(quantidade, precoUnitario, valorVenda, desconto, dataVenda);
		
		Vendas_Por_Data.computeIfAbsent(dataVenda, k -> new ArrayList<>()).add(venda); // Adiciona a venda com a data

		
		System.out.printf("Venda registrada com sucesso! Valor final: R$%.2f\n", valorFinal);
		
	}
	
	//Funcionalidade nova, método para ler a data da venda
	static LocalDate lerDataVenda() {
		while (true) {
			System.out.println("Digite a data da vanda (dd/mm/aaa):");
			String dataString = scan.next();
			try {
				return LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd/MM/yyy"));
				
			} catch (DateTimeParseException e) {
				System.out.println("Data inválida. Use o formato (dd/mm/aaaa).");
			}
		}
		
	}
	
	//Funcionalidade nova, método para bscar vendas por data.
	static void buscarVendasPorData() {
		LocalDate dataBusca = lerDataVenda();
		
		List<Vendas> listaVendas = Vendas_Por_Data.get(dataBusca);
		
		if (listaVendas == null || listaVendas.isEmpty()) {
			System.out.println("Nenhuma vanda encontrada para esta data.");
			return;
		}
		
		System.out.println("---- Vendas em " + dataBusca.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " ----");
	
	
	double totalVendas = 0;
	
	for (Vendas venda : listaVendas) {
		System.out.println(venda);
		totalVendas += venda.getValorVenda() - venda.getDesconto();
	}
	
	System.out.printf("Total de vendas na data: R$%.2f\n", totalVendas);
	}
	
	// Calcula e exibe o troco
	static void calcularTroco() {
        double recebido = TratamentoDeErros.lerDouble(scan,"Digite o valor recebido: ");
        double total = TratamentoDeErros.lerDouble(scan, "Digite o valor da compra: ");
        double troco = calculadora.calcularTroco(recebido, total);
        System.out.printf("Troco: R$%.2f\n", troco);

    }
	
	//Nova funcionalidade, método para buscar vendas por mês.
	static void buscarVendasPorMes() {
		int mes = TratamentoDeErros.lerInteiro(scan, "Digite o mês (1-12):");
		
		if (mes < 1 || mes > 12) {
			System.out.println("Mês inválido.");
			return;
		}
		
		double totalVendasNoMes = 0;
		for (Map.Entry<LocalDate, List<Vendas>> entry : Vendas_Por_Data.entrySet()) {
            if (entry.getKey().getMonthValue() == mes) {
                for (Vendas venda : entry.getValue()) {
                    totalVendasNoMes += venda.getValorVenda()- venda.getDesconto();
                }
            }
        }
		
		if (totalVendasNoMes == 0) {
            System.out.println("Nenhuma venda encontrada para este mês.");
        } else {
            System.out.printf("Total de vendas no mês %d: R$%.2f\n", mes, totalVendasNoMes);
        }
		
		
	}
	
	// Exibe o histórico de vendas
	static void exibirVenda() {
		if (Vendas_Por_Data.isEmpty()) {
			System.out.println("Nenhuma venda registrada ainda.");
			return;
		}
		System.out.println("-----Histórico de Vendas-----");
		for (List<Vendas> vendasDoDia : Vendas_Por_Data.values()) {
		    for (Vendas venda : vendasDoDia) { // Itera sobre as vendas e imprime os detalhes
			System.out.println(venda);
			
		    }
		}
	}
		
}
