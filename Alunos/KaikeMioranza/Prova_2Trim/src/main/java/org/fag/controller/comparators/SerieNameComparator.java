package org.fag.controller.comparators;

import org.fag.model.Serie;

import java.util.Comparator;

public class SerieNameComparator implements Comparator<Serie> {

    @Override
    public int compare(Serie s1, Serie s2) {
        String name1 = (s1 != null && s1.getNome() != null) ? s1.getNome() : "";
        String name2 = (s2 != null && s2.getNome() != null) ? s2.getNome() : "";

        return name1.compareTo(name2);
    }
}
