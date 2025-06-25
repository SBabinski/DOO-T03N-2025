package com.apiseries;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private List<Serie> favoritas;
    private List<Serie> assistidas;
    private List<Serie> desejo;

    public Usuario(String nome) {
        this.nome = nome;
        this.favoritas = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.desejo = new ArrayList<>();
    }

    public Usuario() {
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

    public List<Serie> getDesejadas() {
        return desejo;
    }

    public void adicionarFavorita(Serie serie) {
        favoritas.add(serie);
        System.out.println("Usuário " + this.getNome() + " adicionou a serie " + serie.getNome() + " como favorita");
    }

    public void removerFavorita(Serie serie) {
        favoritas.remove(serie);
        System.out.println("Usuário " + this.getNome() + " removeu a serie " + serie.getNome() + " como favorita");
    }

    public void adicionarAssistida(Serie serie) {
        assistidas.add(serie);
        System.out.println("Usuário " + this.getNome() + " adicionou a serie " + serie.getNome() + " como assistida");
    }

    public void removerAssistida(Serie serie) {
        assistidas.remove(serie);
        System.out.println("Usuário " + this.getNome() + " removeu a serie " + serie.getNome() + " como assistida");
    }

    public void adicionarDesejada(Serie serie) {
        desejo.add(serie);
        System.out.println("Usuário " + this.getNome() + " adicionou a serie " + serie.getNome() + " como desejada");
    }

    public void removerDesejada(Serie serie) {
        desejo.remove(serie);
        System.out.println("Usuário " + this.getNome() + " removeu a serie " + serie.getNome() + " como desejada");
    }
}
