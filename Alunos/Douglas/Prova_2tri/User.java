package fag;

import java.util.ArrayList;

public class User {
    private String name;
    private ArrayList<Series> favorites = new ArrayList<>();
    private ArrayList<Series> watched = new ArrayList<>();
    private ArrayList<Series> wantToWatch = new ArrayList<>();

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Series> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Series> favorites) {
        this.favorites = favorites;
    }

    public ArrayList<Series> getWatched() {
        return watched;
    }

    public void setWatched(ArrayList<Series> watched) {
        this.watched = watched;
    }

    public ArrayList<Series> getWantToWatch() {
        return wantToWatch;
    }

    public void setWantToWatch(ArrayList<Series> wantToWatch) {
        this.wantToWatch = wantToWatch;
    }

}
