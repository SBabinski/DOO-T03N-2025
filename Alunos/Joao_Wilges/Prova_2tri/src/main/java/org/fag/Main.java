package org.fag;

import org.fag.api.ApiClient;
import org.fag.api.SeriesMapper;
import org.fag.data.AppData;
import org.fag.data.DataManager;
import org.fag.managers.FavoritesManager;
import org.fag.managers.WatchedManager;
import org.fag.managers.WatchlistManager;
import org.fag.ui.UserInterface;

public class Main {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        AppData appData = dataManager.loadData();

        SeriesMapper seriesMapper = new SeriesMapper();

        FavoritesManager favoritesManager = new FavoritesManager();
        favoritesManager.setSerieList(appData.getFavorites());

        WatchedManager watchedManager = new WatchedManager();
        watchedManager.setSerieList(appData.getWatched());

        WatchlistManager watchlistManager = new WatchlistManager();
        watchlistManager.setSerieList(appData.getWatchlist());

        UserInterface userInterface = new UserInterface(
                seriesMapper,
                favoritesManager,
                watchedManager,
                watchlistManager
        );

        userInterface.setUserName(appData.getUserName());

        userInterface.start();

        appData = new AppData(
                userInterface.getUserName(),
                favoritesManager.getSerieList(),
                watchedManager.getSerieList(),
                watchlistManager.getSerieList()
        );

        dataManager.saveData(appData);
    }
}