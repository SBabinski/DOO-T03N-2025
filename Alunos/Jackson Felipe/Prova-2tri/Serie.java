package seriesapp;

// Classe para guardar os dados de uma única série
public class Serie {
    private String name;
    private String language;
    private String[] genres;
    private Rating rating;
    private String status;
    private String premiered;
    private Network network;

    // Classe interna para o campo 'rating' que vem dentro do JSON
    public static class Rating {
        private Double average;
        public Double getAverage() { return average; }
    }

    // Classe interna para o campo 'network'
    public static class Network {
        private String name;
        public String getName() { return name; }
    }

    // Getters para os campos principais
    public String getName() { return name; }
    public String getLanguage() { return language; }
    public String[] getGenres() { return genres; }
    public Double getNotaGeral() { return (rating != null && rating.getAverage() != null) ? rating.getAverage() : 0.0; }
    public String getStatus() { return status; }
    public String getPremiered() { return premiered; }
    public String getNomeEmissora() { return (network != null) ? network.getName() : "N/A"; }

    @Override
    public String toString() {
        // Formata a exibição da série para o usuário
        return "Nome: " + name + "\n" +
                "  Idioma: " + language + "\n" +
                "  Gêneros: " + (genres != null ? String.join(", ", genres) : "N/A") + "\n" +
                "  Nota: " + getNotaGeral() + "\n" +
                "  Status: " + status + "\n" +
                "  Estreia: " + premiered + "\n" +
                "  Emissora: " + getNomeEmissora() + "\n";
    }
}