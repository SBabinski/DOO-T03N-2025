package org.fag.managers;

import org.fag.comparators.SerieNameComparator;
import org.fag.comparators.SeriePremieredComparator;
import org.fag.comparators.SerieRatingComparator;
import org.fag.comparators.SerieStatusComparator;
import org.fag.model.Serie;

import java.util.ArrayList;
import java.util.List;

public abstract class SerieListManagers {
    private List<Serie> serieList = new ArrayList<>();

    public void addSerie(Serie serie){
        serieList.add(serie);
    }

    public void removeSerie(String id_serie){
        serieList.removeIf( serie ->serie.getId().equals(id_serie));
    }

    public void showSerieList(){
        if (serieList.isEmpty()) {
            System.out.println("A lista está vazia.");
            return;
        }
        for(Serie serie: serieList){
            System.out.println(serie.toString());
        }
    }

    public void orderByName(){
        SerieNameComparator nameComparator = new SerieNameComparator();
        this.serieList.sort(nameComparator);
    }

    public void orderByRating(){
        SerieRatingComparator ratingComparator = new SerieRatingComparator();
        this.serieList.sort(ratingComparator);
    }

    public void orderByStatus(){
        SerieStatusComparator statusComparator = new SerieStatusComparator();
        this.serieList.sort(statusComparator);
    }

    public void orderByPremiered(){
        SeriePremieredComparator premieredComparator = new SeriePremieredComparator();
        this.serieList.sort(premieredComparator);
    }

    public List<Serie> getSerieList() {
        return new ArrayList<>(this.serieList);
    }

    public void setSerieList(List<Serie> serieList) {
        this.serieList = new ArrayList<>(serieList);
    }
}