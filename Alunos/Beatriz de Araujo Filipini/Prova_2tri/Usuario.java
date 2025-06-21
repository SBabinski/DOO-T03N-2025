import java.util.ArrayList;
import java.util.List;
 

public class Usuario {
    private String apelido;
    private List<Serie> favoritas;
    private List<Serie> assistidas;
    private List<Serie> desejaAssistir;

    public Usuario(String apelido) {
        this.apelido = apelido;
        this.favoritas = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.desejaAssistir = new ArrayList<>();
    }
    public Usuario() {
        this.favoritas = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.desejaAssistir = new ArrayList<>();
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public List<Serie> getFavoritas() {
        return favoritas;
    }

    public void setFavoritas(List<Serie> favoritas) {
        this.favoritas = favoritas;
    }

    public List<Serie> getAssistidas() {
        return assistidas;
    }

    public void setAssistidas(List<Serie> assistidas) {
        this.assistidas = assistidas;
    }

    public List<Serie> getDesejaAssistir() {
        return desejaAssistir;
    }

    public void setDesejaAssistir(List<Serie> desejaAssistir) {
        this.desejaAssistir = desejaAssistir;
    }

    public void adicionarFavorita(Serie serie) {
        if (!contemSerie(favoritas, serie)) {
            favoritas.add(serie);
        }
    }

    public void removerFavorita(Serie serie) {
        removerSeriePorId(favoritas, serie.getId());
    }

    public void adicionarAssistida(Serie serie) {
        if (!contemSerie(assistidas, serie)) {
            assistidas.add(serie);
        }
    }

    public void removerAssistida(Serie serie) {
        removerSeriePorId(assistidas, serie.getId());
    }

    public void adicionarDesejaAssistir(Serie serie) {
        if (!contemSerie(desejaAssistir, serie)) {
            desejaAssistir.add(serie);
        }
    }

    public void removerDesejaAssistir(Serie serie) {
        removerSeriePorId(desejaAssistir, serie.getId());
    }

    private boolean contemSerie(List<Serie> lista, Serie serie) {
        return lista.stream().anyMatch(s -> s.getId() == serie.getId());
    }

    private void removerSeriePorId(List<Serie> lista, int idSerie) {
        lista.removeIf(s -> s.getId() == idSerie);
    }
}