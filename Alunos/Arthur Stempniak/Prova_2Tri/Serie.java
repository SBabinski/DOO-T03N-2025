import java.util.List;
import java.util.Objects;

public class Serie {
    private final String nome;
    private final String idioma;
    private final List<String> generos;
    private final double notaGeral;
    private final String estado;
    private final String dataEstreia;
    private final String dataFim;
    private final String emissora;

    public Serie(String nome, String idioma, List<String> generos, double notaGeral,
                 String estado, String dataEstreia, String dataFim, String emissora) {
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos;
        this.notaGeral = notaGeral;
        this.estado = estado;
        this.dataEstreia = dataEstreia;
        this.dataFim = dataFim;
        this.emissora = emissora;
    }

    // Getters
    public String getNome() { return nome; }
    public String getIdioma() { return idioma; }
    public List<String> getGeneros() { return generos; }
    public double getNotaGeral() { return notaGeral; }
    public String getEstado() { return estado; }
    public String getDataEstreia() { return dataEstreia; }
    public String getDataFim() { return dataFim; }
    public String getEmissora() { return emissora; }

    @Override
    public String toString() {
        return String.format(
            "Nome: %s%n  Idioma: %s%n  Gêneros: %s%n  Nota: %.1f%n  Estado: %s%n  Estreia: %s%n  Término: %s%n  Emissora: %s",
            nome,
            (idioma != null && !idioma.isEmpty()) ? idioma : "N/A",
            (generos != null && !generos.isEmpty()) ? String.join(", ", generos) : "N/A",
            notaGeral,
            (estado != null && !estado.isEmpty()) ? estado : "N/A",
            (dataEstreia != null && !dataEstreia.isEmpty()) ? dataEstreia : "N/A",
            (dataFim != null && !dataFim.isEmpty()) ? dataFim : "N/A",
            (emissora != null && !emissora.isEmpty()) ? emissora : "N/A"
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return Objects.equals(nome.toLowerCase(), serie.nome.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase());
    }
}