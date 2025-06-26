package com.sistematv.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sistematv.model.Usuario;
import java.io.*;

public class PersistenciaJson {
    private static final String CAMINHO = "usuario.json";

    public static void salvar(Usuario usuario) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(CAMINHO)) {
            gson.toJson(usuario, writer);
        }
    }

    public static Usuario carregar() throws IOException {
        File file = new File(CAMINHO);
        if (!file.exists())
            return null;
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, Usuario.class);
        }
    }
}