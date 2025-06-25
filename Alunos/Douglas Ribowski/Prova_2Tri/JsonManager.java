package com.example;
import java.io.*;
import java.util.List;

public class JsonManager {
    public static void salvarLista(String nomeArquivo, List<Serie> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(lista);
        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Serie> carregarLista(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (List<Serie>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }
}
