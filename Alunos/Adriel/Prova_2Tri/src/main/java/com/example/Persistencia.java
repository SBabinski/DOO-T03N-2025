package com.example;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Persistencia {
    private static final String ARQUIVO = "dados_usuario.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void salvar(Usuario usuario) {
        try (FileWriter writer = new FileWriter(ARQUIVO)) {
            gson.toJson(usuario, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public static Usuario carregar() {
        try (FileReader reader = new FileReader(ARQUIVO)) {
            return gson.fromJson(reader, Usuario.class);
        } catch (IOException e) {
            System.err.println("Usuário não encontrado. Criando novo perfil.");
            return null;
        }
    }
}