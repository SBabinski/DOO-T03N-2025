package org.fag.controller.comparators;

import org.fag.model.Serie;

import java.util.Comparator;

public class SerieStatusComparator implements Comparator<Serie> {

    @Override
    public int compare(Serie s1, Serie s2) {
        String status1 = (s1 != null && s1.getEstado() != null) ? s1.getEstado() : "";
        String status2 = (s2 != null && s2.getEstado() != null) ? s2.getEstado() : "";
        return status1.compareTo(status2);
    }
}
