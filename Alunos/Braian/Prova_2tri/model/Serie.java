package com.braian.seriestracker.model;

import java.util.ArrayList;
import java.util.List;

public class Serie {

    private int id;
    private String titulo;
    private String idioma;
    private List<String> generos;
    private double nota;
    private String status;
    private String dataEstreia;
    private String dataTermino;
    private String emissora;

    public Serie() {
        this.generos = new ArrayList<>();
    }

    public Serie(int id, String titulo, String idioma, List<String> generos, double nota, String status, String dataEstreia, String dataTermino, String emissora) {
        this.id = id;
        this.titulo = titulo;
        this.idioma = idioma;
        this.generos = new ArrayList<>(generos);
        this.nota = nota;
        this.status = status;
        this.dataEstreia = dataEstreia;
        this.dataTermino = dataTermino;
        this.emissora = emissora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataEstreia() {
        return dataEstreia;
    }

    public void setDataEstreia(String dataEstreia) {
        this.dataEstreia = dataEstreia;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getEmissora() {
        return emissora;
    }

    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }

    @Override
    public String toString() {
        return "Série: " + titulo +
                " \n | Idioma: " + idioma +
                " \n | Gêneros: " + generos +
                " \n | Nota: " + nota +
                " \n | Status: " + ("Running".equals(status) ? "Em Andamento" : "Encerrada") +
                " \n | Estreia: " + dataEstreia +
                " \n | Término: " + (dataTermino != null ? dataTermino : "Em exibição") +
                " \n | Emissora: " + (emissora != null && !emissora.isEmpty() ? emissora : "N/A");
    }
}

