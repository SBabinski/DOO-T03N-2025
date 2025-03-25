package aula03;

import java.util.ArrayList;

import java.util.List;

import java.util.Scanner;

import objetos03.Vendas;



public class Principal03 {

	static Scanner scanner = new Scanner(System.in);

	   static List<Vendas> RegistroVendas = new ArrayList<>();
	   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
			menuPrincipal();

		}

		
		public static void menuPrincipal(){

			System.out.println("Bem-vindo ao sistema de gerenciamento de floricultura!");

			System.out.println("Escolha a opção desejada:\n1-Calcular preço.\n2-Calcular troco.\n3-Exibir registro de vendas.\n4-sair");

			int op= scanner.nextInt();

			if(op==1) { calcularPreco();

			}

			else if (op==2) { calcularTroco();

			}

			else if(op==3) {

				RegistroVenda();

			}

			else if(op==4) {

				System.out.println("Sistema encerrado!");

				System.exit(4);

			}

			else {

				System.out.println("ERRO- digite uma opção de 1 a 4!");

			}

		}

		

		public static void calcularPreco() {

		

		System.out.println("Dona Gabrielinha, insira a quantidade de plantas que foram vendidas:");

		int quant = scanner.nextInt();

		System.out.println("Ok!\nAgora insira o valor unitário:");

		double valoruni= scanner.nextDouble();

		

		double total = quant*valoruni;

		double desc =0;

		

		if(quant>10) { desc = total*0.05; }

		

		double totaldesc = total- desc;

		

		

		RegistroVendas.add(new Vendas(quant,valoruni, totaldesc, desc));

		System.out.printf("Total: %.2f\nDesconto concedido: %.2f\n", totaldesc, desc);

		

		menuPrincipal();

		

		}

	

		public static void RegistroVenda() {

			if(RegistroVendas.isEmpty()) {

				System.out.println("Nenhuma venda registrada!");

			}

			else {

			System.out.println("Registro de vendas:");

			for(Vendas venda : RegistroVendas) {

				System.out.printf("Quantidade: %d | Valor unitário: %.2f | Valor total: %.2f | Desconto: %.2f\n", venda.getQuant(),venda.getValoruni() , venda.getTotal(), venda.getDesc());

			}}

			

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
