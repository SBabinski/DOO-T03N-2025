package aula04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import objetos04.Vendas;


public class Principal04 {
	
	static Scanner scanner = new Scanner(System.in);
	   static List<Vendas> RegistroVendas = new ArrayList<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menuPrincipal();

	}
	
	public static void menuPrincipal(){
		System.out.println("Bem-vindo ao sistema de gerenciamento de loja de vendas de plantas!");
		System.out.println("Escolha a opção desejada:\n1-Calcular preço.\n2-Calcular troco."
				+ "\n3-Registrar venda por data.\n4-Buscar venda por data. \n5-Exibir registro de vendas.\n"
				+ "6-sair");
		int op= scanner.nextInt();
		
		if(op==1) { calcularPreco();
		}
		else if (op==2) { calcularTroco();
		}
		else if(op==3) { registrarVendacomdata();
		}
		else if(op==4) { buscarVendapordata();
		}
		else if(op==5) {  RegistroVenda();
		}
		else if(op==6) {
			System.out.println("Sistema encerrado!");
			System.exit(6);
		}
		else {
			System.out.println("ERRO- digite uma opção de 1 a 6!");
		}
	}
	
	public static void calcularPreco() {
	
	System.out.println("Dona Gabrielinha, insira por gentileza a quntidade da planta desejada vendida:");
	int quant = scanner.nextInt();
	System.out.println("Certo!\nAgora insira o valor unitário:");
	double valoruni= scanner.nextDouble();
	
	double total = quant*valoruni;
	double desc =0;
	
	if(quant>10) { desc = total*0.05; }
	
	double totaldesc = total- desc;
	
	System.out.printf("Total: %.2f\nDesconto concedido: %.2f\n", totaldesc, desc);
	
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
	
	
	public static void registrarVendacomdata() {
		
		System.out.println("Dona Gabrielinha, insira por gentileza a quntidade da planta desejada vendida:");
		int quant = scanner.nextInt();
		System.out.println("Certo!\nAgora insira o valor unitário:");
		double valoruni= scanner.nextDouble();
		System.out.println("Informe o dia da venda:");
		int dia = scanner.nextInt();
		System.out.println("Informe o mês da venda:");
		int mes = scanner.nextInt();
		
		
		
		double total = quant*valoruni;
		double desc =0;
		
		if(quant>10) { desc = total*0.05; }
		
		double totaldesc = total- desc;
		
		
		RegistroVendas.add(new Vendas(quant,valoruni, totaldesc, desc,dia,mes));
		System.out.println("Venda registrada!");
		
		menuPrincipal();
		
		}
	
	public static void buscarVendapordata() {
		
		System.out.println("Qual o dia da venda que deseja buscar?");
		int dia = scanner.nextInt();
		System.out.println("Qual o mes da venda que deseja buscar?");
		int mes = scanner.nextInt();
		
		int quantVendas=0;
		double valorTotal=0;
		
		for (Vendas venda : RegistroVendas) {
            if (venda.getDia() == dia && venda.getMes() == mes) {
                quantVendas += venda.getQuant();
                valorTotal += venda.getTotal();
            }}
		
		System.out.printf("No dia %d/%d houve %d vendas, totalizando R$ %.2f\n",
				dia, mes, quantVendas, valorTotal);
		
		menuPrincipal();
	}
	
	
	public static void RegistroVenda() {
		if(RegistroVendas.isEmpty()) {
			System.out.println("Nenhuma venda registrada!");
		}
		else {
		System.out.println("Registro de vendas:");
		for(Vendas venda : RegistroVendas) {
			System.out.printf("Quantidade: %d | Valor unitário: %.2f | Valor total: %.2f | Desconto: %.2f | Data: %d/%d\n",
					venda.getQuant(),venda.getValoruni() , venda.getTotal(), venda.getDesc(), venda.getDia(), venda.getMes()); }
		     }
		
		menuPrincipal();
	}
	

}
