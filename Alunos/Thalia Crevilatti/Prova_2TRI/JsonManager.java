import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

// Classe que gerencia o salvamento e carregamento de usuários em um arquivo JSON

public class JsonManager {
    private static final String CAMINHO_ARQUIVO = "usuario.json";

    public static void salvarUsuario(Usuario usuario) { // Método para salvar o usuário em um arquivo JSON
        try (Writer writer = new FileWriter(CAMINHO_ARQUIVO)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(usuario, writer);
            System.out.println("Usuário salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public static Usuario carregarUsuario() { // Método para carregar o usuário de um arquivo JSON
        try (Reader reader = new FileReader(CAMINHO_ARQUIVO)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Usuario.class);
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
            return null;
        }
    }
}