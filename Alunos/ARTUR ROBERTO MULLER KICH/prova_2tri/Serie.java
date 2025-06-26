package org.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Serie implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String name;
    private String language;
    private List<String> genres;
    
    @JsonProperty("rating")
    private Rating rating;
    
    private String status;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate premiered;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate ended;
    
    @JsonProperty("network")
    private Network network;
    
    @JsonProperty("webChannel")
    private Network webChannel;
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Rating implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private Double average;

        public Double getAverage() {
            return average;
        }

        public void setAverage(Double average) {
            this.average = average;
        }
    }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Network implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public Serie() {
        this.genres = new ArrayList<>();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name != null ? name : "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getGenres() {
        return genres != null ? genres : new ArrayList<>();
    }

    public void setGenres(List<String> genres) {
        this.genres = genres != null ? genres : new ArrayList<>();
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getPremiered() {
        return premiered;
    }

    public void setPremiered(LocalDate premiered) {
        this.premiered = premiered;
    }

    public LocalDate getEnded() {
        return ended;
    }

    public void setEnded(LocalDate ended) {
        this.ended = ended;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Network getWebChannel() {
        return webChannel;
    }

    public void setWebChannel(Network webChannel) {
        this.webChannel = webChannel;
    }
    
    public Double getNotaMedia() {
        return rating != null ? rating.getAverage() : null;
    }
    
    public String getEmissora() {
        if (network != null && network.getName() != null) {
            return network.getName();
        } else if (webChannel != null && webChannel.getName() != null) {
            return webChannel.getName();
        }
        return "Desconhecida";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return Objects.equals(id, serie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name != null ? name : "Série sem nome";
    }
}
