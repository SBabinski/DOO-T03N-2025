/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;



import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.Usuario;

import java.io.*;
import java.lang.reflect.Type;

public class Persistencia {
    private static final String PASTA = "usuarios/";

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    static {
        File dir = new File(PASTA);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static void salvarUsuario(Usuario usuario) {
        String caminho = PASTA + usuario.getNome().toLowerCase() + ".json";
        try (FileWriter writer = new FileWriter(caminho)) {
            gson.toJson(usuario, writer);
            System.out.println("✅ Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("❌ Erro ao salvar dados do usuário: " + e.getMessage());
        }
    }

    public static Usuario carregarUsuario(String nome) {
        String caminho = PASTA + nome.toLowerCase() + ".json";
        File arquivo = new File(caminho);
        if (!arquivo.exists()) {
            return null;
        }
        try (FileReader reader = new FileReader(caminho)) {
            Type tipo = new TypeToken<Usuario>() {}.getType();
            return gson.fromJson(reader, tipo);
        } catch (IOException e) {
            System.out.println("❌ Erro ao carregar dados do usuário: " + e.getMessage());
            return null;
        }
    }
}
