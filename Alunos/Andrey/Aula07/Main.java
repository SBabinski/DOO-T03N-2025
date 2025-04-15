package ClassePrincipal;

import java.util.Scanner;

import Objeto.Cliente;
import Objeto.Loja;
import Objeto.Vendedor;

public class Main {

	private static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		menu();

	}
	
	public static void menu() {
		Integer resp =0;
		while(resp != 9){
		System.out.println("menu principal \n");
		System.out.println(
				  "1 - Visualizar Loja \n"
				+ "2 - Adicionar Cliente \n"
				+ "3 - Visualizar Cliente \n"
				+ "4 - Contar Cliente \n"
				+ "5 - Adicionar Vendedor \n"
				+ "6 - Mostrar Vendedor \n"
				+ "7 - Mostrar Bonus e Media salarial \n"
				+ "8 - Contar Vendedores \n"
				+ "9 - Sair");
		resp = scan.nextInt();
		if(resp == 1) {
			Loja.visualizar();
		} else if(resp == 2) {
			Cliente.addCliente();
		} else if(resp == 3) {
			Cliente.mostrarCliente();
		} else if(resp == 4) {
			Cliente.contarCliente();
		} else if(resp == 5) {
			Vendedor.addVendedor();
		} else if(resp == 6) {
			Vendedor.mostrarVendedor();
		} else if(resp == 7) {
			Vendedor.apresentarMediaAndBonus();
		} else if(resp == 8) {
			Vendedor.contarVendedor();
		}else {
			resp =9;
		}
	}

}
}
