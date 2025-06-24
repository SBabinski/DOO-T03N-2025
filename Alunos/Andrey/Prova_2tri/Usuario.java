package Objetos;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	 String name;
	 List<Serie> favoritos = new ArrayList<Serie>();
	 List<Serie> assistir = new ArrayList<Serie>();
	 List<Serie> vistos = new ArrayList<Serie>();
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Serie> getFavoritos() {
		return favoritos;
	}
	public void setFavoritos(List<Serie> favoritos) {
		this.favoritos = favoritos;
	}
	public List<Serie> getAssistir() {
		return assistir;
	}
	public void setAssistir(List<Serie> assistir) {
		this.assistir = assistir;
	}
	public List<Serie> getVistos() {
		return vistos;
	}
	public void setVistos(List<Serie> vistos) {
		this.vistos = vistos;
	}
	
	 
}
