package Main;

import java.util.Scanner;

import Servicos.Listas;
import Servicos.Requisicao; 

public class App {
	
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		 
	        int resp = 0;

	        Listas.carregarUsuario();
	        while (resp != 6) {
	            System.out.println("\nMenu de Series \n"
	                    + "1 - Pesquisar serie \n"
	                    + "2 - Listar listas de series \n"
	                    + "3 - Deletar uma serie dos favoritos \n"
	                    + "4 - Deletar uma serie dos ver mais tarde \n"
	                    + "5 - Deletar uma serie dos assistidos \n"
	                    + "6 - Fechar Programa");

	            System.out.print("Escolha uma opção: ");
	            String input = scan.nextLine();

	            try {
	                resp = Integer.parseInt(input); 

	                switch (resp) {
	                    case 1:
	                        Requisicao.MostrarPrograma();
	                        break;
	                    case 2:
	                    	
	                    Integer list = null;
	                    try {
	                    	System.out.println("digite qual lista quer listar \n"
	                    			+ "1 - Lista de favoridos \n"
	                    			+ "2 - Lista de assistir mais tarde \n"
	                    			+ "3 - Lista de assistidos \n");
	                    	list = scan.nextInt();
						} catch (Exception e) {
							System.out.println("apenas digitos podem ser digitado");
						}
	                    
	                    try {
	                    	System.out.println("Selecione uma ordem de lista \n"
	                        		+ "1 - Ordem alfabetica \n"
	                        		+ "2 - Ordem por nota \n"
	                        		+ "3 - Ordem por data de lancamento \n"
	                        		+ "4 - Ordem por encerrado \n");
	                        Integer ope = scan.nextInt();
	                        	switch (ope) {
								case 1: {
									Listas.listarOrdemAlfabetica(list);
									break;
								}case 2: {
									Listas.ordenarFavoritosPorNota(list);
									break;
								}
								case 3: {
									Listas.ordenarFavoritosPorData(list);
									break;
								}
								case 4: {
									Listas.ordenarFavoritosPorEstado(list);
									break;
								}
								default:
									System.out.println("valor digitado incorreto");;
								}
	                        break;
							} 	catch (Exception e) {
									System.out.println("apenas digitos podem ser digitado");
							}
		                      
	                    
	                    case 3:
	                    	try {
	                    		System.out.println("Digite o id da serie que deseja deletar");
		                        Integer idF = scan.nextInt();
		                        Listas.removeSerieFavorita(idF);
							} catch (Exception e) {
								System.out.println("Id da serie incorreto");
							}
	                        break;
	                    case 4:
	                    	try {
	                    		System.out.println("Digite o id da serie que deseja deletar");
		                        Integer idA = scan.nextInt();
		                        Listas.removeAssistir(idA);
							} catch (Exception e) {
								System.out.println("Id da serie incorreto");
							}
	                        break;
	                    case 5:
	                    	try {
								System.out.println("Digite o id da serie que deseja deletar");
		                        Integer idV = scan.nextInt();
		                        Listas.removeVistos(idV);
							} catch (Exception e) {
								System.out.println("Id da serie incorreto");
							}
	                    	
	                        break;
	                    case 6:
	                    	Listas.salvarUsuario();
	                        System.out.println("Programa encerrado.");
	                        break;
	                    default:
	                        System.out.println("Opção inválida. Tente novamente.");
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("Entrada inválida! Digite apenas números.");
	            }
	        }

	        scan.close();
	    }

}
