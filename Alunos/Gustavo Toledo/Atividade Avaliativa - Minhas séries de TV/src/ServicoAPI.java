import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ServicoAPI {

    public static List<Serie> buscarSeries(String nomeBusca) throws Exception {
        List<Serie> lista = new ArrayList<>();

        String urlString = "https://api.tvmaze.com/search/shows?q=" + nomeBusca.replace(" ", "%20");
        URL url = new URL(urlString);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        StringBuilder resposta = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            resposta.append(inputLine);
        }
        in.close();

        String json = resposta.toString();

        String[] itens = json.split("\\{\"score\":");
        for (int i = 1; i < itens.length; i++) {
            String bloco = itens[i];
            String showBloco = bloco.substring(bloco.indexOf("\"show\":{") + 7);
            int fimBloco = showBloco.indexOf("}}") + 1;
            if (fimBloco == 0) fimBloco = showBloco.length();
            showBloco = showBloco.substring(0, fimBloco);

            String nome = extrair(showBloco, "\"name\":\"", "\"");
            if (!nome.toLowerCase().contains(nomeBusca.toLowerCase())) {
                continue;
            }

            String idioma = traduzirIdioma(extrair(showBloco, "\"language\":\"", "\""));
            String status = traduzirStatus(extrair(showBloco, "\"status\":\"", "\""));
            String dataEstreia = extrair(showBloco, "\"premiered\":\"", "\"");
            String dataFim = showBloco.contains("\"ended\":null") ? "Ainda em exibição" : extrair(showBloco, "\"ended\":\"", "\"");

            String emissora = "N/A";
            if (showBloco.contains("\"network\":")) {
                emissora = extrair(showBloco.substring(showBloco.indexOf("\"network\":")), "\"name\":\"", "\"");
            } else if (showBloco.contains("\"webChannel\":")) {
                emissora = extrair(showBloco.substring(showBloco.indexOf("\"webChannel\":")), "\"name\":\"", "\"");
            }

            List<String> generos = new ArrayList<>();
            String generoBloco = extrair(showBloco, "\"genres\":[", "]");
            if (!generoBloco.isEmpty()) {
                String[] gens = generoBloco.replace("\"", "").split(",");
                for (String g : gens) {
                    if (!g.trim().isEmpty()) {
                        generos.add(g.trim());
                    }
                }
            }

            Serie serie = new Serie(nome, idioma, generos, status, dataEstreia, dataFim, emissora);
            lista.add(serie);
        }

        return lista;
    }

    private static String extrair(String texto, String inicio, String fim) {
        int start = texto.indexOf(inicio);
        if (start == -1) return "Não encontrado";
        start += inicio.length();
        int end = texto.indexOf(fim, start);
        if (end == -1) return "Não encontrado";
        return texto.substring(start, end);
    }

    private static String traduzirIdioma(String idioma) {
        return idioma.equalsIgnoreCase("English") ? "Inglês" : idioma;
    }

    private static String traduzirStatus(String status) {
        return switch (status.toLowerCase()) {
            case "ended" -> "Encerrada";
            case "running" -> "Em andamento";
            case "to be determined", "tbd" -> "Indefinido";
            default -> status;
        };
    }
}
