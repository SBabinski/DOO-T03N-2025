//Atributos nome, idioma, generos, nota, estado, dataEstreia, dataFim e emissora
//Equals e hashcode para evitar duplicação de séries
//Exibe dados

package com.example.model;

import java.io.Serializable;
import java.util.Objects;

public class Serie implements Serializable {
    private String nome;
    private String idioma;
    private String generos;
    private double nota;
    private String estado;
    private String dataEstreia;
    private String dataFim;
    private String emissora;

    public Serie(String nome, String idioma, String generos, double nota, String estado,
                 String dataEstreia, String dataFim, String emissora) {
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos;
        this.nota = nota;
        this.estado = estado;
        this.dataEstreia = dataEstreia;
        this.dataFim = dataFim;
        this.emissora = emissora;
    }

    public String getNome()        { return nome; }
    public String getIdioma()      { return idioma; }
    public String getGeneros()     { return generos; }
    public double getNota()        { return nota; }
    public String getEstado()      { return estado; }
    public String getDataEstreia() { return dataEstreia; }
    public String getDataFim()     { return dataFim; }
    public String getEmissora()    { return emissora; }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Serie)) return false;
        Serie serie = (Serie) o;
        return nome.equalsIgnoreCase(serie.nome);
    }

    public int hashCode() {
        return Objects.hash(nome.toLowerCase());
    }

    public String toString() {
        return "Nome: " + nome + " - Idioma: " + idioma + ", Gêneros: " + generos + ", Nota: " + nota + ", Estado: " + estado + ", Estreia: " + dataEstreia + ", Fim: " + dataFim + ", Emissora: " + emissora;
    }
}
