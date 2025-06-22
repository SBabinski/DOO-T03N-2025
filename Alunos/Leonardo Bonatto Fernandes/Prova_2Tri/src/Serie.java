import java.util.List;

public class Serie {
    private String nome;
    private String idioma;
    private List<String> generos;
    private String status;
    private String dataEstreia;
    private String dataFim;
    private String emissora;

    public Serie(String nome, String idioma, List<String> generos, String status, String dataEstreia, String dataFim, String emissora) {
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos;
        this.status = status;
        this.dataEstreia = dataEstreia;
        this.dataFim = dataFim;
        this.emissora = emissora;
    }

    public String getNome() { return nome; }
    public String getIdioma() { return idioma; }
    public List<String> getGeneros() { return generos; }
    public String getStatus() { return status; }
    public String getDataEstreia() { return dataEstreia; }
    public String getDataFim() { return dataFim; }
    public String getEmissora() { return emissora; }

    @Override
    public String toString() {
        return "\n=== " + nome + " ===" +
               "\nIdioma: " + idioma +
               "\nGêneros: " + String.join(", ", generos) +
               "\nStatus: " + status +
               "\nEstreia: " + dataEstreia +
               "\nFim: " + dataFim +
               "\nEmissora: " + emissora;
    }
}
