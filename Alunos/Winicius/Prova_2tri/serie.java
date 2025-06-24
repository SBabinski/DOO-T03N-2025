package model;

import java.util.List;

public class serie {
    private String nome;
    private String idioma;
    private List<String> generos;
    private Double nota;
    private String estado;
    private String dataEstreia;
    private String dataFim;
    private String emissora;

    // Getters, setters e toString()

    @Override
    public String toString() {
        return "Nome: " + nome + "\nIdioma: " + idioma + "\nGêneros: " + generos +
               "\nNota: " + nota + "\nEstado: " + estado + "\nEstreia: " + dataEstreia +
               "\nFim: " + dataFim + "\nEmissora: " + emissora;
    }

    // Construtor, Getters e Setters omitidos por brevidade
    public serie (String nome, String idioma, List<String> generos, Double nota, String estado, String dataEstreia, String dataFim, String emissora){
        this.nome= nome;
        this.idioma= idioma;
        this.generos= generos;
        this.nota= nota;
        this.estado= estado;
        this.dataEstreia= dataEstreia;
        this.dataFim= dataFim;
        this.emissora= emissora;
    }

    public void setNome(String nome) {
        this.nome= nome;
    }
      public void setIdioma(String idioma) {
        this.idioma= idioma;
    }
      public void setGeneros(List<String> generos) {
        this.generos= generos;
    }
      public void setNota(double nota) {
        this.nota= nota;
    }
      public void setEstado(String estado) {
        this.estado= estado;
    }
      public void setDataEstreia(String dataEstreia) {
        this.dataEstreia= dataEstreia;
    }
      public void setDataFim(String dataFimString) {
        this.dataFim= dataFimString;
    }
      public void setEmissora(String emissoraString) {
        this.emissora= emissoraString;
    }
}
