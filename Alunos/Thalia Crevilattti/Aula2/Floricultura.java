import java.util.Scanner;

public class floricultura {

	public static void main(String[] args) {

	Scanner Scan = new Scanner(System.in);
	double totalSoma = 0;
	
		System.out.println("Bem-Vindo a Floricultura Gabrielinha's \n");
		System.out.println("MENU:");
        System.out.println(" "
        		+ "   [1] - Calcular Preço Total\r\n"
        		+ "    [2] - Calcular Troco    \r\n"
        		+ "    [3] - Sair");
        int opcao = Scan.nextInt();
        
        
        if(opcao == 1) { 
            
        	System.out.println("Quantos itens:\n");
        	int quant = Scan.nextInt();
        	
        	System.out.println("Valor do item:\n");
        	double valor = Scan.nextFloat();
        	 
        	
			totalSoma = quant * valor;
			
			System.out.println("O valor da compra é de: " + totalSoma);
		
			
        } else if (opcao == 2){
        	
        	System.out.println("Valor recebido: \n");
        	int receb = Scan.nextInt();
        	
        	System.out.println("Qual o valor total da compra:");
        	int total = Scan.nextInt();
        	
        	double troco = receb - total;
        	
        	System.out.println("Devolver ao cliente: " + troco);
        	
        	
        } else {
        	System.out.println("...Saindo do Sistema"
        			+ "Aguarde...");
         
        }
        
	return;
	
	}

}
