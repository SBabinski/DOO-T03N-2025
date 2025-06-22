import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private final String nome;
    private final List<Serie> favoritos;
    private final List<Serie> assistidas;
    private final List<Serie> paraAssistir;

    public Usuario(String nome) {
        this.nome = nome;
        this.favoritos = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.paraAssistir = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public List<Serie> getFavoritos() { return favoritos; }
    public List<Serie> getAssistidas() { return assistidas; }
    public List<Serie> getParaAssistir() { return paraAssistir; }

    public void adicionar(List<Serie> lista, Serie serie) {
        if (!lista.contains(serie)) lista.add(serie);
    }

    public void remover(List<Serie> lista, Serie serie) {
        lista.remove(serie);
    }
}