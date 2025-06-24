package fag;

import java.time.LocalDate;
import java.util.ArrayList;

public class Series {
    private String title;
    private String language;
    private ArrayList<String> genres;
    private double note;
    private String status;
    private LocalDate premiered;
    private LocalDate ended;
    private String network;

    public Series() {
    }

    public Series(String title, String language, ArrayList<String> genres, double note, String status,
            LocalDate premiered,
            LocalDate ended,
            String network) {
        this.title = title;
        this.language = language;
        this.genres = genres;
        this.note = note;
        this.status = status;
        this.premiered = premiered;
        this.ended = ended;
        this.network = network;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
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

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

}
