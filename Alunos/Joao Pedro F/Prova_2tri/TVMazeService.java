package com.joaoedro.tvmaze;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TVMazeService {
    private static final String BASE_URL = "https://api.tvmaze.com";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US );
    
    private final CloseableHttpClient httpClient;
    
    public TVMazeService( ) {
        this.httpClient = HttpClients.createDefault( );
    }
    
    /**
     * Busca séries pelo nome
     * @param nomeSerie Nome da série a ser buscada
     * @return Lista de séries encontradas
     * @throws IOException Em caso de erro na requisição
     */
    public List<Serie> buscarSeriesPorNome(String nomeSerie) throws IOException {
        String url = BASE_URL + "/search/shows?q=" + nomeSerie.replace(" ", "+");
        String responseJson = fazerRequisicaoGet(url);
        
        List<Serie> series = new ArrayList<>();
        JsonArray jsonArray = JsonParser.parseString(responseJson).getAsJsonArray();
        
        for (JsonElement element : jsonArray) {
            JsonObject showObj = element.getAsJsonObject().getAsJsonObject("show");
            Serie serie = converterJsonParaSerie(showObj);
            series.add(serie);
        }
        
        return series;
    }
    
    /**
     * Busca detalhes de uma série pelo ID
     * @param serieId ID da série
     * @return Objeto Serie com detalhes completos
     * @throws IOException Em caso de erro na requisição
     */
    public Serie buscarSeriePorId(int serieId) throws IOException {
        String url = BASE_URL + "/shows/" + serieId;
        String responseJson = fazerRequisicaoGet(url);
        
        JsonObject jsonObject = JsonParser.parseString(responseJson).getAsJsonObject();
        return converterJsonParaSerie(jsonObject);
    }
    
    /**
     * Faz uma requisição GET para a URL especificada
     * @param url URL para requisição
     * @return String com a resposta JSON
     * @throws IOException Em caso de erro na requisição
     */
    private String fazerRequisicaoGet(String url) throws IOException {
        HttpGet request = new HttpGet(url);
        
        try (CloseableHttpResponse response = httpClient.execute(request )) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
            throw new IOException("Resposta vazia da API");
        }
    }
    
    /**
     * Converte um objeto JSON em um objeto Serie
     * @param jsonObject Objeto JSON com dados da série
     * @return Objeto Serie preenchido
     */
    private Serie converterJsonParaSerie(JsonObject jsonObject) {
        int id = jsonObject.get("id").getAsInt();
        String nome = jsonObject.get("name").getAsString();
        
        Serie serie = new Serie(id, nome);
        
        // Idioma
        if (!jsonObject.get("language").isJsonNull()) {
            serie.setIdioma(jsonObject.get("language").getAsString());
        }
        
        // Gêneros
        List<String> generos = new ArrayList<>();
        if (!jsonObject.get("genres").isJsonNull()) {
            JsonArray generosArray = jsonObject.getAsJsonArray("genres");
            for (JsonElement genero : generosArray) {
                generos.add(genero.getAsString());
            }
        }
        serie.setGeneros(generos);
        
        // Nota
        if (jsonObject.has("rating") && !jsonObject.get("rating").isJsonNull() && 
            jsonObject.getAsJsonObject("rating").has("average") && 
            !jsonObject.getAsJsonObject("rating").get("average").isJsonNull()) {
            serie.setNota(jsonObject.getAsJsonObject("rating").get("average").getAsDouble());
        }
        
        // Status
        if (!jsonObject.get("status").isJsonNull()) {
            serie.setStatus(StatusSerie.fromString(jsonObject.get("status").getAsString()));
        }
        
        // Datas
        try {
            if (!jsonObject.get("premiered").isJsonNull()) {
                String dataEstreiaStr = jsonObject.get("premiered").getAsString();
                Date dataEstreia = DATE_FORMAT.parse(dataEstreiaStr);
                serie.setDataEstreia(dataEstreia);
            }
            
            if (jsonObject.has("ended") && !jsonObject.get("ended").isJsonNull()) {
                String dataTerminoStr = jsonObject.get("ended").getAsString();
                Date dataTermino = DATE_FORMAT.parse(dataTerminoStr);
                serie.setDataTermino(dataTermino);
            }
        } catch (ParseException e) {
            System.err.println("Erro ao converter data: " + e.getMessage());
        }
        
        // Emissora
        if (jsonObject.has("network") && !jsonObject.get("network").isJsonNull()) {
            JsonObject networkObj = jsonObject.getAsJsonObject("network");
            if (networkObj.has("name") && !networkObj.get("name").isJsonNull()) {
                serie.setEmissora(networkObj.get("name").getAsString());
            }
        }
        
        // Resumo
        if (!jsonObject.get("summary").isJsonNull()) {
            // Remove as tags HTML do resumo
            String resumo = jsonObject.get("summary").getAsString()
                    .replaceAll("<[^>]*>", "");
            serie.setResumo(resumo);
        }
        
        // Imagem
        if (jsonObject.has("image") && !jsonObject.get("image").isJsonNull()) {
            JsonObject imageObj = jsonObject.getAsJsonObject("image");
            if (imageObj.has("medium") && !imageObj.get("medium").isJsonNull()) {
                serie.setImagemUrl(imageObj.get("medium").getAsString());
            }
        }
        
        return serie;
    }
    
    /**
     * Fecha o cliente HTTP
     * @throws IOException Em caso de erro ao fechar
     */
    public void fechar() throws IOException {
        if (httpClient != null ) {
            httpClient.close( );
        }
    }
}
