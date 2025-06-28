package Alunos.Adriel.Aula12;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final String API_KEY = "B6X5LQC93KM6N6A3HSWLM2SWG";
    private static final String URL_API = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite o nome da cidade: ");
        String cidade = entrada.nextLine();
        entrada.close();

        try {
            String url = URL_API + URLEncoder.encode(cidade, StandardCharsets.UTF_8) + "?unitGroup=metric&lang=pt&key=" + API_KEY;
            System.out.println("Buscando dados em: " + url);

            String resposta = requisitarHttp(url);

            if (resposta == null || resposta.isEmpty()) {
                System.out.println("Infelizmente não foi possível achar uma respota ou a resposta da API é nula.");
                return;
            }

            if (resposta.contains("\"days\":[]")) {
                System.out.println("Cidade não encontrada ou sem dados de clima disponíveis para a localização informada.");
                return;
            }

            exibirDadosClima(resposta);

        } catch (Exception e) {
            System.err.println("Erro ao buscar informações do clima: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String requisitarHttp(String urlStr) throws Exception {
        URL url = new URI(urlStr).toURL();
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");
        conexao.setConnectTimeout(5000);
        conexao.setReadTimeout(5000);

        int codigoResposta = conexao.getResponseCode();
        if (codigoResposta != HttpURLConnection.HTTP_OK) {
            System.err.println("Erro na requisição HTTP.\n" +  codigoResposta);
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(conexao.getErrorStream()))) {
                String errorLine;
                StringBuilder errorContent = new StringBuilder();
                while ((errorLine = errorReader.readLine()) != null) {
                    errorContent.append(errorLine);
                }
                System.err.println("Mensagem de erro da API: " + errorContent.toString());
            }
            return null;
        }

        try (BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()))) {
            String linha;
            StringBuilder conteudo = new StringBuilder();
            while ((linha = leitor.readLine()) != null) {
                conteudo.append(linha);
            }
            return conteudo.toString();
        } finally {
            conexao.disconnect();
        }
    }

    private static void exibirDadosClima(String json) {
        String enderecoResolvido = extrairTexto(json, "\"resolvedAddress\":\"([^\"]+)\"");
        String jsonHoje = extrairPrimeiroObjeto(json);

        if (jsonHoje == null) {
            System.out.println("Dados do dia atual não encontrados na resposta da API.");
            return;
        }

        double temperatura = extrairNumero(jsonHoje, "\"temp\":([0-9.\\-]+)");
        double temperaturaMaxima = extrairNumero(jsonHoje, "\"tempmax\":([0-9.\\-]+)");
        double temperaturaMinima = extrairNumero(jsonHoje, "\"tempmin\":([0-9.\\-]+)");
        double umidade = extrairNumero(jsonHoje, "\"humidity\":([0-9.\\-]+)");
        String condicoes = extrairTexto(jsonHoje, "\"conditions\":\"([^\"]+)\"");
        double precipitacao = extrairNumero(jsonHoje, "\"precip\":([0-9.\\-]+)");
        double ventoVelocidade = extrairNumero(jsonHoje, "\"windspeed\":([0-9.\\-]+)");
        double ventoDirecao = extrairNumero(jsonHoje, "\"winddir\":([0-9.\\-]+)");

        System.out.println("\n--- Informações do Clima ---");
        System.out.println("Local: " + (enderecoResolvido.equals("-") ? "Não disponível" : enderecoResolvido));
        System.out.printf("Temperatura atual: %.1f°C%n", temperatura);
        System.out.printf("Temperatura máxima do dia: %.1f°C%n", temperaturaMaxima);
        System.out.printf("Temperatura mínima do dia: %.1f°C%n", temperaturaMinima);
        System.out.printf("Umidade do ar: %.0f%%%n", umidade);
        System.out.println("Condição do tempo: " + (condicoes.equals("-") ? "Não disponível" : condicoes));

        if (!Double.isNaN(precipitacao) && precipitacao > 0) {
            System.out.printf("Precipitação: %.1f mm%n", precipitacao);
        } else {
            System.out.println("Precipitação: 0 mm");
        }

        if (!Double.isNaN(ventoVelocidade) && !Double.isNaN(ventoDirecao)) {
            System.out.printf("Vento: %.1f km/h, direção %.0f°%n", ventoVelocidade, ventoDirecao);
        } else {
            System.out.println("Informações de vento não disponíveis.");
        }
        System.out.println("----------------------------");
    }

    private static double extrairNumero(String texto, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(texto);
        if (matcher.find()) {
            try {
                return Double.parseDouble(matcher.group(1));
            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter número para " + regex + ": " + matcher.group(1));
                return Double.NaN;
            }
        }
        return Double.NaN;
    }

    private static String extrairTexto(String texto, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(texto);
        return matcher.find() ? matcher.group(1) : "-";
    }

    private static String extrairPrimeiroObjeto(String texto) {
        Matcher matcher = Pattern.compile("\"days\":\\[\\s*(\\{[^}]*\\})").matcher(texto);
        return matcher.find() ? matcher.group(1) : null;
    }
}