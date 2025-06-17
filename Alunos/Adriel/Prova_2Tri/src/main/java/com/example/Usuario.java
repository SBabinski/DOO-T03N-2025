package com.example;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String apelido;
    private List<Serie> favoritas = new ArrayList<>();
    private List<Serie> assistidas = new ArrayList<>();
    private List<Serie> paraAssistir = new ArrayList<>();

    public Usuario(String apelido) {
        this.apelido = apelido;
    }

    // Getters e Setters
    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = apelido; }
    public List<Serie> getFavoritas() { return favoritas; }
    public List<Serie> getAssistidas() { return assistidas; }
    public List<Serie> getParaAssistir() { return paraAssistir; }

    // Métodos para adicionar/remover séries
    public void adicionarFavorita(Serie serie) {
        if (!favoritas.contains(serie)) favoritas.add(serie);
    }

    public void removerFavorita(Serie serie) {
        favoritas.remove(serie);
    }

    public void adicionarAssistida(Serie serie) {
        if (!assistidas.contains(serie)) assistidas.add(serie);
    }

    public void removerAssistida(Serie serie) {
        assistidas.remove(serie);
    }

    public void adicionarParaAssistir(Serie serie) {
        if (!paraAssistir.contains(serie)) paraAssistir.add(serie);
    }

    public void removerParaAssistir(Serie serie) {
        paraAssistir.remove(serie);
    }
}