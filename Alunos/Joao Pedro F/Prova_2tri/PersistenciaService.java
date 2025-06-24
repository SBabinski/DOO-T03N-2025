package com.joaoedro.tvmaze;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PersistenciaService {
    private static final String ARQUIVO_USUARIO = "usuario.json";
    private final Gson gson;
    
    public PersistenciaService() {
        // Configurar o Gson para formatar o JSON de forma legível
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }
    
    /**
     * Salva os dados do usuário em um arquivo JSON
     * @param usuario Objeto Usuario a ser salvo
     * @throws IOException Em caso de erro na escrita do arquivo
     */
    public void salvarUsuario(Usuario usuario) throws IOException {
        try (Writer writer = new FileWriter(ARQUIVO_USUARIO)) {
            gson.toJson(usuario, writer);
            System.out.println("Dados do usuário salvos com sucesso!");
        }
    }
    
    /**
     * Carrega os dados do usuário a partir de um arquivo JSON
     * @return Objeto Usuario carregado ou um novo objeto se o arquivo não existir
     * @throws IOException Em caso de erro na leitura do arquivo
     */
    public Usuario carregarUsuario() throws IOException {
        File arquivo = new File(ARQUIVO_USUARIO);
        
        // Se o arquivo não existir, retorna um novo usuário
        if (!arquivo.exists()) {
            System.out.println("Arquivo de usuário não encontrado. Criando novo usuário.");
            return new Usuario();
        }
        
        try (Reader reader = new FileReader(arquivo)) {
            Usuario usuario = gson.fromJson(reader, Usuario.class);
            System.out.println("Dados do usuário carregados com sucesso!");
            return usuario;
        }
    }
    
    /**
     * Verifica se existe um arquivo de dados do usuário
     * @return true se o arquivo existir, false caso contrário
     */
    public boolean existeArquivoUsuario() {
        File arquivo = new File(ARQUIVO_USUARIO);
        return arquivo.exists();
    }
}
