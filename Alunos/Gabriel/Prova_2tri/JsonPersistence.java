
package com.tvseriestracker;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonPersistence {
    private static final String FILE_NAME = "user_data.json";
    private Gson gson;

    public JsonPersistence() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void saveUser(User user) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(user, writer);
            System.out.println("Dados do usuário salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados do usuário: " + e.getMessage());
        }
    }

    public User loadUser() {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            Type userType = new TypeToken<User>() {}.getType();
            User user = gson.fromJson(reader, userType);
            if (user != null) {
                System.out.println("Dados do usuário carregados com sucesso.");
            } else {
                System.out.println("Nenhum dado de usuário encontrado. Criando novo usuário.");
            }
            return user;
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados do usuário: " + e.getMessage());
            System.out.println("Nenhum dado de usuário encontrado. Criando novo usuário.");
            return null; 
        }
    }
}


