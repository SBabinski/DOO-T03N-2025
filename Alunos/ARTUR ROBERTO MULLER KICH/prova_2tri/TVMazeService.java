package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.model.Serie;
import org.example.model.SerieResultado;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TVMazeService {
    private static final String BASE_URL = "http://api.tvmaze.com";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    static {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    public List<Serie> buscarSeries(String query) throws IOException {
        if (query == null || query.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String url = BASE_URL + "/search/shows?q=" + encodedQuery;
        
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();
                
                if (statusCode == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String json = EntityUtils.toString(entity);
                        List<SerieResultado> resultados = objectMapper.readValue(json, new TypeReference<List<SerieResultado>>() {});
                        
                        List<Serie> series = new ArrayList<>();
                        for (SerieResultado resultado : resultados) {
                            if (resultado.getSerie() != null) {
                                series.add(resultado.getSerie());
                            }
                        }
                        return series;
                    }
                    return new ArrayList<>();
                } else {
                    throw new IOException("Erro ao buscar séries. Código de status: " + statusCode);
                }
            }
        } catch (Exception e) {
            throw new IOException("Falha na comunicação com a API TVMaze: " + e.getMessage(), e);
        }
    }
    
    public Serie buscarSeriePorId(Long id) throws IOException {
        if (id == null) {
            return null;
        }
        
        String url = BASE_URL + "/shows/" + id;
        
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();
                
                if (statusCode == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String json = EntityUtils.toString(entity);
                        return objectMapper.readValue(json, Serie.class);
                    }
                    return null;
                } else {
                    throw new IOException("Erro ao buscar série. Código de status: " + statusCode);
                }
            }
        } catch (Exception e) {
            throw new IOException("Falha na comunicação com a API TVMaze: " + e.getMessage(), e);
        }
    }
    
    public List<Serie> carregarDadosPreDefinidos() throws IOException {
        String[] seriesPopulares = {"Breaking Bad", "Game of Thrones", "Friends", "Stranger Things", "The Office"};
        List<Serie> series = new ArrayList<>();
        
        for (String nomeSerie : seriesPopulares) {
            try {
                List<Serie> resultados = buscarSeries(nomeSerie);
                if (!resultados.isEmpty()) {
                    series.add(resultados.get(0));
                }
            } catch (Exception e) {
                System.err.println("Erro ao carregar série pré-definida '" + nomeSerie + "': " + e.getMessage());
            }
        }
        
        return series;
    }
}
