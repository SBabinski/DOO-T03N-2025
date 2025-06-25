package model;

import java.util.ArrayList;
import java.util.List;

public final class ThreeListsModel {

    private final List<ShowDTO> favorites = new ArrayList<>();
    private final List<ShowDTO> alreadyWatched = new ArrayList<>();
    private final List<ShowDTO> toWatch = new ArrayList<>();

    public List<ShowDTO> getFavorites() {
        return favorites;
    }

    public List<ShowDTO> getAlreadyWatched() {
        return alreadyWatched;
    }

    public List<ShowDTO> getToWatch() {
        return toWatch;
    }

}
