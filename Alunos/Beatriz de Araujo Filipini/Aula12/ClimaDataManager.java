import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
public class ClimaDataManager {
   
    private static final String FILE_NAME = "dados_clima.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public void salvarUltimaConsulta(Clima ultimaConsulta) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(ultimaConsulta, writer);
            System.out.println("Dados da última consulta salvos em " + FILE_NAME);
        }
    }
    public Clima carregarUltimaConsulta() throws IOException {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            return gson.fromJson(reader, Clima.class);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de dados não encontrado. Iniciando com dados vazios.");
            return null;
        }
    }
}