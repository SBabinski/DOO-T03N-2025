package org.example.models;

import java.util.List;

public class Serie {

    private int id;
    private String nome;
    private String idioma;
    private List<String> generos;
    private double notaGeral;
    private String estado;
    private String dataEstreia;
    private String dataTermino;
    private String emissora;

    public Serie() {
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIdioma() {
        return idioma;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public double getNota() {
        return notaGeral;
    }

    public String getStatus() {
        return estado;
    }

    public String getDataEstreia() {
        return dataEstreia;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public String getEmissora() {
        return emissora;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public void setNotaGeral(double notaGeral) {
        this.notaGeral = notaGeral;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDataEstreia(String dataEstreia) {
        this.dataEstreia = dataEstreia;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }

    @Override
    public String toString() {
        return "Nome: " + nome +
                "\nIdioma: " + idioma +
                "\nGêneros: " + generos +
                "\nNota: " + notaGeral +
                "\nStatus: " + estado +
                "\nEstreia: " + dataEstreia +
                "\nTérmino: " + dataTermino +
                "\nEmissora: " + emissora;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Serie)) return false;
        Serie outra = (Serie) obj;
        return this.id == outra.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
