package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClimaService {

    private final String apiKey;

    public ClimaService(String apiKey) {
        this.apiKey = apiKey;
    }

    public void buscarClima(String cidade) {
        try {
            String endpoint = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                    + cidade + "?unitGroup=metric&key=" + apiKey + "&include=current";

            URL url = new URL(endpoint);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            int codigoResposta = conexao.getResponseCode();
            if (codigoResposta != 200) {
                System.out.println("Erro: cidade inválida ou chave de API incorreta.");
                return;
            }

            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;

            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }

            leitor.close();
            exibirClima(resposta.toString());

        } catch (Exception e) {
            System.out.println("Erro ao buscar dados do clima: " + e.getMessage());
        }
    }

    private void exibirClima(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        if (!obj.has("currentConditions") || !obj.has("days")) {
            System.out.println("A cidade informada não foi encontrada ou os dados estão incompletos.");
            return;
        }

        JsonObject atual = obj.getAsJsonObject("currentConditions");
        JsonArray dias = obj.getAsJsonArray("days");

        if (dias.size() == 0 || atual == null) {
            System.out.println("Dados climáticos indisponíveis no momento para esta cidade.");
            return;
        }

        JsonObject hoje = dias.get(0).getAsJsonObject();

        double tempAtual = getAsDoubleSafe(atual, "temp");
        double tempMax = getAsDoubleSafe(hoje, "tempmax");
        double tempMin = getAsDoubleSafe(hoje, "tempmin");
        double umidade = getAsDoubleSafe(atual, "humidity");
        String condicao = getAsStringSafe(atual, "conditions");
        double precipitacao = getAsDoubleSafe(atual, "precip");
        double ventoVel = getAsDoubleSafe(atual, "windspeed");
        double ventoDir = getAsDoubleSafe(atual, "winddir");

        System.out.println("Clima em: " + obj.get("address").getAsString());
        System.out.println("Temperatura atual: " + tempAtual + "°C");
        System.out.println("Máxima do dia: " + tempMax + "°C");
        System.out.println("Mínima do dia: " + tempMin + "°C");
        System.out.println("Umidade: " + umidade + "%");
        System.out.println("Condição: " + condicao);
        System.out.println("Precipitação: " + precipitacao + " mm");
        System.out.println("Vento: " + ventoVel + " km/h (Direção: " + ventoDir + "°)");
    }

    private double getAsDoubleSafe(JsonObject obj, String key) {
        JsonElement e = obj.get(key);
        return (e != null && !e.isJsonNull()) ? e.getAsDouble() : 0.0;
    }

    private String getAsStringSafe(JsonObject obj, String key) {
        JsonElement e = obj.get(key);
        return (e != null && !e.isJsonNull()) ? e.getAsString() : "Desconhecido";
    }
}
