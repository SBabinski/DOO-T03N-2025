package org.fag.controller.comparators;

import org.fag.model.Serie;

import java.util.Comparator;

public class SerieRatingComparator implements Comparator<Serie> {
    @Override
    public int compare(Serie s1, Serie s2) {
        Double rating1 = (s1 != null && s1.getNotaGeral() != null) ? s1.getNotaGeral() : 0;
        Double rating2 = (s2 != null && s2.getNotaGeral() != null) ? s2.getNotaGeral() : 0;

        return rating2.compareTo(rating1);
    }
}
