import java.util.List;

public class Serie {
    public int id;
    public String nome;
    public String idioma;
    public List<String> generos;
    public double nota;
    public String status;
    public String estreia;
    public String fim;
    public String emissora;

    public Serie(int id, String nome, String idioma, List<String> generos, double nota,
                 String status, String estreia, String fim, String emissora) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos;
        this.nota = nota;
        this.status = status;
        this.estreia = estreia;
        this.fim = fim;
        this.emissora = emissora;
    }

    public String mostrarDados() {
        return "Nome: " + nome +
                "\nIdioma: " + idioma +
                "\nGêneros: " + generos +
                "\nNota: " + nota +
                "\nStatus: " + status +
                "\nEstreia: " + estreia +
                "\nFim: " + fim +
                "\nEmissora: " + emissora + "\n";
    }
}