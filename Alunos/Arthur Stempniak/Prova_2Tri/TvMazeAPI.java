import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TvMazeAPI {
    private static final String API_URL = "https://api.tvmaze.com/search/shows?q=";

    public List<Serie> buscarSeriesPorNome(String termo) {
        List<Serie> series = new ArrayList<>();
        try {
            String encodedTermo = URLEncoder.encode(termo, StandardCharsets.UTF_8.name());
            URL url = new URL(API_URL + encodedTermo);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() != 200) {
                System.err.println("Falha na comunicação com a API: Código " + connection.getResponseCode());
                return Collections.emptyList();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return parseJsonDaApi(response.toString());

        } catch (Exception e) {
            System.err.println("Erro ao buscar séries na API: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private List<Serie> parseJsonDaApi(String json) {
        List<Serie> series = new ArrayList<>();
        if (!json.startsWith("[")) return series;

        int currentPos = 1;
        while (true) {
            int objStart = json.indexOf("{\"score\":", currentPos);
            if (objStart == -1) break;

            int objEnd = encontrarFimDoObjeto(json, objStart);
            if (objEnd == -1) break;

            String showWrapper = json.substring(objStart, objEnd + 1);
            String showJson = extrairObjetoInterno(showWrapper, "\"show\":");
            
            if (showJson != null) {
                String nome = extrairValorString(showJson, "\"name\"");
                if (nome.isEmpty()) continue;

                String idioma = extrairValorString(showJson, "\"language\"");
                List<String> generos = extrairListaString(showJson, "\"genres\"");
                String estado = extrairValorString(showJson, "\"status\"");
                String estreia = extrairValorString(showJson, "\"premiered\"");
                String fim = extrairValorString(showJson, "\"ended\"");

                String notaStr = extrairValorString(extrairObjetoInterno(showJson, "\"rating\":"), "\"average\"");
                double nota = 0.0;
                if(notaStr != null && !notaStr.equals("null") && !notaStr.isEmpty()){
                    nota = Double.parseDouble(notaStr);
                }

                String emissora = extrairValorString(extrairObjetoInterno(showJson, "\"network\":"), "\"name\"");
                if(emissora.isEmpty()){
                    emissora = extrairValorString(extrairObjetoInterno(showJson, "\"webChannel\":"), "\"name\"");
                }

                series.add(new Serie(nome, idioma, generos, nota, estado, estreia, fim, emissora));
            }
            currentPos = objEnd + 1;
        }
        return series;
    }

    private int encontrarFimDoObjeto(String json, int start) {
        int balance = 0;
        for (int i = start; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '{') {
                balance++;
            } else if (c == '}') {
                balance--;
            }
            if (balance == 0) {
                return i;
            }
        }
        return -1;
    }
    
    private String extrairObjetoInterno(String json, String chave) {
        int keyIndex = json.indexOf(chave);
        if (keyIndex == -1) return null;
        int objStart = json.indexOf('{', keyIndex);
        if (objStart == -1) return null;
        int objEnd = encontrarFimDoObjeto(json, objStart);
        if (objEnd == -1) return null;
        return json.substring(objStart, objEnd + 1);
    }
    
    private String extrairValorString(String json, String chave) {
        if (json == null) return "";
        int keyIndex = json.indexOf(chave);
        if (keyIndex == -1) return "";
        int valueStart = json.indexOf('"', keyIndex + chave.length());
        if (valueStart == -1) return "";
        int valueEnd = json.indexOf('"', valueStart + 1);
        if (valueEnd == -1) return "";
        return json.substring(valueStart + 1, valueEnd);
    }
    
    private List<String> extrairListaString(String json, String chave) {
        List<String> result = new ArrayList<>();
        int keyIndex = json.indexOf(chave);
        if (keyIndex == -1) return result;
        int arrayStart = json.indexOf('[', keyIndex);
        if (arrayStart == -1) return result;
        int arrayEnd = json.indexOf(']', arrayStart);
        if (arrayEnd == -1) return result;
        String arrayContent = json.substring(arrayStart + 1, arrayEnd);
        if(arrayContent.isEmpty()) return result;
        String[] items = arrayContent.split(",");
        for (String item : items) {
            result.add(item.trim().replace("\"", ""));
        }
        return result;
    }
}