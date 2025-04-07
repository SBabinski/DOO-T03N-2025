package sistemaPrincipal;

import java.util.Scanner;

public class lojaGabrielinha {

	public static Scanner scan = new Scanner (System.in);
	
	static float escolhaM = 0;
	static float valort = 0;
	static float troco = 0;
	static int escolhaU = 0;
	static int escolha = 0;
	static float recebido = 0; 
	static float quant = 0;
	static float valorTotal = 0;
    static float valorComDesc = 0; 
    static float quantCompra = 0;
    static float valorDeCompra = 0;
    static int quantDesconto = 0;
    static float valorDescontos = 0;
	
	 private static final float preçoRosa = 6.00f;
	 private static final float preçoTulipa = 25.00f;
	 private static final float preçoOrquidea = 245.00f;
	 private static final float preçobeijinho = 2.00f;
	 private static final float preçoMargarida = 10.00f;


	public static void main(String[] args) {
		
		menu();
		
	}
	
	public static void menu (){
		
		System.out.println("\n");
		System.out.println("--------OPÇÕES DE MENU--------");
		
		System.out.println("1-  CALCULAR PREÇO TOTAL");
		System.out.println("2-  CALCULAR O TROCO");
		System.out.println("3-  VER RELATÓRIO");
        System.out.println("0-  SAIR DO SISTEMA");
		
		System.out.println(" O que deseja fazer?");
		escolha = scan.nextInt();
		
		if(escolha == 1) {
			
			calcularTotal();
			
		}else if (escolha == 2) {
				
			calcularTroco();
		
		}else if (escolha == 3) {
			
			verRelatorio();
			
		}else if(escolha == 0) {
			
			System.out.println("Saindo do sistema, até mais...");
			System.exit(0);
			
			
			
		}else {
			
			System.out.println("O numero selecionado não corresponde a nenhuma opção");
			System.out.println("Tente novamente...");
		
		}
			
	}

	public static void calcularTotal () {
		
		System.out.println( "Qual foi o produto escolhido?");
		
		System.out.println(" 1 - ROSAS");
		System.out.println(" 2 - TULIPAS");
		System.out.println(" 3 - MARGARIDAS");
		System.out.println(" 4 - ORQUIDEAS");
		System.out.println(" 5 - BEIJINHOS");
		
		escolhaU = scan.nextInt();
		
		System.out.println("Qual a quantidade escolhida?");
		 quant = scan.nextInt();
		
		if(escolhaU == 1) {
			
		valorTotal = preçoRosa * quant;
		quantCompra = quantCompra + quant;
		
			
		}else if (escolhaU == 2) {
			
		 valorTotal = preçoTulipa * quant;
		 quantCompra = quantCompra + quant;
			
		}else if (escolhaU == 3) {
			
		 valorTotal = preçoMargarida * quant;
		 quantCompra = quantCompra + quant;
			
		}else if (escolhaU == 4) {
			
		valorTotal = preçoOrquidea * quant;
		quantCompra = quantCompra + quant;
			
		}else if (escolhaU == 5) {
			
		 valorTotal = preçobeijinho * quant;
		 quantCompra = quantCompra + quant;
			
		}else {
			
			System.out.println("O numero selecionado não corresponde a nenhuma opção");
			System.out.println("Tente novamente...");
			
			calcularTotal();
		}
		
		if(quant > 10) {
			
			valorComDesc = valorTotal * 0.95f;
			valorDeCompra = valorDeCompra + valorComDesc;
			quantDesconto++;
			valorDescontos = valorDescontos + (valorTotal * 0.05f);
			
		}else if (quant <= 10) {
			
			valorDeCompra = valorDeCompra + valorTotal;
			
		}
		
		System.out.printf("O valor total da compra é: %.2f \n", valorTotal);
		System.out.printf(" O valor com desconto é: %.2f", valorComDesc);
		System.out.println("  ");
		calcularTroco();
		
	}
	
	public static void calcularTroco () {
		
		System.out.println("--------PARA CALCULAR O TROCO--------");
		System.out.println("1- Não quero inserir o valor total");
		System.out.println("2- Quero inserir o valor total");
		System.out.println("3- Voltar para o menu");
		
		escolhaM = scan.nextInt();
		
		if(escolhaM == 1) {
			
			if(valorTotal == 0) {
				
				System.out.println("Então calcule o valor primeiramente");
				calcularTotal();
				
			}
			
			if(quant > 10) {
				
				System.out.println("Vamos calcular o troco");
				System.out.println("Qual o valor recebido ?");
				float recebido = scan.nextFloat();
				
				
				 troco = recebido - valorComDesc;
				
			   if(troco < 0) {
					
					System.out.printf("Está faltando a quantia de %.2f" , troco);
					
				} else if(troco == 0 ) {
					
					System.out.println("Não precisa de troco, valor está certo!!");
					
				}else{
				
				System.out.printf("O valor do troco é: %.2f", troco);
				
				}
				
			}else if( quant <= 10) {
				
			
				System.out.println("--------VAMOS CALCULAR O TROCO--------");
				System.out.println("Qual o valor recebido ?");
				recebido = scan.nextFloat();
				
				
			   troco = recebido - valorTotal;
				
			   if(troco < 0) {
					
					System.out.printf("Está faltando a quantia de %.2f" , troco);
				}
			   else if(troco == 0 ) {
					
					System.out.println("Não precisa de troco, valor está certo!!");
				}else {
				
				System.out.printf("O valor do troco é: %.2f", troco);
				
		    	}
		
			}
			
		}else if (escolhaM == 2){
		
		System.out.println("Qual valor total da compra ?");
	     valort = scan.nextInt();
		
		System.out.println("Qual o valor recebido ?");
		recebido = scan.nextFloat();
		
		
		 troco = recebido - valort;
		
		 if(troco < 0) {
				
				System.out.printf("Está faltando a quantia de %.2f" , troco);
			}
		   else if(troco == 0 ) {
				
				System.out.println("Não precisa de troco, valor está certo!!");
			}else {
			
			System.out.printf("O valor do troco é: %.2f", troco);
			
			}	
	
	}if(escolhaM == 3) {
		
		menu();
		
	}else {
		
		System.out.println("O numero selecionado não corresponde a nenhuma opção");
		System.out.println("Tente novamente...");
		
		calcularTroco();
	}
	
	menu();
}
	
	public static void verRelatorio () {
		
		System.out.println("--------RELATÓRIO--------");
		System.out.printf("Total de plantas vendidas: %.2f \n", quantCompra);
		System.out.printf("O valor total das plantas vendidas é de: %.2f \n", valorDeCompra);
		System.out.printf("O quantidade de descontos aplicados é : %d, no valor de %.2f ", quantDesconto, valorDescontos);
			
	}

}
