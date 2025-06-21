import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException; 
public class JsonDataManager {
    private static final String FILE_NAME = "dados_usuario.json";
    
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void salvarDados(Usuario usuario) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(usuario, writer);
        }
    }

    public Usuario carregarDados() throws IOException {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            return gson.fromJson(reader, Usuario.class);
        } catch (FileNotFoundException e) {
            
            return null;
        }
    }
}