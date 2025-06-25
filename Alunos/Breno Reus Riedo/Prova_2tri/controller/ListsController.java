package controller;

import model.ShowDTO;
import model.ThreeListsModel;
import util.JsonFileHandler;

import java.util.List;

public final class ListsController {

    private static ThreeListsModel threeListsModel;

    public static ThreeListsModel getThreeListsModel() {
        return threeListsModel;
    }

    public static void setThreeListsModel(ThreeListsModel threeListsModel) {
        ListsController.threeListsModel = threeListsModel;
    }

    public static boolean addToFavorites(ShowDTO addingShow) {
        if (hasDuplicate(threeListsModel.getFavorites(), addingShow)) {
            return false;
        }
        threeListsModel.getFavorites().add(addingShow);
        JsonFileHandler.writeListsToFile(threeListsModel);

        return true;
    }

    public static void removeFromFavorites(ShowDTO removingShow) {
        threeListsModel.getFavorites().remove(removingShow);
        JsonFileHandler.writeListsToFile(threeListsModel);
    }

    public static boolean addToAlreadyWatched(ShowDTO addingShow) {
        if (hasDuplicate(threeListsModel.getAlreadyWatched(), addingShow)) {
            return false;
        }

        //remove da lista de 'deseja assistir' (impossivel desejar assistir estando na lista de 'já assistidos')
        threeListsModel.getToWatch().stream()
                .filter(show -> show.id() == addingShow.id())
                .findFirst()
                .ifPresent(incoherentShow -> threeListsModel.getToWatch().remove(incoherentShow));

        threeListsModel.getAlreadyWatched().add(addingShow);
        JsonFileHandler.writeListsToFile(threeListsModel);

        return true;
    }

    public static void removeFromAlreadyWatched(ShowDTO removingShow) {
        threeListsModel.getAlreadyWatched().remove(removingShow);
        JsonFileHandler.writeListsToFile(threeListsModel);
    }

    public static boolean addToWatch(ShowDTO addingShow) {
        if (hasDuplicate(threeListsModel.getToWatch(), addingShow)) {
            return false;
        }

        //remove da lista de 'já assistidos' (impossivel ja ter assistido estando na lista de 'deseja assistir')
        threeListsModel.getAlreadyWatched().stream()
                .filter(show -> show.id() == addingShow.id())
                .findFirst()
                .ifPresent(incoherentShow -> threeListsModel.getAlreadyWatched().remove(incoherentShow));

        threeListsModel.getToWatch().add(addingShow);
        JsonFileHandler.writeListsToFile(threeListsModel);

        return true;
    }
    public static void removeFromWatch(ShowDTO removingShow) {
        threeListsModel.getToWatch().remove(removingShow);
        JsonFileHandler.writeListsToFile(threeListsModel);
    }


    private static boolean hasDuplicate(List<ShowDTO> shows, ShowDTO addingShow) {
        return shows.stream().anyMatch(show -> show.id() == addingShow.id());
    }

}
