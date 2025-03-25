package tratamentos;

import java.util.InputMismatchException; 
import java.util.Scanner;

public class TratamentoDeErros {
	
	static Scanner scan = new Scanner(System.in);
	
	public static int lerInteiro(Scanner scan, String mensagem) {
		
		while(true) {
			try {
				System.out.println(mensagem);
				return scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Digite um número inteiro.");
				scan.next();
			}
		}
		
	}
	
	public static double lerDouble(Scanner scan, String mensagem) {
		
		while (true) {
			try {
				System.out.println(mensagem);
				return scan.nextDouble();
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Digite um número válido.");
				scan.next();

			}
		}
		
    }

}
