package controller;

import model.Show;
import model.ShowDTO;
import util.TVApiHandler;

import java.util.ArrayList;
import java.util.List;

public final class ApiController {

    public static List<ShowDTO> searchShowsByName(String showName){
        List<Show> returnedShows = TVApiHandler.searchShowsByName(showName);

        if(returnedShows == null){
            return null;
        }

        return wrapToDTO(returnedShows);
    }


    private static List<ShowDTO> wrapToDTO(List<Show> shows){
        List<ShowDTO> wrappedDTOs = new ArrayList<>();
        shows.forEach(show ->
            wrappedDTOs.add(
                    new ShowDTO(
                    show.getId(),
                    show.getName(),
                    show.getLanguage(),
                    show.getGenres(),
                    show.getRating().getAverage(),
                    statusConverter(show.getStatus()),
                    show.getPremiered(),
                    show.getEnded(),
                    broadcasterNameHandler(show.getNetwork()))
        ));
        return wrappedDTOs;
    }

    private static String statusConverter(String status){
        return switch (status) {
            case "Ended" -> "Concluída";
            case "Running" -> "Ainda transmitindo";
            default -> "Desconhecido";
        };
    }

    private static String broadcasterNameHandler(Show.Network network){
        if (network == null) {
            return "Desconhecida";
        } else {
            return network.getName();
        }
    }
}
