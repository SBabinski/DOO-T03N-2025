package org.aplicacao;

public class Series {

    public int id;
    public String name;
    public String language;
    public String genres;
    public String status;
    public double rating;
    public String premiered;
    public String ended;
    public String network;

    public Series() {
    }


    public Series(int id, String name, String language, double rating, String genres, String status, String premiered, String ended, String network) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.rating = rating;
        this.genres = genres;
        this.status = status;
        this.premiered = premiered;
        this.ended = ended;
        this.network = network;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getPremiered() {
        return premiered;
    }

    public void setPremiered(String premiered) {
        this.premiered = premiered;
    }

    public String getEnded() {
        return ended;
    }

    public void setEnded(String ended) {
        this.ended = ended;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    @Override
    public String toString() {
        return "Série: " + name +
                "\nIdioma: " + language +
                "\nGêneros: " + genres +
                "\nStatus: " + status +
                "\nAvaliação: " + rating +
                "\nEstreou em: " + premiered +
                "\nTerminou em: " + ended +
                "\nEmissora: " + network;
    }

}