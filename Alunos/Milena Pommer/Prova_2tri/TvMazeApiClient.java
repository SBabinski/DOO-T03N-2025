package com.trabalhotvmaze.series;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Responsável pela comunicação com a  API do TV maze
 * pega os dados na internet (Requisição HTTP) e traduz o texto em JSON para Jackson.
 */

public class TvMazeApiClient {
	
	// URL base para buscar séries na API. O item de pesquisa será adicionado no final do endereço base.
    private final String API_BASE_URL = "https://api.tvmaze.com/search/shows?q=";
    
    //Cliente HTTP moderdo que envia requisições e busca respostas.
    private final HttpClient client = HttpClient.newHttpClient();
    
    //Objeto Jackson que converse Json em java
    private final ObjectMapper objectMapper = new ObjectMapper();

    //Construtor
    public TvMazeApiClient() {
        // Serve para lidar com datas tipo LocalDate
        objectMapper.registerModule(new JavaTimeModule());
    }
    
    /**
     * Faz uma busca de séries na API pelo nome informado;
     * @param nome - nome informado pra busca na API;
     * @return - retorna uma lista de objetos Serie que correspondem ao nome da busca ou uma lista vazia.
     */
    public List<Serie> buscarSeries(String nome) {
        try {
        	// Prepara o nome da série (codifica) para um formato seguro para URLs
            String encodedName = URLEncoder.encode(nome, StandardCharsets.UTF_8);
            String url = API_BASE_URL + encodedName;

            // Constrói a requisição do tipo get para a URL da API
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // Envia a requisição pra internet e pausa o programa até receber a resposta como Srtring
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verifica se a requisição deu certo (Código de status 200)
            if (response.statusCode() == 200) {
            	
            	// Se for bem sucedida  a requisição, o sistema transforma a resposta Json, usando o jackson, em uma lista de objetos "ResultadoBusca" java. 
                List<ResultadoBusca> resultados = objectMapper.readValue(response.body(), new TypeReference<>() {});
                //A API retorna uma lista "ResultadoBusca" em Java que contém a Série.
                // O Stream pega somente o objeto Serie de cada ResultadoBUsca.
                return resultados.stream()
                                 .map(ResultadoBusca::getShow)
                                 .collect(Collectors.toList());
            } else {
                System.out.println("Erro ao buscar séries. Status code: " + response.statusCode());
                //Retorna uma lista vazia de a API retornar erro.
                return Collections.emptyList();
            }

        } catch (Exception e) {
        	// Captura qualquer exceção (e) que possa ocorrer durante a chamada (erro de rede, parsing).
            System.err.println("Ocorreu um erro na chamada da API: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
