package com.trabalhotvmaze.series;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo que representa um usuário do sistema
 * Armazena o nome e as listas de séries de forma personalizada.
 */

public class Usuario {
    private String nome;
    private List<Serie> favoritos = new ArrayList<>();
    private List<Serie> seriesAssistidas = new ArrayList<>();
    private List<Serie> seriesParaAssistir = new ArrayList<>();

    // Construtor vazio
    public Usuario() {} 

    //Construtor pra criar um novo usuário
    public Usuario(String nome) {
        this.nome = nome;
    }
    
    // Getters e Setters onde o Jackson acessa e preenche os campos
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public List<Serie> getFavoritos() { return favoritos; }
    public void setFavoritos(List<Serie> favoritos) { this.favoritos = favoritos; }
    public List<Serie> getSeriesAssistidas() { return seriesAssistidas; }
    public void setSeriesAssistidas(List<Serie> seriesAssistidas) { this.seriesAssistidas = seriesAssistidas; }
    public List<Serie> getSeriesParaAssistir() { return seriesParaAssistir; }
    public void setSeriesParaAssistir(List<Serie> seriesParaAssistir) { this.seriesParaAssistir = seriesParaAssistir; }

    // Métodos para manipular listas de séries
    public void adicionarFavorito(Serie serie) {
        if (!favoritos.contains(serie)) {
            favoritos.add(serie);
            System.out.println("'" + serie.getNome() + "' adicionada aos favoritos.");
        } else {
            System.out.println("Série já está nos favoritos.");
        }
    }

    public void removerFavorito(Serie serie) {
        if(favoritos.remove(serie)) {
             System.out.println("'" + serie.getNome() + "' removida dos favoritos.");
        }
    }

    public void adicionarAssistida(Serie serie) {
        if (!seriesAssistidas.contains(serie)) {
            seriesAssistidas.add(serie);
            System.out.println("'" + serie.getNome() + "' adicionada às séries assistidas.");
        } else {
            System.out.println("Série já está na lista de assistidas.");
        }
    }
    
    public void removerAssistida(Serie serie) {
        if(seriesAssistidas.remove(serie)) {
             System.out.println("'" + serie.getNome() + "' removida das séries assistidas.");
        }
    }

    public void adicionarParaAssistir(Serie serie) {
        if (!seriesParaAssistir.contains(serie)) {
            seriesParaAssistir.add(serie);
            System.out.println("'" + serie.getNome() + "' adicionada à lista 'Para Assistir'.");
        } else {
            System.out.println("Série já está na lista 'Para Assistir'.");
        }
    }
    
    public void removerParaAssistir(Serie serie) {
        if(seriesParaAssistir.remove(serie)) {
            System.out.println("'" + serie.getNome() + "' removida da lista 'Para Assistir'.");
        }
    }
}
