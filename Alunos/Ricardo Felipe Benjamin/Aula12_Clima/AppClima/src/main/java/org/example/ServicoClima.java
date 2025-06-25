package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ServicoClima {

    public static String buscarDadosClima(String cidade, String apiKey) {
        try {
            String urlString = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                    + cidade.replace(" ", "%20") +
                    "?unitGroup=metric&key=" + apiKey + "&contentType=json";

            URL url = new URL(urlString);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            int codigoResposta = conexao.getResponseCode();
            if (codigoResposta != 200) {
                System.out.println("Erro HTTP: " + codigoResposta);
                return null;
            }

            BufferedReader leitor = new BufferedReader(
                    new InputStreamReader(conexao.getInputStream())
            );

            StringBuilder resposta = new StringBuilder();
            String linha;
            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }

            leitor.close();
            return resposta.toString();

        } catch (Exception e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }
}
