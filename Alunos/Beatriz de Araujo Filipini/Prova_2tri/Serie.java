import java.util.List;

public class Serie {
    private int id;
    private String name;
    private String language;
    private List<String> genres;
    private Rating rating;      
    private String status;
    private String premiered;   
    private String ended;       
    private Network network;    

    
    public Serie() {}

    
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

    public double getRatingAverage() {
        return rating != null ? rating.getAverage() : 0.0;
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

    public Network getNetwork() {
        return network;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public void setEnded(String ended) {
        this.ended = ended;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

  private static class Rating {
        private double average;

        public double getAverage() {
            return average;
        }
    
    }
    
    public static class Network {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return id == serie.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}