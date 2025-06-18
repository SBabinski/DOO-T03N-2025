import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonStorage {
    private static final String FILE_NAME = "usuario.json";

    public void salvarUsuario(User user) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            pw.println("{");
            pw.println("  \"nome\": \"" + escape(user.getNome()) + "\",");
            pw.println("  \"seriesFavoritas\": " + seriesParaJson(user.getSeriesFavoritas()) + ",");
            pw.println("  \"seriesAssistidas\": " + seriesParaJson(user.getSeriesAssistidas()) + ",");
            pw.println("  \"seriesDesejadas\": " + seriesParaJson(user.getSeriesDesejadas()));
            pw.println("}");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados do usuário: " + e.getMessage());
        }
    }

    public User carregarUsuario() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new User(""); // Retorna um usuário vazio se o arquivo não existe
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonBuilder.append(line);
            }
            return jsonParaUsuario(jsonBuilder.toString());
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados do usuário: " + e.getMessage());
            return new User(""); // Retorna usuário vazio em caso de erro de leitura
        }
    }

    private String seriesParaJson(List<Serie> series) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < series.size(); i++) {
            Serie s = series.get(i);
            sb.append("    {\n");
            sb.append("      \"nome\": \"").append(escape(s.getNome())).append("\",\n");
            sb.append("      \"idioma\": \"").append(escape(s.getIdioma())).append("\",\n");
            sb.append("      \"generos\": ").append(listaStringParaJson(s.getGeneros())).append(",\n");
            sb.append("      \"notaGeral\": ").append(s.getNotaGeral()).append(",\n");
            sb.append("      \"estado\": \"").append(escape(s.getEstado())).append("\",\n");
            sb.append("      \"dataEstreia\": \"").append(escape(s.getDataEstreia())).append("\",\n");
            sb.append("      \"dataFim\": \"").append(escape(s.getDataFim())).append("\",\n");
            sb.append("      \"emissora\": \"").append(escape(s.getEmissora())).append("\"\n");
            sb.append("    }");
            if (i < series.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("  ]");
        return sb.toString();
    }

    private String listaStringParaJson(List<String> lista) {
        if (lista == null || lista.isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < lista.size(); i++) {
            sb.append("\"").append(escape(lista.get(i))).append("\"");
            if (i < lista.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    private User jsonParaUsuario(String json) {
        try {
            String nome = extrairValor(json, "\"nome\"", 0);
            User user = new User(nome);
            
            user.getSeriesFavoritas().addAll(extrairListaSeries(json, "\"seriesFavoritas\""));
            user.getSeriesAssistidas().addAll(extrairListaSeries(json, "\"seriesAssistidas\""));
            user.getSeriesDesejadas().addAll(extrairListaSeries(json, "\"seriesDesejadas\""));

            return user;
        } catch (Exception e) {
            System.err.println("Erro ao interpretar o arquivo JSON. Um novo perfil será criado.");
            return new User("");
        }
    }

    private List<Serie> extrairListaSeries(String json, String chave) {
        List<Serie> series = new ArrayList<>();
        int startIndex = json.indexOf(chave);
        if (startIndex == -1) return series;

        int arrayStart = json.indexOf('[', startIndex);
        int arrayEnd = json.indexOf(']', arrayStart);
        if (arrayStart == -1 || arrayEnd == -1) return series;
        
        String arrayContent = json.substring(arrayStart + 1, arrayEnd);
        int currentPos = 0;
        while (true) {
            int objStart = arrayContent.indexOf('{', currentPos);
            if (objStart == -1) break;
            
            int objEnd = arrayContent.indexOf('}', objStart);
            if (objEnd == -1) break;

            String serieJson = arrayContent.substring(objStart, objEnd + 1);

            String nome = extrairValor(serieJson, "\"nome\"", 0);
            String idioma = extrairValor(serieJson, "\"idioma\"", 0);
            double nota = Double.parseDouble(extrairValorNumerico(serieJson, "\"notaGeral\""));
            String estado = extrairValor(serieJson, "\"estado\"", 0);
            String estreia = extrairValor(serieJson, "\"dataEstreia\"", 0);
            String fim = extrairValor(serieJson, "\"dataFim\"", 0);
            String emissora = extrairValor(serieJson, "\"emissora\"", 0);
            List<String> generos = extrairListaString(serieJson, "\"generos\"");
            
            series.add(new Serie(nome, idioma, generos, nota, estado, estreia, fim, emissora));

            currentPos = objEnd + 1;
        }
        return series;
    }

    private List<String> extrairListaString(String json, String chave) {
        int startIndex = json.indexOf(chave);
        if (startIndex == -1) return Collections.emptyList();
        int arrayStart = json.indexOf('[', startIndex);
        int arrayEnd = json.indexOf(']', arrayStart);
        if (arrayStart == -1 || arrayEnd == -1) return Collections.emptyList();
        
        String content = json.substring(arrayStart + 1, arrayEnd);
        if(content.trim().isEmpty()) return Collections.emptyList();

        List<String> result = new ArrayList<>();
        String[] items = content.split(",");
        for(String item : items) {
            result.add(item.trim().replace("\"", ""));
        }
        return result;
    }

    private String extrairValor(String json, String chave, int fromIndex) {
        int idx = json.indexOf(chave, fromIndex);
        if (idx == -1) return "";
        int start = json.indexOf('"', idx + chave.length());
        if (start == -1) return "";
        int end = json.indexOf('"', start + 1);
        if (end == -1) return "";
        return json.substring(start + 1, end);
    }

    private String extrairValorNumerico(String json, String chave) {
        int idx = json.indexOf(chave);
        if(idx == -1) return "0.0";
        int colonIdx = json.indexOf(':', idx);
        if(colonIdx == -1) return "0.0";
        int endIdx = json.indexOf(',', colonIdx);
        if(endIdx == -1) {
            endIdx = json.indexOf('}', colonIdx);
        }
        return json.substring(colonIdx + 1, endIdx).trim();
    }
    
    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}