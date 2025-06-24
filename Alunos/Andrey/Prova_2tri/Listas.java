package Servicos;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import ArquivoJSON.JSONService;
import Objetos.Serie;
import Objetos.Usuario;

public class Listas {
	static Usuario usuario;
    static List<Serie> favoritos = new ArrayList<Serie>();
    static List<Serie> assistir = new ArrayList<Serie>();
    static List<Serie> vistos = new ArrayList<Serie>();
    private static Scanner scan = new Scanner(System.in);
    
    //carregar usuario
    public static void carregarUsuario() {
    	System.out.println("Digite nome do usuario");
        String nome = scan.next();
	    	if (!new File(nome+".json").exists()) {
	            usuario = new Usuario();
	            usuario.setName(nome);
	            System.out.println("Seja bem vindo " + usuario.getName() + "Bem vindo");
	        } else {
	            usuario = JSONService.lerJSON(nome);
	            favoritos = usuario.getFavoritos();
	            assistir = usuario.getAssistir();
	            vistos = usuario.getVistos();
	        }
    }
    
    
    //salvar usuario
    public static void salvarUsuario() {
    	usuario.setFavoritos(favoritos);
    	usuario.setAssistir(assistir);
    	usuario.setVistos(vistos);
    	
    	JSONService.writeJson(usuario);
    }
    
    //adicionar nas listas
    public static void addFavoritos(Serie serie) {	
		favoritos.add(new Serie(serie.getNome(),serie.getLingua(),serie.getGenero(),serie.getNota(),serie.getDataInicio(),serie.getDataFim(),
			serie.getCanal(),serie.getEstado()));
	}
	
	public static void addAssistir(Serie serie) {
		assistir.add(new Serie(serie.getNome(),serie.getLingua(),serie.getGenero(),serie.getNota(),serie.getDataInicio(),serie.getDataFim(),
				serie.getCanal(),serie.getEstado()));
	}
	
	public static void addVistos(Serie serie) {
		vistos.add(new Serie(serie.getNome(),serie.getLingua(),serie.getGenero(),serie.getNota(),serie.getDataInicio(),serie.getDataFim(),
				serie.getCanal(),serie.getEstado()));
	}
	
	
	//remover nas listas
	public static void removeSerieFavorita(Integer id) {
		try {
			favoritos.removeIf(favoritos -> favoritos.getId() == id);
		}
		 catch (Exception e) {
			System.out.println("nao foi possivel remover");
		}		
	}
	
	public static void removeAssistir(Integer id) {
		try {
			assistir.removeIf(assisir -> assisir.getId() == id);
			
		} catch (Exception e) {
			System.out.println("nao foi possivel remover");
		}
	}
	
	public static void removeVistos(Integer id) {
		try {
			vistos.removeIf(vistos -> vistos.getId() == id);
		} catch (Exception e) {
			System.out.println("nao foi possivel remover");
		}
	}
	
	
	//listar as listas
	public static void listarSerieFavorita() {
		for(Serie series : favoritos) {
			System.out.println("id da serie " + series.getId() + "\n"
					+ "nome da serie " + series.getNome() + "\n"
					+ "idioma da serie " + series.getLingua() + "\n"
					+ "genero da serie " + series.getGenero() + "\n"
					+ "nota da serie " + series.getNota() + "\n"
					+ "estado da serie " + series.getEstado() + "\n"
					+ "data de inicio da serie " + series.getDataInicio() + "\n"
					+ "data de fim da serie " + series.getDataFim() + "\n"
					+ "emisor " + series.getCanal() + "\n"
					);
		}
	}
	
	public static void listarAssistir() {
		for(Serie series : assistir) {
			System.out.println("id da serie " + series.getId() + "\n"
					+ "nome da serie " + series.getNome() + "\n"
					+ "idioma da serie " + series.getLingua() + "\n"
					+ "genero da serie " + series.getGenero() + "\n"
					+ "nota da serie " + series.getNota() + "\n"
					+ "estado da serie " + series.getEstado() + "\n"
					+ "data de inicio da serie " + series.getDataInicio() + "\n"
					+ "data de fim da serie " + series.getDataFim() + "\n"
					+ "emisor " + series.getCanal() + "\n"
					);
		}
	}
	
	public static void listarVistos() {
		for(Serie series : vistos) {
			System.out.println("id da serie " + series.getId() + "\n"
					+ "nome da serie " + series.getNome() + "\n"
					+ "idioma da serie " + series.getLingua() + "\n"
					+ "genero da serie " + series.getGenero() + "\n"
					+ "nota da serie " + series.getNota() + "\n"
					+ "estado da serie " + series.getEstado() + "\n"
					+ "data de inicio da serie " + series.getDataInicio() + "\n"
					+ "data de fim da serie " + series.getDataFim() + "\n"
					+ "emisor " + series.getCanal() + "\n"
					);
		}
	}
	
	
	//ordenar listas por filtro
	public static void listarOrdemAlfabetica(Integer ope) {
		switch (ope) {
		case 1: {
				Collections.sort(favoritos, Comparator.comparing(Serie::getNome));
				listarSerieFavorita();
			break;
		}
		case 2: {
				Collections.sort(assistir, Comparator.comparing(Serie::getNome));
				listarAssistir();	
			break;
		}	
		case 3: {
				Collections.sort(vistos, Comparator.comparing(Serie::getNome));
				listarVistos();
			break;
		}
		default:
			System.out.println("valor incorreto");
		}
	}
	
	public static void ordenarFavoritosPorNota(Integer ope) {
		switch (ope) {
		case 1: {
				Collections.sort(favoritos, Comparator.comparingDouble(Serie::getNota));
				listarSerieFavorita();
			break;
		}
		case 2: {
				Collections.sort(assistir, Comparator.comparingDouble(Serie::getNota));
				listarAssistir();	
			break;
		}	
		case 3: {
				Collections.sort(vistos, Comparator.comparingDouble(Serie::getNota));
				listarVistos();
			break;
		}
		default:
			System.out.println("valor incorreto");
		}
	}
	
	public static void ordenarFavoritosPorData(Integer ope) {
		switch (ope) {
		case 1: {
				favoritos.sort(Comparator.comparing(Serie::getDataInicio));
				listarSerieFavorita();
			break;
		}
		case 2: {
				assistir.sort(Comparator.comparing(Serie::getDataInicio));
				listarAssistir();	
			break;
		}	
		case 3: {
				vistos.sort(Comparator.comparing(Serie::getDataInicio));
				listarVistos();
			break;
		}
		default:
			System.out.println("valor incorreto");
		}
		
	}
	
	public static void ordenarFavoritosPorEstado(Integer ope) {
		// Palavra a ser usada como critério de ordenação
        String palavraChave = "ended";
        
        switch (ope) {
		case 1: {
				favoritos.sort(Comparator.comparingInt(Serie -> 
	            Serie.getEstado().contains(palavraChave) ? 0 : 1));
				listarSerieFavorita();
			break;
		}
		case 2: {
				assistir.sort(Comparator.comparingInt(Serie -> 
	            Serie.getEstado().contains(palavraChave) ? 0 : 1));
				listarAssistir();	
			break;
		}	
		case 3: {
				vistos.sort(Comparator.comparingInt(Serie -> 
	            Serie.getEstado().contains(palavraChave) ? 0 : 1));
				listarVistos();
			break;
		}
		default:
			System.out.println("valor incorreto");
		}
	}

}
