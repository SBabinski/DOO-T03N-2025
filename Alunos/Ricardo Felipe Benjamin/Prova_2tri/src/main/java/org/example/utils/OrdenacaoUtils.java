package org.example.utils;

import org.example.models.Serie;

import java.util.Comparator;
import java.util.List;

public class OrdenacaoUtils {

    public static void ordenarPorNome(List<Serie> series) {
        series.sort(Comparator.comparing(Serie::getNome, String.CASE_INSENSITIVE_ORDER));
    }

    public static void ordenarPorNota(List<Serie> series) {
        series.sort(Comparator.comparingDouble(Serie::getNota).reversed());
    }

    public static void ordenarPorEstado(List<Serie> series) {
        series.sort(Comparator.comparing(Serie::getStatus));
    }

    public static void ordenarPorDataEstreia(List<Serie> series) {
        series.sort(Comparator.comparing(Serie::getDataEstreia));
    }
}
