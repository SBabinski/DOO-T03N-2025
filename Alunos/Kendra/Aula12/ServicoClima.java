package br.com.meuprojeto.tempo;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ServicoClima {
    
    // IMPORTANTE: Substitua pela sua chave da API!
    private static final String CHAVE_API = "SUA_CHAVE_API_AQUI"; 
    private static final String URL_API = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    public DadosClimaticos obterDadosClimaticos(String cidade) throws IOException, InterruptedException {
        // Codifica o nome da cidade para ser seguro na URL (trata espaços, acentos, etc.)
        String cidadeCodificada = URLEncoder.encode(cidade, StandardCharsets.UTF_8);

        // Monta a URL completa da requisição
        // unitGroup=metric -> para obter dados em Celsius, km/h, etc.
        // contentType=json -> para receber a resposta em formato JSON
        String urlCompleta = String.format("%s%s?unitGroup=metric&key=%s&contentType=json", URL_API, cidadeCodificada, CHAVE_API);

        // 1. Cria um cliente HTTP moderno
        HttpClient clienteHttp = HttpClient.new
}