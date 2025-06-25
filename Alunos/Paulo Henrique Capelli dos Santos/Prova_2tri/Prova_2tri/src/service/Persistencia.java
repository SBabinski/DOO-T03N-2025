package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Usuario;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Persistencia {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String CAMINHO = "usuario.json";

    public static void salvar(Usuario usuario) {
        try (FileWriter writer = new FileWriter(CAMINHO)) {
            gson.toJson(usuario, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public static Usuario carregar() {
        try (FileReader reader = new FileReader(CAMINHO)) {
            return gson.fromJson(reader, Usuario.class);
        } catch (IOException e) {
            System.out.println("Nenhum usuário salvo anteriormente.");
            return null;
        }
    }
}