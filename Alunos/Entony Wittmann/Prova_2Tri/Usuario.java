package org.example;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private List<Serie> seriesFavoritas = new ArrayList<>();
    private List<Serie> seriesAssistidas = new ArrayList<>();
    private List<Serie> seriesParaAssistir = new ArrayList<>();

    public Usuario(String nome) {
        this.nome = nome;
    }

    public void adicionarFavorito(Serie serie) {
        seriesFavoritas.add(serie);
    }

    public void removerFavorito(String nome) {
        seriesFavoritas.removeIf(s -> s.getNome().equalsIgnoreCase(nome));
    }

    public void adicionarAssistida(Serie serie) {
        seriesAssistidas.add(serie);
    }

    public void removerAssistida(String nome) {
        seriesAssistidas.removeIf(s -> s.getNome().equalsIgnoreCase(nome));
    }

    public void adicionarParaAssistir(Serie serie) {
        seriesParaAssistir.add(serie);
    }

    public void removerParaAssistir(String nome) {
        seriesParaAssistir.removeIf(s -> s.getNome().equalsIgnoreCase(nome));
    }

    public List<Serie> getFavoritos() {
        return seriesFavoritas;
    }

    public List<Serie> getAssistidas() {
        return seriesAssistidas;
    }

    public List<Serie> getParaAssistir() {
        return seriesParaAssistir;
    }

    public String getNome() {
        return nome;
    }
}
