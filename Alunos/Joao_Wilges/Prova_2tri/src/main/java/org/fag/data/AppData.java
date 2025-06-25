package org.fag.data;

import org.fag.model.Serie;
import java.util.ArrayList;
import java.util.List;

public class AppData {
    private String userName;
    private List<Serie> favorites;
    private List<Serie> watched;
    private List<Serie> watchlist;

    public AppData() {
        this.favorites = new ArrayList<>();
        this.watched = new ArrayList<>();
        this.watchlist = new ArrayList<>();
    }
    public AppData(String userName, List<Serie> favorites, List<Serie> watched, List<Serie> watchlist) {
        this.userName = userName;
        this.favorites = favorites != null ? new ArrayList<>(favorites) : new ArrayList<>();
        this.watched = watched != null ? new ArrayList<>(watched) : new ArrayList<>();
        this.watchlist = watchlist != null ? new ArrayList<>(watchlist) : new ArrayList<>();
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Serie> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Serie> favorites) {
        this.favorites = favorites;
    }

    public List<Serie> getWatched() {
        return watched;
    }

    public void setWatched(List<Serie> watched) {
        this.watched = watched;
    }

    public List<Serie> getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(List<Serie> watchlist) {
        this.watchlist = watchlist;
    }
}