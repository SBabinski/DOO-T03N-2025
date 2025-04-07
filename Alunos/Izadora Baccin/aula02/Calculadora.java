//progrqmar calculadora
import java.util.Scanner;

public class Calculadora {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); 
		System.out.println("1-Calcular Preço Total ");
		System.out.println("2-Calcular Troco ");
		System.out.println("3-Sair ");
		int a = scan.nextInt();
		
		if(a==1) {
			System.out.println("Qual o nome da planta? ");
			String nome = scan.next();
			System.out.println("Qual o preço? ");
			float preco = scan.nextFloat();
			System.out.println("Quantas unidades? ");
			int unid = scan.nextInt();
			
			float PrecoTotal = preco*unid;
			System.out.printf("O valor total da compra de %d %s é: $%.2f" ,unid,nome,PrecoTotal);
		}if(a==2) {
			System.out.println("Valor total da compra: ");
			float total = scan.nextFloat();
			System.out.println("Valor recebido: ");
			float rec = scan.nextFloat();
			
			float troco = rec-total;
			System.out.println("O valor do troco e de: $"+troco);
		}
     scan.close();
	}

}
