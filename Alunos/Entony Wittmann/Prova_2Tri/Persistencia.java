package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Persistencia {
    private static final String ARQUIVO = "series.json";

    public static void salvarDados(Map<String, Usuario> usuarios) {
        try (FileWriter writer = new FileWriter(ARQUIVO)) {
            Gson gson = new Gson();
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public static Map<String, Usuario> carregarDados() {
        if (!Files.exists(Paths.get(ARQUIVO))) {
            return new HashMap<>();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<Map<String, Usuario>>() {}.getType());
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }

        return new HashMap<>();
    }
}
