//Armazena o nome do usuário
//Contém as listas


package com.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DadosUsuario implements Serializable {
    private String usuario;
    private List<Serie> assistidos = new ArrayList<>();
    private List<Serie> favoritos  = new ArrayList<>();
    private List<Serie> desejo      = new ArrayList<>();

    public String getUsuario() { return usuario; }
    public void setUsuario(String u) { this.usuario = u; }

    public List<Serie> getAssistidos() { return assistidos; }
    public List<Serie> getFavoritos()  { return favoritos; }
    public List<Serie> getDesejo()      { return desejo; }
}

