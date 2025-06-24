import java.util.HashSet;
import java.util.Set;

public class Usuario {
    private String nome;
    private Set<Integer> seriesFavoritas;
    private Set<Integer> seriesAssistidas;
    private Set<Integer> seriesParaAssistir;

    public Usuario(String nome) {
        this.nome = nome;
        this.seriesFavoritas = new HashSet<>();
        this.seriesAssistidas = new HashSet<>();
        this.seriesParaAssistir = new HashSet<>();
    }

    public String getNome() { return nome; }
    public Set<Integer> getSeriesFavoritas() { return seriesFavoritas; }
    public Set<Integer> getSeriesAssistidas() { return seriesAssistidas; }
    public Set<Integer> getSeriesParaAssistir() { return seriesParaAssistir; }

    public void addFavorita(int id) { seriesFavoritas.add(id); }
    public void removeFavorita(int id) { seriesFavoritas.remove(id); }
    public void addAssistida(int id) { seriesAssistidas.add(id); }
    public void removeAssistida(int id) { seriesAssistidas.remove(id); }
    public void addParaAssistir(int id) { seriesParaAssistir.add(id); }
    public void removeParaAssistir(int id) { seriesParaAssistir.remove(id); }
}