package model;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

public class Usuario {
    private String nome;
    private List<Serie> favoritas = new ArrayList<>();
    private List<Serie> assistidas = new ArrayList<>();
    private List<Serie> desejoAssistir = new ArrayList<>();

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public List<Serie> getFavoritas() {
        return favoritas;
    }

    public List<Serie> getAssistidas() {
        return assistidas;
    }

    public List<Serie> getDesejoAssistir() {
        return desejoAssistir;
    }

    public static Usuario carregarOuCriarUsuario(String nome) {
        Gson gson = new Gson(); 
        
        new File("usuarios/").mkdirs();
        File arquivo = new File("usuarios/" + nome + ".json");

        if (arquivo.exists()) {
            try {
                String json = new String(java.nio.file.Files.readAllBytes(arquivo.toPath()));
                return gson.fromJson(json, Usuario.class);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar usuário. Criando novo.");
            }
        }

        Usuario novoUsuario = new Usuario(nome);

        try {
            String json = gson.toJson(novoUsuario);
            java.nio.file.Files.write(arquivo.toPath(), json.getBytes());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar novo usuário.");
        }
        
        return novoUsuario;
    }

    public void adicionarSerie(TipoSerie type, Serie serie) {
        switch (type) {
            case FAVORITA:
                if (!this.favoritas.contains(serie)) {
                    this.favoritas.add(serie);
                }
            break;

            case ASSISTIDA:
                if (!this.assistidas.contains(serie)) {
                    this.assistidas.add(serie);
                }
            break;
            
            case  DESEJO_ASSISTIR:
                if (!this.desejoAssistir.contains(serie)) {
                    this.desejoAssistir.add(serie);
                }
            break;
    
            default:
            break;
        }
        salvar();
    }

    public void removerSerie(TipoSerie type, Serie serie) {
        switch (type) {
            case FAVORITA:
                if (this.favoritas.contains(serie)) {
                    this.favoritas.remove(serie);
                }
            break;
            
            case ASSISTIDA:
                if (this.assistidas.contains(serie)) {
                    this.assistidas.remove(serie);
                }
            break;
            
            case DESEJO_ASSISTIR:
                if (this.desejoAssistir.contains(serie)) {
                    this.desejoAssistir.remove(serie);
                }
            break;
        
            default:
            break;
        }
        salvar();
    }

    public void salvar() {
        try {
            Gson gson = new Gson();
            File arquivo = new File("usuarios/" + nome + ".json");
            String json = gson.toJson(this);
            java.nio.file.Files.write(arquivo.toPath(), json.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar alterações no usuário.");
        }
    }
}