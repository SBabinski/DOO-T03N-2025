package model;

import java.util.ArrayList;
import java.util.List;

public class usuario {
    private List<serie> favoritas = new ArrayList<>();
    private List<serie> assistidas = new ArrayList<>();
    private List<serie> desejadas = new ArrayList<>();
    private String nome;

    // Métodos para adicionar/remover/exibir/ordenar séries
    public void adicionarSerie(String tipo, serie serie) {
        switch (tipo) {
            case "favoritas" -> favoritas.add(serie);
            case "assistidas" -> assistidas.add(serie);
            case "desejadas" -> desejadas.add(serie);
        }
    }

    public void removerSerie(String tipo, serie serie) {
        switch (tipo) {
            case "favoritas" -> favoritas.remove(serie);
            case "assistidas" -> assistidas.remove(serie);
            case "desejadas" -> desejadas.remove(serie);
        }
    }

    public List<serie> getLista(String tipo) {
        return switch (tipo) {
            case "favoritas" -> favoritas;
            case "assistidas" -> assistidas;
            case "desejadas" -> desejadas;
            default -> new ArrayList<>();
        };
    }

    public void setNome(String nome) {
        this.nome= nome;
    }

    // Getters e setters omitidos
}
