package com.example;
import java.util.List;

public class Serie {
    final String titulo;
    final String idioma;
    final List<String> generos;
    final double nota;
    final String status;
    final String estreia;
    final String termino;
    final String emissora;

    public Serie(String titulo, String idioma, List<String> generos, double nota, String status, String estreia, String termino, String emissora) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.generos = generos;
        this.nota = nota;
        this.status = status;
        this.estreia = estreia;
        this.termino = termino;
        this.emissora = emissora;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getNota() {
        return nota;
    }

    public String getStatus() {
        return status;
    }

    public String getEstreia() {
        return estreia;
    }

    @Override
    public String toString() {
        return "Nome: " + titulo +
                "\nIdioma: " + idioma +
                "\nGêneros: " + String.join(", ", generos) +
                "\nNota: " + nota +
                "\nStatus: " + status +
                "\nData de Estreia: " + estreia +
                "\nData de Término: " + termino +
                "\nEmissora: " + emissora;
    }
}
