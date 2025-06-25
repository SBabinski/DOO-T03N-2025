package com.trabalhotvmaze.series;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Serie {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String nome;
    @JsonProperty("language")
    private String idioma;
    @JsonProperty("genres")
    private List<String> generos;
    @JsonProperty("status")
    private String status;
    @JsonProperty("premiered")
    private String dataEstreia;
    @JsonProperty("ended")
    private String dataFim;
    @JsonProperty("rating")
    private Rating rating;
    @JsonProperty("network")
    private Network network;

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getIdioma() { return idioma; }
    public List<String> getGeneros() { return generos; }
    public String getStatus() { return status; }
    public Rating getRating() { return rating; }
    public Network getNetwork() { return network; }
    public String getDataEstreia() { return dataEstreia; }

    public LocalDate getDataEstreiaAsDate() {
        if (dataEstreia == null || dataEstreia.isBlank()) {
            return null;
        }
        return LocalDate.parse(dataEstreia, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public String getNomeEmissora() {
        return (network != null) ? network.getName() : "N/A";
    }

    public Double getNota() {
        return (rating != null && rating.getAverage() != null) ? rating.getAverage() : 0.0;
    }
    
    public String getGenerosFormatado() {
        return generos != null && !generos.isEmpty() ? generos.stream().collect(Collectors.joining(", ")) : "N/A";
    }

    public void exibirDetalhes() {
        System.out.println("----------------------------------------");
        System.out.println("Nome: " + nome);
        System.out.println("Idioma: " + (idioma != null ? idioma : "N/A"));
        System.out.println("Gêneros: " + getGenerosFormatado());
        System.out.println("Nota: " + getNota());
        System.out.println("Status: " + (status != null ? status : "N/A"));
        System.out.println("Data de Estreia: " + (dataEstreia != null ? dataEstreia : "N/A"));
        System.out.println("Data de Término: " + (dataFim != null ? dataFim : "Em andamento/N/A"));
        System.out.println("Emissora: " + getNomeEmissora());
        System.out.println("----------------------------------------");
    }

    // Usado para garantir que não foi adicionada a mesma série duas vezes na lista
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return id == serie.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    // Classes aninhadas para mapear o JSON
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Rating {
        @JsonProperty("average")
        private Double average;
        public Double getAverage() { return average; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Network {
        @JsonProperty("name")
        private String name;
        public String getName() { return name; }
    }
}