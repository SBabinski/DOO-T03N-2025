package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListaManager {
    public List<Serie> favoritos = new ArrayList<>();
    public List<Serie> assistidas = new ArrayList<>();
    public List<Serie> desejaAssistir = new ArrayList<>();

    public void adicionarSerie(Serie serie, String lista) {
        switch (lista.toLowerCase()) {
            case "favoritos" -> favoritos.add(serie);
            case "assistidas" -> assistidas.add(serie);
            case "deseja" -> desejaAssistir.add(serie);
        }
    }

    public void removerSerie(String nome, String lista) {
        switch (lista.toLowerCase()) {
            case "favoritos" -> favoritos.removeIf(s -> s.getTitulo().equalsIgnoreCase(nome));
            case "assistidas" -> assistidas.removeIf(s -> s.getTitulo().equalsIgnoreCase(nome));
            case "deseja" -> desejaAssistir.removeIf(s -> s.getTitulo().equalsIgnoreCase(nome));
        }
    }

    public void exibirLista(String lista) {
        List<Serie> alvo = switch (lista.toLowerCase()) {
            case "favoritos" -> favoritos;
            case "assistidas" -> assistidas;
            case "deseja" -> desejaAssistir;
            default -> null;
        };

        if (alvo == null || alvo.isEmpty()) {
            System.out.println("Lista vazia ou inválida.");
            return;
        }

        for (Serie serie : alvo) {
            System.out.println("\n" + serie);
        }
    }

    public void ordenarLista(String lista, String criterio) {
        List<Serie> alvo = switch (lista.toLowerCase()) {
            case "favoritos" -> favoritos;
            case "assistidas" -> assistidas;
            case "deseja" -> desejaAssistir;
            default -> null;
        };

        if (alvo == null || alvo.isEmpty()) {
            System.out.println("Lista vazia ou inválida.");
            return;
        }

        switch (criterio.toLowerCase()) {
            case "nome" -> alvo.sort(Comparator.comparing(Serie::getTitulo));
            case "nota" -> alvo.sort(Comparator.comparing(Serie::getNota).reversed());
            case "status" -> alvo.sort(Comparator.comparing(Serie::getStatus));
            case "estreia" -> alvo.sort(Comparator.comparing(Serie::getEstreia));
        }

        System.out.println("Lista " + lista + " ordenada por " + criterio + ":");
        for (Serie serie : alvo) {
            System.out.println("\n" + serie);
        }
    }
}
