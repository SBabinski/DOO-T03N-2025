package vendaGabrielinha;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class VendaGabrielinha {
		
	static Scanner scan = new Scanner (System.in);
	static List<CalculadoraGabrielinha> listaVendas = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menuPrincipal();
	}

	public static void menuPrincipal() {
			
		System.out.println("------------ Menu Principal ------------");
		System.out.println("Informe o número correspondente à ação que deseja realizar:");
		System.out.println(" ");
		System.out.println("Digite 1 para calcular o preço total:");
		System.out.println("Digite 2 para registrar venda:");
		System.out.println("Digite 3 para calcular o troco:");
		System.out.println("Digite 4 pra exibir vendas:");
		System.out.println("Digite 5 para buscar vendas por dia:");
		System.out.println("Digite 6 para buscar vendas por mês:");
		System.out.println("Digite 7 para sair do sistema:");

			
		int escolhaUsuario = scan.nextInt();
		scan.nextLine();
			
		if (escolhaUsuario == 1) {
			calcularPreçoTotal();
		} else if (escolhaUsuario == 2) {
			registrarVenda();
		} else if (escolhaUsuario == 3) {
			calcularTroco();
		}  else if (escolhaUsuario == 4) {
			exibirVendas();
		}
		else if (escolhaUsuario == 5) {
			buscarVendasDia();
		}
		else if (escolhaUsuario == 6) {
			buscarVendasMes();
		}
		else if (escolhaUsuario == 7) {
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
			
			
		menuPrincipal();
	}
		
	public static double calcularValorComDesconto(int quantidade, double preco) {
		double valorTotal = quantidade * preco;
		if (quantidade > 10) {
			valorTotal *= 0.95;
		}
		return valorTotal;
	}
		
	public static void registrarVenda() {
		System.out.println("----Registrando Venda----");
			
		System.out.println("Informe o dia da venda:");
		int dia = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Informe o mês da venda:");
		int mes = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Informe a quantidade da planta:");
		int quantidade = scan.nextInt();
		scan.nextLine();
			
		System.out.println("Informe o valor da planta:");
		float preco = scan.nextFloat();
		scan.nextLine();
		

        double valorTotal = calcularValorComDesconto(quantidade, preco);
        double desconto = 0;
        if (quantidade > 10) {
        	
        	desconto = valorTotal *0.05;
        	valorTotal -= desconto;
        }

        listaVendas.add(new CalculadoraGabrielinha(quantidade, preco, desconto, valorTotal, dia, mes));

        System.out.println("Venda registrada com sucesso! Valor total: R$" + valorTotal);
	        
        menuPrincipal();
	}
		
	public static void exibirVendas() {
		if (listaVendas.isEmpty()) {
			System.out.println("Nenhuma venda registrada ainda.");
			return;
		}
		System.out.println("-----Histórico de Vendas-----");
		for (CalculadoraGabrielinha venda : listaVendas) { // Itera sobre as vendas e imprime os detalhes
			System.out.println(venda);
		}
			
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
	
	public static void buscarVendasDia() {
		
		System.out.println("-----Buscando vendas por dia-----");
		
		System.out.println("Informe o dia da venda:");
		int dia = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Informe o mês da venda:");
		int mes = scan.nextInt();
		scan.nextLine();
		
		int totalVendas = 0;
		for (CalculadoraGabrielinha venda : listaVendas) {
			
			if(venda.getDia() == dia && venda.getMes() == mes) {
				
				totalVendas += venda.getQuantidade();
			}
		}
		
		System.out.println("Total de vendas no dia"+ dia +"/"+ mes+":"+ totalVendas);
		menuPrincipal();
	}
	
	public static void buscarVendasMes() {
		
		System.out.println("-----Buscando vendas por mês-----");
		
		System.out.println("Informe o mês da venda:");
		int mes = scan.nextInt();
		scan.nextLine();
		
		int totalVendas = 0;
		for (CalculadoraGabrielinha venda : listaVendas) {
			
			if (venda.getMes() == mes) {
				
				totalVendas += venda.getQuantidade();
			}
		}
		
		System.out.println("Total de vendas no mês "+ mes +": "+ totalVendas);
		menuPrincipal();
	}
}

