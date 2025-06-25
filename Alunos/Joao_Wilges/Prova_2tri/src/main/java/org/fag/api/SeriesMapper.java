package org.fag.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fag.model.Serie;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SeriesMapper {

    public List<Serie> mapJsonToSeriesList(String jsonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Serie> seriesEncontradas = new ArrayList<>();
        try {
            JsonNode jsonObject = objectMapper.readTree(jsonResponse);
            if (jsonObject.isArray()) {
                for (JsonNode itemNode : jsonObject) {
                    JsonNode showNode = itemNode.get("show");
                    if (showNode != null && !showNode.isNull()) {
                        Serie tempSerie = new Serie(
                                getStringOrDefault(showNode, "id", "0"),
                                getStringOrDefault(showNode, "name", "Indefinido"),
                                getStringOrDefault(showNode, "language", "Indefinido"),
                                getGenresListOrDefault(showNode, "genres", Collections.emptyList()),
                                Double.valueOf(getStringOrDefault(showNode.get("rating"), "average", "0.0")),
                                getStringOrDefault(showNode, "status", "Indefinido"),
                                parseDateOrDefault(showNode, "premiered", null),
                                parseDateOrDefault(showNode, "ended", null),
                                getStringOrDefault(showNode.get("network"), "name", "Indefinido")
                        );

                        seriesEncontradas.add(tempSerie);
                    }
                }
            } else {
                System.out.println("A resposta da API não é um array de resultados de busca.");
            }
            return seriesEncontradas;
        } catch (Exception e) {
            System.err.println("Erro ao processar JSON ou mapear série.");
            return new ArrayList<>();
        }
    }


    private String getStringOrDefault(JsonNode parentNode, String fieldName, String defaultValue) {
        if (parentNode == null || parentNode.isNull()) {
            return defaultValue;
        }
        JsonNode childNode = parentNode.get(fieldName);
        if (childNode != null && !childNode.isNull()) {
            return childNode.asText();
        }
        return defaultValue;
    }
    private LocalDate parseDateOrDefault(JsonNode parentNode, String fieldName, LocalDate defaultValue) {
        if (parentNode == null || parentNode.isNull()) {
            return defaultValue;
        }
        JsonNode dateNode = parentNode.get(fieldName);
        if (dateNode != null && !dateNode.isNull() && !dateNode.asText().isEmpty()) {
            try { return LocalDate.parse(dateNode.asText());
            } catch (DateTimeParseException e) {
                System.err.println("Erro ao parsear data '" + dateNode.asText() + "': " + e.getMessage());
                return defaultValue;
            }
        }
        return defaultValue;
    }
    private List<String> getGenresListOrDefault(JsonNode parentNode, String fieldName, List<String> defaultValue) {
        if (parentNode == null || parentNode.isNull()) {
            return defaultValue;
        }

        JsonNode genresNode = parentNode.get(fieldName);

        if (genresNode != null && !genresNode.isNull() && genresNode.isArray()) {
            List<String> genres = new ArrayList<>();
            for (JsonNode genreElement : genresNode) {
                genres.add(genreElement.asText());
            }
            return genres;
        }
        return defaultValue;
    }


}