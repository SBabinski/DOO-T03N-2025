package org.example.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.model.Usuario;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonPersistencia {
    private static final String ARQUIVO_USUARIO = "usuario.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    static {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    public static void salvarUsuario(Usuario usuario) throws IOException {
        if (usuario == null) {
            throw new IOException("Usuário não pode ser nulo");
        }
        
        try {
            Files.createDirectories(Paths.get(ARQUIVO_USUARIO).getParent() != null 
                    ? Paths.get(ARQUIVO_USUARIO).getParent() 
                    : Paths.get("."));
                    
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(ARQUIVO_USUARIO), usuario);
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
            throw e;
        }
    }
    
    public static Usuario carregarUsuario() {
        File arquivo = new File(ARQUIVO_USUARIO);
        if (arquivo.exists() && arquivo.length() > 0) {
            try {
                return objectMapper.readValue(arquivo, Usuario.class);
            } catch (IOException e) {
                System.err.println("Erro ao carregar usuário: " + e.getMessage());
                e.printStackTrace();
                
                try {
                    File backup = new File(ARQUIVO_USUARIO + ".bak");
                    Files.copy(arquivo.toPath(), backup.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                    System.err.println("Backup do arquivo corrompido criado: " + backup.getAbsolutePath());
                } catch (IOException ex) {
                    System.err.println("Não foi possível criar backup: " + ex.getMessage());
                }
            }
        }
        return null;
    }
}
