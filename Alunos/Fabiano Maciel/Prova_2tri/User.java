package Prova_2tri;

import java.util.*;

public class User {
    private String nome;
    private List<Serie> favoritos;
    private List<Serie> assistidas;
    private List<Serie> desejaAssistir;

    public User(String nome) {
        this.nome = nome;
        this.favoritos = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.desejaAssistir = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public List<Serie> getFavoritos() { return favoritos; }
    public List<Serie> getAssistidas() { return assistidas; }
    public List<Serie> getDesejaAssistir() { return desejaAssistir; }
}