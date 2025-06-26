package Prova_2tri;

import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class JsonUtils {
    private static final String ARQUIVO = "Usuarios.json";

    private static List<User> carregarTodosUsuarios() {
        try {
            File f = new File(ARQUIVO);
            if (!f.exists()) return new ArrayList<>();
            FileReader fr = new FileReader(f);
            Gson gson = new Gson();
            List<User> usuarios = gson.fromJson(fr, new TypeToken<List<User>>(){}.getType());
            fr.close();
            if (usuarios == null) return new ArrayList<>();
            return usuarios;
        } catch (Exception e) {
            System.out.println("Erro ao carregar todos os usuários: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void salvarTodosUsuarios(List<User> usuarios) {
        try {
            FileWriter fw = new FileWriter(ARQUIVO);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(usuarios, fw);
            fw.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar todos os usuários: " + e.getMessage());
        }
    }

    public static void salvarUsuario(User user) {
        List<User> usuarios = carregarTodosUsuarios();
        usuarios.removeIf(u -> u.getNome().equals(user.getNome()));
        usuarios.add(user);
        salvarTodosUsuarios(usuarios);
    }

    public static User carregarUsuario(String nome) {
        List<User> usuarios = carregarTodosUsuarios();
        for (User u : usuarios) {
            if (u.getNome().equals(nome)) {
                return u;
            }
        }
        return new User(nome);
    }
}