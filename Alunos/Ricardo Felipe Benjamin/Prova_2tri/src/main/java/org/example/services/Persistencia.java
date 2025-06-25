package org.example.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.models.Usuario;

import java.io.FileReader;
import java.io.FileWriter;


public class Persistencia {

    private static final String CAMINHO_ARQUIVO = "usuario.json";

    public static void salvarDados(Usuario usuario) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter escritor = new FileWriter(CAMINHO_ARQUIVO);
            gson.toJson(usuario, escritor);
            escritor.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public static Usuario carregarDados() {
        try {
            FileReader leitor = new FileReader(CAMINHO_ARQUIVO);
            Gson gson = new Gson();
            return gson.fromJson(leitor, Usuario.class);
        } catch (Exception e) {
            System.out.println("Nenhum dado salvo encontrado. Criando novo usuário.");
            return null;
        }
    }
}
