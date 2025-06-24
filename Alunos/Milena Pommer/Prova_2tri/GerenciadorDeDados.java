package com.trabalhotvmaze.series;

import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

/**
 * Serve para salvar os dados e carregar os mesmos quando necessário
 */
public class GerenciadorDeDados {
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    //Tradutor do Json
    public GerenciadorDeDados() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());
    }

    //Salva o objeto Usuario e suas informações em um arquivo Json
    public void salvarUsuario(Usuario usuario) {
        if (usuario == null || usuario.getNome() == null || usuario.getNome().isBlank()) {
            System.err.println("Erro: Não é possível salvar um usuário sem nome.");
            return;
        }
        // Cria o nome do arquivo dinamicamente
        String fileName = usuario.getNome().toLowerCase() + ".json";
        try {
        	//Tenta salvar o arquivo no disco
            objectMapper.writeValue(new File(fileName), usuario);
            System.out.println("Dados de '" + usuario.getNome() + "' salvos com sucesso em " + fileName);
        } catch (IOException e) {
        	// Se der erro, imprime a mensagem
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    // O método recebe o nome do usuário, transforma em um objeto Usuario na memória do pc
    public Usuario carregarUsuario(String nomeUsuario) {
        String fileName = nomeUsuario.toLowerCase() + ".json";
        File file = new File(fileName);
        
        if (file.exists()) {
            try {
                System.out.println("Carregando dados de " + fileName);
                return objectMapper.readValue(file, Usuario.class);
            } catch (IOException e) {
                System.err.println("Erro ao carregar os dados. " + e.getMessage());
            }
        }
        return null; // Retorna null se o arquivo do usuário não existe
    }
}