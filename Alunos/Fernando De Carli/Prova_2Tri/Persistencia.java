import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class Persistencia {
    static final String ARQUIVO = "usuario.json";

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void salvarUsuario(Usuario usuario) {
        try {
            Writer writer = new FileWriter(ARQUIVO);
            gson.toJson(usuario, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public static Usuario carregarUsuario() {
        try {
            Reader reader = new FileReader(ARQUIVO);
            Usuario usuario = gson.fromJson(reader, Usuario.class); // Lê JSON e transforma em objeto
            reader.close();
            return usuario;
        } catch (IOException e) {
            return null;
        }
    }
}

