package org.fag.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class DataManager {
    private final ObjectMapper objectMapper;
    private static final String DATA_FILE = "app_data.json";

    public DataManager() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void saveData(AppData data) {
        try {
            objectMapper.writeValue(new File(DATA_FILE), data);
            System.out.println("Dados salvos com sucesso em: " + DATA_FILE);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public AppData loadData() {
        File file = new File(DATA_FILE);
        if (file.exists() && file.length() > 0) {
            try {
                System.out.println("Carregando dados de: " + DATA_FILE);
                return objectMapper.readValue(file, AppData.class);
            } catch (IOException e) {
                System.err.println("Erro ao carregar dados: " + e.getMessage());
                e.printStackTrace();
                return new AppData(); // Retorna um objeto vazio em caso de erro de leitura
            }
        }
        System.out.println("Arquivo de dados não encontrado ou vazio. Iniciando com dados novos.");
        return new AppData();
    }
}