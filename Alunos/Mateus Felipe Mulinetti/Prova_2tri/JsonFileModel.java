package org.aplicacao;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonFileModel {
    public String userName;
    public List<Series> favoritos;
    public List<Series> assistidas;
    public List<Series> paraAssistir;

    public JsonFileModel() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Series> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Series> favoritos) {
        this.favoritos = favoritos;
    }

    public List<Series> getAssistidas() {
        return assistidas;
    }

    public void setAssistidas(List<Series> assistidas) {
        this.assistidas = assistidas;
    }

    public List<Series> getParaAssistir() {
        return paraAssistir;
    }

    public void setParaAssistir(List<Series> paraAssistir) {
        this.paraAssistir = paraAssistir;
    }

    public void saveToFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("usuario.json"), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
