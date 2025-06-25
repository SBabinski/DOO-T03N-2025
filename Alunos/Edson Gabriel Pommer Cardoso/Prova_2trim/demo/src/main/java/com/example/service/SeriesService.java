//Constrói URL para pesquisar por nome
//Faz requisição TVmaze
//Extrai dados do JSON
//Pergunta em qual lista adicionar a série



package com.example.service;

import com.example.model.DadosUsuario;
import com.example.model.Serie;
import com.example.util.JsonUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class SeriesService {
    private final String apiUrl;
    private final Scanner scanner;

    public SeriesService(String apiUrl, Scanner scanner) {
        this.apiUrl  = apiUrl;
        this.scanner = scanner;
    }

    public String procurarSerie(DadosUsuario dados) {
        try {
            System.out.println("Digite o nome da série que deseja buscar:");
            String nomeSerie = scanner.nextLine();
            String urlStr = apiUrl.trim() + "/search/shows?q=" + nomeSerie.replace(" ", "%20");

            URL url = java.net.URI.create(urlStr).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                conn.disconnect();

                String response = sb.toString();
                String showJson = JsonUtils.extractShowObject(response);
                if (showJson.isEmpty()) {
                    System.out.println("Série não encontrada.");
                    return null;
                }

                String nome = JsonUtils.extrairCampo(showJson, "\"name\":\"");
                String idioma = JsonUtils.extrairCampo(showJson, "\"language\":\"");
                String generos = JsonUtils.extrairLista(showJson, "\"genres\":[", "]");
                double nota = JsonUtils.extrairCampoNumerico(showJson, "\"average\":")
                                        .map(Double::parseDouble)
                                        .orElse(0.0);
                String status = JsonUtils.extrairCampo(showJson, "\"status\":\"");
                String estreia = JsonUtils.extrairCampo(showJson, "\"premiered\":\"");
                String fim = JsonUtils.extrairCampo(showJson, "\"ended\":\"");
                String emissora = JsonUtils.extractNetworkName(showJson);

                System.out.println("========== Detalhes da Série ==========");
                System.out.println("Nome: " + nome);
                System.out.println("Idioma: " + idioma);
                System.out.println("Gêneros: " + generos);
                System.out.println("Nota geral: " + nota);
                System.out.println("Estado: " + status);
                System.out.println("Data de estreia: " + estreia);
                System.out.println("Data de término: " + fim);
                System.out.println("Emissora: " + emissora);
                System.out.println("========================================");

                System.out.println("Deseja adicionar esta série a alguma lista?");
                System.out.println("Digite os números separados por vírgula se quiser adicionar a mais de uma lista:");
                System.out.println("[1] - Assistidos");
                System.out.println("[2] - Favoritos");
                System.out.println("[3] - Desejo assistir");
                System.out.println("[0] - Não adicionar");
                String escolha = scanner.nextLine().replace(" ", "");

                Serie serie = new Serie(nome, idioma, generos, nota, status, estreia, fim, emissora);

                if (!escolha.equals("0")) {
                    String[] opcoes = escolha.split(",");
                    for (String opcao : opcoes) {
                        switch (opcao) {
                            case "1" -> {
                                List<Serie> assistidos = dados.getAssistidos();
                                if (!assistidos.contains(serie)) {
                                    assistidos.add(serie);
                                    System.out.println("Série adicionada à lista de assistidos.");
                                } else {
                                    System.out.println("Série já está na lista de assistidos.");
                                }
                            }
                            case "2" -> {
                                List<Serie> favoritos = dados.getFavoritos();
                                if (!favoritos.contains(serie)) {
                                    favoritos.add(serie);
                                    System.out.println("Série adicionada à lista de favoritos.");
                                } else {
                                    System.out.println("Série já está na lista de favoritos.");
                                }
                            }
                            case "3" -> {
                                List<Serie> desejo = dados.getDesejo();
                                if (!desejo.contains(serie)) {
                                    desejo.add(serie);
                                    System.out.println("Série adicionada à lista de desejo assistir.");
                                } else {
                                    System.out.println("Série já está na lista de desejo assistir.");
                                }
                            }
                            default -> System.out.println("Opção inválida: " + opcao);
                        }
                    }
                } else {
                    System.out.println("Nenhuma ação realizada.");
                }
                return nome;
            } else {
                System.out.println("Erro na requisição: Código HTTP " + conn.getResponseCode());
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter número: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao procurar série: " + e.getMessage());
        }
        return null;
    }
}
