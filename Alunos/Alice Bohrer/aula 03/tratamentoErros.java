package classes;

import java.util.Scanner;
import java.util.InputMismatchException;
public class tratamentoErros {
	
  static Scanner scan = new Scanner(System.in);
	
	public static int lerValorInteiro (Scanner scan, String mensagem) {
		
	while(true) {
		
		try {
			
			System.out.println(mensagem);
			return scan.nextInt();
			
		}catch(InputMismatchException e) {
			
			System.out.println("Está incorreto, insira um valor inteiro");
			scan.next();
				
		}
		
	}
	
	}
	
	public static int lerValordouble (Scanner scan, String mensagem) {
		
		while(true) {
			
			try {
				
				System.out.println(mensagem);
				return scan.nextInt();
				
			}catch(InputMismatchException e) {
				
				System.out.println("Está incorreto, insira um valor inteiro");
				scan.next();

}
		}
	}

}