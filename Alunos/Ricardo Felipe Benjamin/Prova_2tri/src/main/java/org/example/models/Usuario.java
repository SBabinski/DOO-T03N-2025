package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;
    private List<Serie> favoritas = new ArrayList<>();
    private List<Serie> assistidas = new ArrayList<>();
    private List<Serie> desejoAssistir = new ArrayList<>();

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public List<Serie> getFavoritas() {
        return favoritas;
    }

    public List<Serie> getAssistidas() {
        return assistidas;
    }

    public List<Serie> getDesejoAssistir() {
        return desejoAssistir;
    }

    public void adicionarFavorita(Serie serie) {
        if (!favoritas.contains(serie)) {
            favoritas.add(serie);
        }
    }

    public void removerFavorita(Serie serie) {
        favoritas.removeIf(s -> s.getId() == serie.getId());
    }

    public void adicionarAssistida(Serie serie) {
        if (!assistidas.contains(serie)) {
            assistidas.add(serie);
        }
    }

    public void removerAssistida(Serie serie) {
        assistidas.removeIf(s -> s.getId() == serie.getId());
    }

    public void adicionarDesejoAssistir(Serie serie) {
        if (!desejoAssistir.contains(serie)) {
            desejoAssistir.add(serie);
        }
    }

    public void removerDesejoAssistir(Serie serie) {
        desejoAssistir.removeIf(s -> s.getId() == serie.getId());
    }
}
