package tratamentos;

import java.util.InputMismatchException;  
import java.util.Scanner;

public class TratamentoDeErros {
	
	static Scanner scan = new Scanner(System.in);
	
	// Lê um número inteiro do usuário, tratando exceções de formato inválido.
	//// Captura exceção se a entrada não for um inteiro
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
	
	//Lê um número de ponto flutuante (double) do usuário, tratando exceções de formato inválido.
	// Captura exceção se a entrada não for um double
	public static double lerDouble(Scanner sacn, String mensagem) {
		
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
