import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServicoAPI {
    private static final String API_KEY = "QX5DBT7JPQH3P5T6HF6VECUHF";

    public static Clima buscarClima(String cidade) {
        try {
            String endpoint = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                    + cidade.replace(" ", "%20")
                    + "?unitGroup=metric&key=" + API_KEY + "&contentType=json";

            URL url = new URL(endpoint);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;
            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }
            leitor.close();

            String json = resposta.toString();

            // Extrair dados atuais
            String blocoAtual = extrairBloco(json, "\"currentConditions\":{", "}");
            String temperatura = extrair(blocoAtual, "\"temp\":", ",");
            String condicao = traduzirCondicao(extrair(blocoAtual, "\"conditions\":\"", "\""));
            String umidade = extrair(blocoAtual, "\"humidity\":", ",");
            String vento = extrair(blocoAtual, "\"windspeed\":", ",");
            String direcaoVento = extrair(blocoAtual, "\"winddir\":", ",");

            // Extrair dados do dia
            String blocoDia = extrairBloco(json, "\"days\":[{", "}]");
            String tempMax = extrair(blocoDia, "\"tempmax\":", ",");
            String tempMin = extrair(blocoDia, "\"tempmin\":", ",");
            String precipitacao = extrair(blocoDia, "\"precip\":", ",");

            // Criar objeto Clima
            Clima clima = new Clima(
                    cidade,
                    temperatura + "°C",
                    tempMax + "°C",
                    tempMin + "°C",
                    umidade + "%",
                    condicao,
                    precipitacao + " mm",
                    vento + " km/h",
                    direcaoVento + "°"
            );

            return clima;

        } catch (Exception e) {
            System.out.println("Erro ao buscar dados: " + e.getMessage());
            return null;
        }
    }

    private static String extrair(String texto, String inicio, String fim) {
        try {
            int posInicio = texto.indexOf(inicio);
            if (posInicio == -1) return "N/A";
            posInicio += inicio.length();
            int posFim = texto.indexOf(fim, posInicio);
            if (posFim == -1) return "N/A";
            return texto.substring(posInicio, posFim).replace("\"", "").trim();
        } catch (Exception e) {
            return "N/A";
        }
    }

    private static String extrairBloco(String texto, String inicio, String fim) {
        try {
            int posInicio = texto.indexOf(inicio);
            if (posInicio == -1) return "";

            posInicio += inicio.length();
            int contadorChaves = 1;
            int pos = posInicio;

            while (pos < texto.length() && contadorChaves > 0) {
                char c = texto.charAt(pos);
                if (c == '{') contadorChaves++;
                if (c == '}') contadorChaves--;
                pos++;
            }

            if (contadorChaves == 0) {
                return texto.substring(posInicio, pos - 1);
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    private static String traduzirCondicao(String condicao) {
        condicao = condicao.toLowerCase();
        condicao = condicao.replace("partially cloudy", "Parcialmente nublado");
        condicao = condicao.replace("cloudy", "Nublado");
        condicao = condicao.replace("clear", "Limpo");
        condicao = condicao.replace("rain", "Chuva");
        condicao = condicao.replace("snow", "Neve");
        condicao = condicao.replace("overcast", "Encoberto");
        condicao = condicao.replace("mist", "Névoa");
        condicao = condicao.replace("fog", "Nevoeiro");
        condicao = condicao.replace("drizzle", "Garoa");
        condicao = condicao.replace("thunderstorm", "Tempestade");
        condicao = condicao.replace("windy", "Ventando");
        condicao = condicao.replace("haze", "Névoa seca");

        return condicao.substring(0, 1).toUpperCase() + condicao.substring(1);
    }
}
