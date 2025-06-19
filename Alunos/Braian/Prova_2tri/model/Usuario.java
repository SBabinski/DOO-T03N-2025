package com.braian.seriestracker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {

    private final String nome;
    private final List<Serie> favoritos;
    private final List<Serie> assistidas;
    private final List<Serie> desejadas;

    public Usuario(String nome) {
        this.nome = nome;
        this.favoritos = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.desejadas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Serie> getFavoritos() {
        return Collections.unmodifiableList(favoritos);
    }

    public void addFavorito(Serie serie) {
        if (!favoritos.contains(serie)) {
            favoritos.add(serie);
        }
    }

    public void removeFavorito(Serie serie) {
        favoritos.remove(serie);
    }

    public List<Serie> getAssistidas() {
        return Collections.unmodifiableList(assistidas);
    }

    public void addAssistida(Serie serie) {
        if (!assistidas.contains(serie)) {
            assistidas.add(serie);
        }
    }

    public void removeAssistida(Serie serie) {
        assistidas.remove(serie);
    }

    public List<Serie> getDesejadas() {
        return Collections.unmodifiableList(desejadas);
    }

    public void addDesejada(Serie serie) {
        if (!desejadas.contains(serie)) {
            desejadas.add(serie);
        }
    }

    public void removeDesejada(Serie serie) {
        desejadas.remove(serie);
    }

    // Métodos para obter as listas modificáveis para ordenação no GerenciadorDeSeries
    public List<Serie> getModifiableFavoritos() {
        return favoritos;
    }

    public List<Serie> getModifiableAssistidas() {
        return assistidas;
    }

    public List<Serie> getModifiableDesejadas() {
        return desejadas;
    }
}

