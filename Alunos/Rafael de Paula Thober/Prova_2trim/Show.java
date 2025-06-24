import java.util.List;

public class Show {

    private int id;
    private String name;
    private String language;
    private List<String> genres;
    private Rating rating;
    private String status;
    private String premiered;
    private String ended;
    private Network network;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public List<String> getGenres() {
        return genres;
    }

    public double getRating() {
        return rating != null ? rating.average : 0.0;
    }

    public String getStatus() {
        return status;
    }

    public String getPremiered() {
        return premiered;
    }

    public String getEnded() {
        return ended;
    }

    public String getNetworkName() {
        return network != null ? network.name : "Desconhecida";
    }

    public String toStringCompact() {
        return name + " (" + status + ")";
    }

    public String toStringDetail() {
        return "\n📺 " + name +
            "\nIdioma: " + language +
            "\nGêneros: " + String.join(", ", genres) +
            "\nNota: " + getRating() +
            "\nStatus: " + status +
            "\nEstreia: " + premiered +
            "\nTérmino: " + (ended != null ? ended : "Ainda em exibição") +
            "\nEmissora: " + getNetworkName();
    }

    private static class Rating {
        double average;
    }

    private static class Network {
        String name;
    }
}
