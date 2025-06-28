import java.util.Arrays;
import java.util.Objects;

// Classe que representa uma série de TV

public class Serie {
    private String nome;
    private String idioma;
    private String[] generos;
    private double nota;
    private String status;
    private String dataEstreia;
    private String dataFim;
    private String emissora;

    public Serie() {
        this.nome = "";
        this.idioma = "";
        this.generos = new String[0];
        this.nota = 0.0;
        this.status = "";
        this.dataEstreia = "";
        this.dataFim = "";
        this.emissora = "";
    }
    public Serie(String nome, String idioma, String[] generos, double nota, String status, String dataEstreia, String dataFim, String emissora) {
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos != null ? generos : new String[0];
        this.nota = nota;
        this.status = status;
        this.dataEstreia = dataEstreia;
        this.dataFim = dataFim;
        this.emissora = emissora;
    }

    public Serie(String nome) {
        this.nome = nome;
        this.idioma = "";
        this.generos = new String[0];
        this.nota = 0.0;
        this.status = "";
        this.dataEstreia = "";
        this.dataFim = "";
        this.emissora = "";
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public String[] getGeneros() { return generos; }
    public void setGeneros(String[] generos) { this.generos = generos; }

    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDataEstreia() { return dataEstreia; }
    public void setDataEstreia(String dataEstreia) { this.dataEstreia = dataEstreia; }

    public String getDataFim() { return dataFim; }
    public void setDataFim(String dataFim) { this.dataFim = dataFim; }

    public String getEmissora() { return emissora; }
    public void setEmissora(String emissora) { this.emissora = emissora; }

    @Override
    public String toString() {
        return "Nome: " + nome +
               "\nIdioma: " + idioma +
               "\nGêneros: " + (generos != null ? Arrays.toString(generos) : "Não informado") +
               "\nNota: " + (nota > 0 ? nota : "Não disponível") +
               "\nStatus: " + status +
               "\nEstreia: " + dataEstreia +
               "\nFim: " + (dataFim.isEmpty() ? "Ainda em exibição ou não disponível" : dataFim) +
               "\nEmissora: " + emissora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Serie)) return false;
        Serie serie = (Serie) o;
        return nome != null && nome.equalsIgnoreCase(serie.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome != null ? nome.toLowerCase() : null);
    }
}