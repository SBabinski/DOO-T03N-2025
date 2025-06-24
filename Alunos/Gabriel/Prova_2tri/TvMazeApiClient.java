
package com.tvseriestracker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TvMazeApiClient {
    private static final String BASE_URL = "https://api.tvmaze.com/";
    private Gson gson;

    public TvMazeApiClient() {
        gson = new Gson();
    }

    public List<SearchResult> searchShows(String query) throws Exception {
        String urlString = BASE_URL + "search/shows?q=" + query.replace(" ", "+");
        String jsonResponse = getApiResponse(urlString);
        return gson.fromJson(jsonResponse, new TypeToken<List<SearchResult>>() {}.getType());
    }

    public Show getShowDetails(int showId) throws Exception {
        String urlString = BASE_URL + "shows/" + showId;
        String jsonResponse = getApiResponse(urlString);
        return gson.fromJson(jsonResponse, Show.class);
    }

    private String getApiResponse(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            throw new Exception("Erro na requisição HTTP: " + responseCode);
        }
    }
}


