package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeriesManager {

    public static Serie buscarSerie(String nome) {
        try {
            String apiUrl = "https://api.tvmaze.com/singlesearch/shows?q=" + nome.replace(" ", "+");
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            String json;
            try (InputStream inputStream = conn.getInputStream();
                Scanner scanner = new Scanner(inputStream)) {
                scanner.useDelimiter("\\A"); 
                json = scanner.hasNext() ? scanner.next() : "";
            }

            String titulo = extrairCampo(json, "\"name\":\"([^\"]+)\"");
            String idioma = extrairCampo(json, "\"language\":\"([^\"]+)\"");

            List<String> generos = new ArrayList<>();
            Matcher m = Pattern.compile("\"genres\":\\[(.*?)\\]").matcher(json);
            if (m.find()) {
                String generosStr = m.group(1);
                Matcher mg = Pattern.compile("\"([^\"]+)\"").matcher(generosStr);
                while (mg.find()) {
                    generos.add(mg.group(1));
                }
            }

            double nota = 0.0;
            Matcher mNota = Pattern.compile("\"average\":(null|[0-9.]+)").matcher(json);
            if (mNota.find()) {
                String notaStr = mNota.group(1);
                if (!"null".equals(notaStr)) {
                    nota = Double.parseDouble(notaStr);
                }
            }

            String status = extrairCampo(json, "\"status\":\"([^\"]+)\"");
            String estreia = extrairCampo(json, "\"premiered\":\"([^\"]+)\"");
            if (estreia == null) estreia = "Sem data";
            String termino = extrairCampo(json, "\"ended\":\"([^\"]+)\"");
            if (termino == null) termino = "Ainda em exibição";

            String emissora = "Desconhecida";
            Matcher mNetwork = Pattern.compile("\"network\":\\{[^}]*\"name\":\"([^\"]+)\"").matcher(json);
            if (mNetwork.find()) {
                emissora = mNetwork.group(1);
            }

            return new Serie(titulo, idioma, generos, nota, status, estreia, termino, emissora);

        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao buscar série: Série '"+nome+"' não encontrada");
            return null;
        }
    }

    private static String extrairCampo(String json, String regex) {
        Matcher m = Pattern.compile(regex).matcher(json);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }
}
