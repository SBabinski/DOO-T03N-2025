package com.braian.seriestracker.service;

import java.util.Comparator;
import java.util.List;

import com.braian.seriestracker.model.Serie;

public class GerenciadorDeSeries {

    public void exibir(List<Serie> lista) {
        if (lista.isEmpty()) {
            System.out.println("Lista vazia.");
        } else {
            for (Serie s : lista) {
                System.out.println(s);
                System.out.println("--------------------");
            }
        }
    }

    public void ordenarPorNome(List<Serie> lista) {
        lista.sort(Comparator.comparing(Serie::getTitulo));
        System.out.println("Lista ordenada por nome.");
    }

    public void ordenarPorNota(List<Serie> lista) {
        lista.sort(Comparator.comparingDouble(Serie::getNota).reversed());
        System.out.println("Lista ordenada por nota (maior para menor).");
    }

    public void ordenarPorStatus(List<Serie> lista) {
        lista.sort(Comparator.comparing(Serie::getStatus));
        System.out.println("Lista ordenada por status.");
    }

    public void ordenarPorDataEstreia(List<Serie> lista) {
        lista.sort(Comparator.comparing(Serie::getDataEstreia));
        System.out.println("Lista ordenada por data de estreia.");
    }
}
