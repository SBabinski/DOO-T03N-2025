package seriesapp;

import java.util.ArrayList;
import java.util.List;

// Classe que guarda os dados do usuário e suas listas de séries
public class Usuario {
    private String nome;
    private List<Serie> favoritos;
    private List<Serie> assistidas;
    private List<Serie> desejoAssistir;

    public Usuario(String nome) {
        this.nome = nome;
        this.favoritos = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.desejoAssistir = new ArrayList<>();
    }

    // Getters para acessar as informações
    public String getNome() { return nome; }
    public List<Serie> getFavoritos() { return favoritos; }
    public List<Serie> getAssistidas() { return assistidas; }
    public List<Serie> getDesejoAssistir() { return desejoAssistir; }
}