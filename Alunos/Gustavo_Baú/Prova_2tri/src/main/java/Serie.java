import java.util.List;

class Rating {
    Double average;
}

class Network {
    String name;
}

public class Serie {
    private int id;
    private String name;
    private String language;
    private List<String> genres;
    private String status;
    private String premiered;
    private String ended;
    private Rating rating;
    private Network network;

    public int getId() { return id; }
    public String getName() { return name; }
    public String getLanguage() { return language; }
    public List<String> getGenres() { return genres; }
    public String getStatus() { return status; }
    public String getPremiered() { return premiered; }
    public Rating getRating() { return rating; }

    public void exibir() {
        System.out.println("--------------------------------------------------");
        System.out.println("Nome: " + name + " (ID: " + id + ")");
        System.out.println("Idioma: " + language);
        System.out.println("Gêneros: " + String.join(", ", genres));
        
        String nota = (rating != null && rating.average != null) ? String.valueOf(rating.average) : "N/A";
        System.out.println("Nota Geral: " + nota);
        
        System.out.println("Estado: " + status);
        System.out.println("Data de Estreia: " + (premiered != null ? premiered : "N/A"));
        System.out.println("Data de Término: " + (ended != null ? ended : "N/A"));
        
        String emissora = (network != null && network.name != null) ? network.name : "N/A";
        System.out.println("Emissora: " + emissora);
        System.out.println("--------------------------------------------------");
    }
}

class SearchResult {
    Serie show;
}