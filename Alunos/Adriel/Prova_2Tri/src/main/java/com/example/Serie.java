package com.example;

import java.util.List;

public class Serie implements Comparable<Serie> {
    private int id;
    private String name;
    private String language;
    private List<String> genres;
    private double rating;
    private String status;
    private String premiered;
    private String ended;
    private String network;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public List<String> getGenres() { return genres; }
    public void setGenres(List<String> genres) { this.genres = genres; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPremiered() { return premiered; }
    public void setPremiered(String premiered) { this.premiered = premiered; }
    public String getEnded() { return ended; }
    public void setEnded(String ended) { this.ended = ended; }
    public String getNetwork() { return network; }
    public void setNetwork(String network) { this.network = network; }

    @Override
    public String toString() {
        return "Nome: " + name +
               "\nIdioma: " + language +
               "\nGêneros: " + genres +
               "\nNota: " + rating +
               "\nStatus: " + status +
               "\nEstreia: " + premiered +
               "\nTérmino: " + ended +
               "\nEmissora: " + network;
    }

    @Override
    public int compareTo(Serie outra) {
        return this.name.compareToIgnoreCase(outra.name);
    }
}