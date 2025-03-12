package sistemaGabrielinha;

import java.util.Scanner;

public class sistemaPrincipalGabrielinha {
	
	public static Scanner scan = new Scanner (System.in);
	
	static float valorTotal = 0;

	
	 private static final float preçoRosa = 6.50f;
	 private static final float preçoTulipa = 25.75f;
	 private static final float preçoOrquidea = 245.42f;
	 private static final float preçobeijinho = 2.50f;
	 private static final float preçoMargarida = 10.00f;


	public static void main(String[] args) {
		
		menu();
		
	}
	
	public static void menu (){
		
		System.out.println("\n");
		System.out.println("Opções do menu");
		
		System.out.println("1-  CALCULAR PREÇO TOTAL");
		System.out.println("2-  CALCULAR O TROCO");
		System.out.println("3-  SAIR DO SISTEMA");
		
		System.out.println(" O que deseja fazer?");
		int escolha = scan.nextInt();
		
		if(escolha == 1) {
			
			calcularTotal();
			
		}else if (escolha == 2) {
				
			calcularTroco();
		
		}else {
			
			System.out.println("Saindo do sistema, até mais...");
			System.exit(0);
			
		}
			
	}

	public static void calcularTotal () {
		
		System.out.println( "Qual foi o produto escolhido?");
		
		System.out.println(" 1 - rosas");
		System.out.println(" 2 - Tulipas");
		System.out.println(" 3 - margaridas");
		System.out.println(" 4 - orquideas");
		System.out.println(" 5 - beijinhos");
		
		int escolhaU = scan.nextInt();
		
		System.out.println("Qual a quantidade escolhida?");
		int quant = scan.nextInt();
		
		if(escolhaU == 1) {
			
		valorTotal = preçoRosa * quant;
			
		}else if (escolhaU == 2) {
			
		 valorTotal = preçoTulipa * quant;
			
		}else if (escolhaU == 3) {
			
		 valorTotal = preçoMargarida * quant;
			
		}else if (escolhaU == 4) {
			
		valorTotal = preçoOrquidea * quant;
			
		}else if (escolhaU == 5) {
			
		 valorTotal = preçobeijinho * quant;
			
		}
		
		System.out.printf("o valor total da compra é: %f", valorTotal);
		System.out.println("  ");
		calcularTroco();
		
	}
	
	public static void calcularTroco () {
		
		System.out.println("PARA CALCULAR O TROCO");
		System.out.println("1- não quero inserir o valor total");
		System.out.println("2- quero inserir o valor total");
		
		int escolhaM = scan.nextInt();
		
		if(escolhaM == 1) {
			
			if(valorTotal == 0) {
				
				System.out.println("Então calcule o valor primeiramente");
				calcularTotal();
				
			}
			
		System.out.println("vamos calcular o troco");
		System.out.println("qual o valor recebido ?");
		float recebido = scan.nextFloat();
		
		
		float troco = recebido - valorTotal;
		
	   if(troco < 0) {
			
			System.out.printf("esta faltando a quantia de %f" , troco);
		}
	   else if(troco == 0 ) {
			
			System.out.println("não precisa de troco valor certo!!");
		}else {
		
		System.out.printf("o valor do troco é: %f", troco);
		
		}
		
	}else {
		
		System.out.println("qual valor total da compra ?");
		float valort = scan.nextInt();
		
		System.out.println("qual o valor recebido ?");
		float recebido = scan.nextFloat();
		
		
		float troco = recebido - valort;
		
		 if(troco < 0) {
				
				System.out.printf("esta faltando a quantia de %f" , troco);
			}
		   else if(troco == 0 ) {
				
				System.out.println("não precisa de troco valor certo!!");
			}else {
			
			System.out.printf("o valor do troco é: %f", troco);
			
			}	
	
	}
	
	menu();
}
}
