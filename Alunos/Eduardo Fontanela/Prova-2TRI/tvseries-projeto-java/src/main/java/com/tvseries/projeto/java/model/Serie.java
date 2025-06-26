package com.tvseries.projeto.java.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Serie {
    @SerializedName("name")
    private String nome;

    @SerializedName("language")
    private String idioma;

    @SerializedName("genres")
    private List<String> generos;

    private Rating rating;

    @SerializedName("status")
    private String estado;

    @SerializedName("premiered")
    private String dataEstreia;

    @SerializedName("ended")
    private String dataTermino;

    private Network network;

    public String getNome() { return nome; }
    public String getIdioma() { return idioma; }
    public List<String> getGeneros() { return generos; }
    public Rating getRating() { return rating; }
    public String getEstado() { return estado; }
    public String getDataEstreia() { return dataEstreia; }
    public String getDataTermino() { return dataTermino; }
    public Network getNetwork() { return network; }

    public static class Rating {
        @SerializedName("average")
        private Double notaGeral;
        public Double getNotaGeral() { return notaGeral; }
    }

    public static class Network {
        @SerializedName("name")
        private String nomeEmissora;
        public String getNomeEmissora() { return nomeEmissora; }
    }

    @Override
    public String toString() {
        String generosStr = (generos != null && !generos.isEmpty()) ? String.join(", ", generos) : "N/A";
        return "-----------------------------------\n" +
               "Nome: " + nome + "\n" +
               "Idioma: " + (idioma != null ? idioma : "N/A") + "\n" +
               "Gêneros: " + generosStr + "\n" +
               "Nota Geral: " + (rating != null && rating.notaGeral != null ? rating.notaGeral : "N/A") + "\n" +
               "Estado: " + (estado != null ? estado : "N/A") + "\n" +
               "Data de Estreia: " + (dataEstreia != null ? dataEstreia : "N/A") + "\n" +
               "Data de Término: " + (dataTermino != null ? dataTermino : "Em andamento") + "\n" +
               "Emissora: " + (network != null && network.nomeEmissora != null ? network.nomeEmissora : "N/A") + "\n" +
               "-----------------------------------\n";
    }
}