package com.braian.seriestracker.repository;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.braian.seriestracker.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UsuarioRepository {
    private static final String ARQUIVO = "usuario.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void salvar(Usuario usuario) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(ARQUIVO))) {
            gson.toJson(usuario, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public Usuario carregar() {
        File file = new File(ARQUIVO);
        if (!file.exists()) {
            return null;
        }

        try (Reader reader = Files.newBufferedReader(Paths.get(ARQUIVO))) {
            return gson.fromJson(reader, Usuario.class);
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuário: " + e.getMessage());
            return null;
        }
    }
}

