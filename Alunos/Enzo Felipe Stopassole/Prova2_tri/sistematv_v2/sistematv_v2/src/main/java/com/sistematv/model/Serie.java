package com.sistematv.model;

import java.util.List;
import java.util.Objects;

public class Serie {
    private int id;
    private String nome;
    private String idioma;
    private List<String> generos;
    private double nota;
    private String status;
    private String dataEstreia;
    private String dataFim;
    private String emissora;

    public Serie(int id, String nome, String idioma, List<String> generos, double nota,
            String status, String dataEstreia, String dataFim, String emissora) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos;
        this.nota = nota;
        this.status = status;
        this.dataEstreia = dataEstreia;
        this.dataFim = dataFim;
        this.emissora = emissora;
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
        return nota;
    }

    public String getStatus() {
        return status;
    }

    public String getDataEstreia() {
        return dataEstreia;
    }

    public String getDataFim() {
        return dataFim;
    }

    public String getEmissora() {
        return emissora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Serie))
            return false;
        Serie serie = (Serie) o;
        return id == serie.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format(
                "%s (%s)\nIdioma: %s\nGêneros: %s\nNota: %.1f\nStatus: %s\nEstreia: %s | Fim: %s\nEmissora: %s",
                nome, id, idioma, String.join(", ", generos), nota, status, dataEstreia,
                dataFim == null ? "-" : dataFim, emissora);
    }
}
