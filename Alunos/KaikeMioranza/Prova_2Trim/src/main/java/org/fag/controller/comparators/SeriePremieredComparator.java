package org.fag.controller.comparators;

import org.fag.model.Serie;

import java.time.LocalDate;
import java.util.Comparator;

public class SeriePremieredComparator implements Comparator<Serie> {

    @Override
    public int compare(Serie s1, Serie s2) {
        LocalDate date1 = s1.getDataEstreia();
        LocalDate date2 = s2.getDataEstreia();

        if (date1 == null && date2 == null) {
            return 0;
        }
        if (date1 == null) {
            return -1;
        }
        if (date2 == null) {
            return 1;
        }
        return date1.compareTo(date2);
    }
}
