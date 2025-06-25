package com.trabalhotvmaze.series;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;

public class GerenciadorDeDados {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public GerenciadorDeDados() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());
    }

    public void salvarUsuario(Usuario usuario) {
        if (usuario == null || usuario.getNome() == null || usuario.getNome().isBlank()) {
            // Em uma GUI, é melhor não imprimir no console, mas para manter simples...
            System.err.println("Tentativa de salvar um usuário nulo ou sem nome.");
            return;
        }
        String fileName = getFileNameForUser(usuario.getNome());
        try {
            objectMapper.writeValue(new File(fileName), usuario);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados para " + usuario.getNome() + ": " + e.getMessage());
        }
    }

    public Usuario carregarUsuario(String nomeUsuario) {
        if (nomeUsuario == null || nomeUsuario.isBlank()) return null;
        
        String fileName = getFileNameForUser(nomeUsuario);
        File file = new File(fileName);

        if (file.exists()) {
            try {
                return objectMapper.readValue(file, Usuario.class);
            } catch (IOException e) {
                System.err.println("Erro ao carregar dados de " + fileName + ": " + e.getMessage());
            }
        }
        return null;
    }
    
    private String getFileNameForUser(String username) {
        // Remove caracteres inválidos para nomes de arquivo e converte para minúsculas
        return username.trim().replaceAll("[^a-zA-Z0-9.-]", "_").toLowerCase() + ".json";
    }
}