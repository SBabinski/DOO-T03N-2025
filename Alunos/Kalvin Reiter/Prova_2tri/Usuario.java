import java.util.*;

public class Usuario {

    private String nome; 
    private Map<Long, Serie> favoritos;
    private Map<Long, Serie> assistidas;
    private Map<Long, Serie> desejo;

    public Usuario(String nome) {

        this.nome = nome;
        this.favoritos = new HashMap<>();
        this.assistidas = new HashMap<>();
        this.desejo = new HashMap<>();

    }

    public String getNome() { return nome; }

    public Map<Long, Serie> getFavoritos() { return favoritos; }
    public Map<Long, Serie> getAssistidas() { return assistidas; }
    public Map<Long, Serie> getDesejo() { return desejo; }

    public void adicionarSerie(String lista, Serie serie) {

        long serieId = serie.getId();

        switch(lista) {

            case "favoritos": favoritos.put(serieId, serie); break;
            case "assistidas": assistidas.put(serieId, serie); break;
            case "desejo": desejo.put(serieId, serie); break;

        }

    }

    public void removerSerie(String lista, long idSerie) {

        switch(lista) {

            case "favoritos": favoritos.remove(idSerie); break;
            case "assistidas": assistidas.remove(idSerie); break;
            case "desejo": desejo.remove(idSerie); break;

        }

    }

    public List<Serie> getLista(String lista) {

        switch(lista) {

            case "favoritos": return new ArrayList<>(favoritos.values());
            case "assistidas": return new ArrayList<>(assistidas.values());
            case "desejo": return new ArrayList<>(desejo.values());
            default: return new ArrayList<>();

        }

    }
    
}