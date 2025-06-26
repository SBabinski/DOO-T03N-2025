package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nome;
    private List<Serie> favoritos;
    private List<Serie> assistidas;
    private List<Serie> paraAssistir;

    public Usuario() {
        this.favoritos = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.paraAssistir = new ArrayList<>();
    }

    public Usuario(String nome) {
        this();
        this.nome = nome;
    }

    public String getNome() {
        return nome != null ? nome : "Usuário";
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Serie> getFavoritos() {
        if (favoritos == null) {
            favoritos = new ArrayList<>();
        }
        return favoritos;
    }

    public void setFavoritos(List<Serie> favoritos) {
        this.favoritos = favoritos != null ? favoritos : new ArrayList<>();
    }

    public List<Serie> getAssistidas() {
        if (assistidas == null) {
            assistidas = new ArrayList<>();
        }
        return assistidas;
    }

    public void setAssistidas(List<Serie> assistidas) {
        this.assistidas = assistidas != null ? assistidas : new ArrayList<>();
    }

    public List<Serie> getParaAssistir() {
        if (paraAssistir == null) {
            paraAssistir = new ArrayList<>();
        }
        return paraAssistir;
    }

    public void setParaAssistir(List<Serie> paraAssistir) {
        this.paraAssistir = paraAssistir != null ? paraAssistir : new ArrayList<>();
    }


    public void adicionarFavorito(Serie serie) {
        if (serie != null && !getFavoritos().contains(serie)) {
            getFavoritos().add(serie);
        }
    }

    public void removerFavorito(Serie serie) {
        getFavoritos().remove(serie);
    }

    public void adicionarAssistida(Serie serie) {
        if (serie != null && !getAssistidas().contains(serie)) {
            getAssistidas().add(serie);
        }
    }

    public void removerAssistida(Serie serie) {
        getAssistidas().remove(serie);
    }

    public void adicionarParaAssistir(Serie serie) {
        if (serie != null && !getParaAssistir().contains(serie)) {
            getParaAssistir().add(serie);
        }
    }

    public void removerParaAssistir(Serie serie) {
        getParaAssistir().remove(serie);
    }
}
