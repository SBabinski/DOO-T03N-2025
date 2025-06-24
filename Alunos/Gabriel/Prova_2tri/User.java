package com.tvseriestracker;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String nickname;
    private List<Show> favorites;
    private List<Show> watched;
    private List<Show> watchlist;

    public User(String nickname) {
        this.nickname = nickname;
        this.favorites = new ArrayList<>();
        this.watched = new ArrayList<>();
        this.watchlist = new ArrayList<>();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Show> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Show> favorites) {
        this.favorites = favorites;
    }

    public List<Show> getWatched() {
        return watched;
    }

    public void setWatched(List<Show> watched) {
        this.watched = watched;
    }

    public List<Show> getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(List<Show> watchlist) {
        this.watchlist = watchlist;
    }

    public void addFavorite(Show show) {
        if (!favorites.contains(show)) {
            favorites.add(show);
        }
    }

    public void removeFavorite(Show show) {
        favorites.remove(show);
    }

    public void addWatched(Show show) {
        if (!watched.contains(show)) {
            watched.add(show);
        }
    }

    public void removeWatched(Show show) {
        watched.remove(show);
    }

    public void addWatchlist(Show show) {
        if (!watchlist.contains(show)) {
            watchlist.add(show);
        }
    }

    public void removeWatchlist(Show show) {
        watchlist.remove(show);
    }
}


