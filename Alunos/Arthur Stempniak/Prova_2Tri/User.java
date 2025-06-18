import java.util.ArrayList;
import java.util.List;

public class User {
    private final String nome;
    private final List<Serie> seriesFavoritas;
    private final List<Serie> seriesAssistidas;
    private final List<Serie> seriesDesejadas;

    public User(String nome) {
        this.nome = nome;
        this.seriesFavoritas = new ArrayList<>();
        this.seriesAssistidas = new ArrayList<>();
        this.seriesDesejadas = new ArrayList<>();
    }

    // Getters
    public String getNome() { return nome; }
    public List<Serie> getSeriesFavoritas() { return seriesFavoritas; }
    public List<Serie> getSeriesAssistidas() { return seriesAssistidas; }
    public List<Serie> getSeriesDesejadas() { return seriesDesejadas; }
}