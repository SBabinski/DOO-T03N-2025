package seriesapp;

import com.google.gson.Gson;
import java.io.File; // Importa a classe File
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ServicoPersistencia {
    private static final String ARQUIVO_JSON = "dados_usuario.json";
    private final Gson gson = new Gson();

    public void salvarDados(Usuario usuario) {
        try (FileWriter writer = new FileWriter(ARQUIVO_JSON)) {
            gson.toJson(usuario, writer);
        } catch (IOException e) {
            System.err.println("ERRO: Falha ao salvar os dados. " + e.getMessage());
        }
    }

    public Usuario carregarDados() {
        try (FileReader reader = new FileReader(ARQUIVO_JSON)) {
            return gson.fromJson(reader, Usuario.class);
        } catch (IOException e) {
            return null;
        }
    }

    // NOVO MÉTODO PARA APAGAR O ARQUIVO DE DADOS
    public boolean apagarDados() {
        try {
            File arquivo = new File(ARQUIVO_JSON);
            if (arquivo.exists()) {
                return arquivo.delete(); // Retorna true se o arquivo foi apagado com sucesso
            }
            return true; // Se o arquivo não existe, consideramos a operação um sucesso
        } catch (SecurityException e) {
            System.err.println("ERRO: Problema de segurança ao tentar apagar o arquivo de dados.");
            return false;
        }
    }
}