package org.example;

import java.util.List;

public class Serie {
    private String nome;
    private String idioma;
    private List<String> generos;
    private double notaGeral;
    private String estado;
    private String dataEstreia;
    private String dataTermino;
    private String emissora;

    public Serie(String nome, String idioma, List<String> generos, double notaGeral, String estado, String dataEstreia, String dataTermino, String emissora) {
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos;
        this.notaGeral = notaGeral;
        this.estado = estado;
        this.dataEstreia = dataEstreia;
        this.dataTermino = dataTermino;
        this.emissora = emissora;
    }

    public String getNome() {
        return nome;
    }

    public double getNotaGeral() {
        return notaGeral;
    }

    public String getEstado() {
        return estado;
    }

    public String getDataEstreia() {
        return dataEstreia;
    }

    @Override
    public String toString() {
        return String.format(
                "Nome: %s\nIdioma: %s\nGêneros: %s\nNota Geral: %.1f\nEstado: %s\nData de Estreia: %s\nData de Término: %s\nEmissora: %s",
                nome, idioma, String.join(", ", generos), notaGeral, estado,
                dataEstreia, dataTermino == null ? "N/A" : dataTermino, emissora
        );
    }
}