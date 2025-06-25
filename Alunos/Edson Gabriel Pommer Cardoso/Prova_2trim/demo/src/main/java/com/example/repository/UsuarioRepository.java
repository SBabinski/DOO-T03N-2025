//Le e grava dados de usuários em arquivos JSON
//Lista usuários cadastrados
//Verifica se o usuário existe

package com.example.repository;

import com.example.model.DadosUsuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private static final String USUARIOS_DIR = "Prova_2trim" + File.separator + "usuarios";

    public void salvarDados(DadosUsuario dados) {
        try {
            File dir = new File(USUARIOS_DIR);
            if (!dir.exists()) dir.mkdir();
            String filename = USUARIOS_DIR + File.separator + dados.getUsuario() + ".json";
            try (Writer writer = new FileWriter(filename)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(dados, writer);
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public DadosUsuario carregarDadosUsuario(String usuario) {
        String filename = USUARIOS_DIR + File.separator + usuario + ".json";
        try (Reader reader = new FileReader(filename)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, DadosUsuario.class);
        } catch (IOException e) {
            return null;
        }
    }

    public boolean usuarioExiste(String usuario) {
        File file = new File(USUARIOS_DIR + File.separator + usuario + ".json");
        return file.exists();
    }

    public List<String> listarUsuarios() {
        File dir = new File(USUARIOS_DIR);
        List<String> usuarios = new ArrayList<>();
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles((d, name) -> name.endsWith(".json"));
            if (files != null) {
                for (File f : files) {
                    String nome = f.getName();
                    usuarios.add(nome.substring(0, nome.length() - 5));
                }
            }
        }
        return usuarios;
    }
}
