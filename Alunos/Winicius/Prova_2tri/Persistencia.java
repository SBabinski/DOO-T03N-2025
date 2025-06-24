package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.usuario;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Persistencia {
    private static final String ARQUIVO = "usuario.json";

    public static void salvar(usuario usuario) {
        try (FileWriter writer = new FileWriter(ARQUIVO)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(usuario, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public static usuario carregar() {
        try (FileReader reader = new FileReader(ARQUIVO)) {
            return new Gson().fromJson(reader, usuario.class);
        } catch (IOException e) {
            return null;
        }
    }
}
