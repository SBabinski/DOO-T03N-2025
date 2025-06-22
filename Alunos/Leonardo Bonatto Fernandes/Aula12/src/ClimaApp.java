import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ClimaApp {

    private static final String API_KEY = "FB54LDVJYJF3JKBJ2BQ2MGVE2"; // coloque sua chave aqui
    private static final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            System.out.print("Digite o nome da cidade: ");
            String cidade = sc.nextLine();

            try {
                Clima clima = consultarClima(cidade);
                System.out.println("\nClima em " + cidade + ":");
                System.out.println("Temperatura atual: " + clima.tempAtual + " °C");
                System.out.println("Temperatura máxima: " + clima.tempMax + " °C");
                System.out.println("Temperatura mínima: " + clima.tempMin + " °C");
                System.out.println("Umidade: " + clima.umidade + " %");
                System.out.println("Condição do tempo: " + clima.condicao);
                System.out.println("Precipitação: " + clima.precip + " mm");
                System.out.println("Velocidade do vento: " + clima.ventoVel + " km/h");
                System.out.println("Direção do vento: " + clima.ventoDir + " °");
            } catch (Exception e) {
                System.out.println("Erro ao consultar clima: " + e.getMessage());
                System.out.println("Encerrando programa devido ao erro.");
                break;
            }

            System.out.print("\nDeseja consultar outra cidade? (s/n): ");
            String resp = sc.nextLine().toLowerCase();
            if (!resp.equals("s")) {
                continuar = false;
            }
        }

        System.out.println("Encerrando programa.");
        sc.close();
    }

    private static Clima consultarClima(String cidade) throws Exception {
        String urlStr = BASE_URL + cidade.replace(" ", "%20") + "?unitGroup=metric&key=" + API_KEY + "&contentType=json";

        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();
        if (status != 200) {
            throw new RuntimeException("Erro HTTP: " + status);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder resposta = new StringBuilder();
        String linha;
        while ((linha = in.readLine()) != null) {
            resposta.append(linha);
        }
        in.close();

        String json = resposta.toString();

        double tempAtual = extrairDouble(json, "\"temp\":", ",");
        double tempMax = extrairDouble(json, "\"tempmax\":", ",");
        double tempMin = extrairDouble(json, "\"tempmin\":", ",");
        double umidade = extrairDouble(json, "\"humidity\":", ",");
        String condicao = extrairString(json, "\"conditions\":\"", "\"");
        double precip = extrairDouble(json, "\"precip\":", ",");
        double ventoVel = extrairDouble(json, "\"windspeed\":", ",");
        double ventoDir = extrairDouble(json, "\"winddir\":", ",");

        return new Clima(tempAtual, tempMax, tempMin, umidade, condicao, precip, ventoVel, ventoDir);
    }

    private static double extrairDouble(String texto, String chave, String delimitador) {
        int posIni = texto.indexOf(chave);
        if (posIni == -1) return Double.NaN;
        posIni += chave.length();
        int posFim = texto.indexOf(delimitador, posIni);
        if (posFim == -1) posFim = texto.length();
        try {
            return Double.parseDouble(texto.substring(posIni, posFim));
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }

    private static String extrairString(String texto, String inicio, String fim) {
        int posIni = texto.indexOf(inicio);
        if (posIni == -1) return "N/A";
        posIni += inicio.length();
        int posFim = texto.indexOf(fim, posIni);
        if (posFim == -1) return "N/A";
        return texto.substring(posIni, posFim);
    }

    private static class Clima {
        double tempAtual, tempMax, tempMin, umidade, precip, ventoVel, ventoDir;
        String condicao;

        public Clima(double tempAtual, double tempMax, double tempMin, double umidade,
                     String condicao, double precip, double ventoVel, double ventoDir) {
            this.tempAtual = tempAtual;
            this.tempMax = tempMax;
            this.tempMin = tempMin;
            this.umidade = umidade;
            this.condicao = condicao;
            this.precip = precip;
            this.ventoVel = ventoVel;
            this.ventoDir = ventoDir;
        }
    }
}
