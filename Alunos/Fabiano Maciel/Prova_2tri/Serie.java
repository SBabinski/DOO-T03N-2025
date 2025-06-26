package Prova_2tri;
import java.util.List;
import java.util.Objects;
import java.io.Serializable;


public class Serie implements Serializable {
    private int id;
    private String nome;
    private String idioma;
    private List<String> generos;
    private double nota;
    private String estado;
    private String dataEstreia;
    private String dataFim;
    private String emissora;

    public Serie(int id, String nome, String idioma, List<String> generos, double nota, String estado, String dataEstreia, String dataFim, String emissora) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos;
        this.nota = nota;
        this.estado = estado;
        this.dataEstreia = dataEstreia;
        this.dataFim = dataFim;
        this.emissora = emissora;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getIdioma() { return idioma; }
    public List<String> getGeneros() { return generos; }
    public double getNota() { return nota; }
    public String getEstado() { return estado; }
    public String getDataEstreia() { return dataEstreia; }
    public String getDataFim() { return dataFim; }
    public String getEmissora() { return emissora; }

public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Serie)) return false;
        Serie serie = (Serie) o;
        return nome.equalsIgnoreCase(serie.nome);
    }

    public int hashCode() {
        return Objects.hash(nome.toLowerCase());
    }

    @Override
    public String toString() {
        return String.format(
            "Nome: %s\nIdioma: %s\nGêneros: %s\nNota: %.1f\nEstado: %s\nEstreia: %s\nFim: %s\nEmissora: %s\n",
            nome, idioma, generos, nota, estado, dataEstreia, dataFim, emissora
        );
    }
}