package com.apiseries;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AppSeries {

    public static int lerInt(Scanner scanner) {
        while (true) {
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número válido.");
                scanner.nextLine();
            }
        }
    }

    public static Usuario carregarUsuario(String nomeUsuario) throws IOException {
        String diretorio = "Alunos/Eduardo Andrade Rech/Prova_2tri/demo/Usuarios";
        Gson gson = new Gson();
        String nomeArquivo = nomeUsuario + ".json";
        String json = Files.readString(Path.of(diretorio, nomeArquivo));
        return gson.fromJson(json, Usuario.class);
    }

    public static void salvarUsuario(Usuario usuario) throws IOException {
        String diretorio = "Alunos/Eduardo Andrade Rech/Prova_2tri/demo/Usuarios";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(usuario);
        String nomeArquivo = usuario.getNome() + ".json";
        Files.writeString(Path.of(diretorio, nomeArquivo), json);
    }

    public static List<Serie> buscarSeries(String termoBusca) throws IOException, InterruptedException {
        int id;
        String nome;
        String idioma = "Desconhecido";
        List<String> generos;
        Double nota;
        String estado;
        String data_estreia, data_termino;
        String emissora;
        List<Serie> listaSeries = new ArrayList<>();
        try {
            String termoBuscaEncoded = URLEncoder.encode(termoBusca, StandardCharsets.UTF_8);
            String endereco = "https://api.tvmaze.com/search/shows?q=" + termoBuscaEncoded;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            JsonArray resultados = JsonParser.parseString(json).getAsJsonArray();
            for (JsonElement elemento : resultados) {
                JsonObject objetoShow = elemento.getAsJsonObject().getAsJsonObject("show");
                id = objetoShow.get("id").getAsInt();
                nome = objetoShow.get("name").getAsString();
                if (!objetoShow.get("language").isJsonNull()) {
                    idioma = objetoShow.get("language").getAsString();
                }
                generos = new ArrayList<>();
                JsonArray generosJson = objetoShow.getAsJsonArray("genres");
                for (JsonElement genero : generosJson) {
                    generos.add(genero.getAsString());
                }
                JsonObject rating = objetoShow.getAsJsonObject("rating");
                if (!rating.get("average").isJsonNull() && rating.get("average") != null)
                    nota = rating.get("average").getAsDouble();
                else
                    nota = null;
                estado = objetoShow.get("status").getAsString();
                if (!objetoShow.get("premiered").isJsonNull()) {
                    data_estreia = objetoShow.get("premiered").getAsString();
                } else
                    data_estreia = null;
                if (!objetoShow.get("ended").isJsonNull()) {
                    data_termino = objetoShow.get("ended").getAsString();
                } else
                    data_termino = null;
                if (!objetoShow.get("network").isJsonNull()) {
                    emissora = objetoShow.get("network").getAsJsonObject().get("name").getAsString();
                } else
                    emissora = null;

                Serie serie = new Serie(id, nome, idioma, generos, nota, estado, data_estreia, data_termino, emissora);
                listaSeries.add(serie);
            }
        } catch (UnknownHostException e) {
            System.out.println("Erro de conexão: Parece que você está sem internet!");
        } catch (ConnectException e) {
            System.out.println("Não foi possível conectar à API. Verifique sua conexão com a internet.");
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro na requisição: " + e.getMessage());
        }
        return listaSeries;

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite seu nome de usuário: ");
        String nomeUsuario = scanner.nextLine();

        Usuario usuario;
        try {
            usuario = carregarUsuario(nomeUsuario);
            System.out.println("Usuário carregado com sucesso!");
        } catch (IOException e) {
            System.out.println("Novo usuário criado");
            usuario = new Usuario(nomeUsuario);
        }

        System.out.println("Bem-vindo, " + usuario.getNome());

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("0 - Sair");
            System.out.println("1 - Buscar series");
            System.out.println("2 - Ver series favoritas");
            System.out.println("3 - Ver series assistidas");
            System.out.println("4 - Ver series desejadas");
            System.out.println("Escolha uma opção: ");
            int opcao = lerInt(scanner);

            switch (opcao) {
                case 0 -> {
                    try {
                        salvarUsuario(usuario);
                    } catch (IOException ex) {
                    }
                    scanner.close();
                    return;
                }
                case 1 -> {
                    System.out.print("Digite o termo de busca: ");
                    String termoBusca = scanner.nextLine();
                    try {
                        List<Serie> series = buscarSeries(termoBusca);
                        System.out.println("Escolha uma série:");
                        System.out.println("0 - Voltar");
                        for (int i = 0; i < series.size(); i++) {
                            Serie s = series.get(i);
                            System.out.println((i + 1) + " - " + s.getNome());
                        }
                        System.out.print("Escolha uma série: ");
                        int opcaoSerie = lerInt(scanner);
                        if (opcaoSerie > 0 && opcaoSerie <= series.size()) {
                            Serie serieEscolhida = null; //warning preventivo para evitar erro de alocação de memória
                            serieEscolhida = series.get(opcaoSerie - 1);
                            serieEscolhida.printarSerie();
                            System.out.println("Escolha uma ação:");
                            System.out.println("0 - Voltar");
                            System.out.println("1 - Adicionar aos favoritos");
                            System.out.println("2 - Adicionar aos assistidos");
                            System.out.println("3 - Adicionar aos desejados");
                            System.out.print("Escolha uma ação: ");
                            int opcaoAcao = lerInt(scanner);
                            switch (opcaoAcao) {
                                case 0 -> {
                                    break;
                                }
                                case 1 -> usuario.adicionarFavorita(serieEscolhida);
                                case 2 -> usuario.adicionarAssistida(serieEscolhida);
                                case 3 -> usuario.adicionarDesejada(serieEscolhida);
                                default -> System.out.println("Opção inválida");
                            }
                        } else {
                            System.out.println("Opção inválida");
                        }
                    } catch (IOException | InterruptedException e) {
                        System.out.println("Erro ao buscar series: " + e.getMessage());
                    }
                }
                case 2 -> {
                    List<Serie> favoritas = usuario.getFavoritas();
                    if (favoritas.isEmpty()) {
                        System.out.println("Nenhuma série favorita encontrada.");
                        break;
                    }

                    System.out.println("Escolha como deseja ordenar a lista:");
                    System.out.println("0 - Voltar");
                    System.out.println("1 - Ordem alfabética (nome)");
                    System.out.println("2 - Nota (maior para menor)");
                    System.out.println("3 - Por estado (status)");
                    System.out.println("4 - Data de estreia (maior para menor)");
                    System.out.println("Escolha: ");

                    switch (lerInt(scanner)) {
                        case 0 -> {
                            break;
                        }
                        case 1 -> favoritas.sort(Comparator.comparing(Serie::getNome));
                        case 2 -> favoritas.sort(
                                Comparator.comparing(Serie::getNota, Comparator.nullsFirst(Comparator.naturalOrder()))
                                        .reversed());
                        case 3 -> favoritas.sort(Comparator.comparing(Serie::getEstado));
                        case 4 -> favoritas.sort(Comparator.comparing(Serie::getData_estreia));
                        default -> System.out.println("Opção inválida");
                    }
                    System.out.println("--- Series favoritadas ---");
                    System.out.println("0 - Voltar");
                    for (int i = 0; i < favoritas.size(); i++) {
                        System.out.println(i + 1 + " - " + favoritas.get(i).getNome());
                    }
                    System.out.println("Escolha uma série:");
                    int opcaoSerie = lerInt(scanner);
                    if (opcaoSerie > 0 && opcaoSerie <= favoritas.size()) {
                        Serie serieEscolhida = favoritas.get(opcaoSerie - 1);
                        serieEscolhida.printarSerie();
                        System.out.println("Escolha uma ação:");
                        System.out.println("0 - Voltar");
                        System.out.println("1 - Remover dos favoritos");

                        System.out.println("Escolha uma ação: ");
                        int opcaoAcao = lerInt(scanner);
                        switch (opcaoAcao) {
                            case 0 -> {
                                break;
                            }
                            case 1 -> usuario.removerFavorita(serieEscolhida);
                            default -> System.out.println("Opção inválida");
                        }
                    } else if (opcaoSerie == 0) {
                        break;
                    } else {
                        System.out.println("Opção inválida");
                    }
                }
                case 3 -> {
                    List<Serie> assistidas = usuario.getAssistidas();
                    if (assistidas.isEmpty()) {
                        System.out.println("Nenhuma série assistida");
                        continue;
                    }

                    System.out.println("Escolha como deseja ordenar a lista:");
                    System.out.println("0 - Voltar");
                    System.out.println("1 - Ordem alfabética (nome)");
                    System.out.println("2 - Nota (maior para menor)");
                    System.out.println("3 - Por estado (status)");
                    System.out.println("4 - Data de estreia (maior para menor)");
                    System.out.println("Escolha: ");

                    switch (lerInt(scanner)) {
                        case 0 -> {
                            break;
                        }
                        case 1 -> assistidas.sort(Comparator.comparing(Serie::getNome));
                        case 2 -> assistidas.sort(
                                Comparator.comparing(Serie::getNota, Comparator.nullsFirst(Comparator.naturalOrder()))
                                        .reversed());
                        case 3 -> assistidas.sort(Comparator.comparing(Serie::getEstado));
                        case 4 -> assistidas.sort(Comparator.comparing(Serie::getData_estreia));
                        default -> System.out.println("Opção inválida");
                    }

                    System.out.println("--- Series assistidas ---");
                    System.out.println("0 - Voltar");
                    for (int i = 0; i < assistidas.size(); i++) {
                        System.out.println(i + 1 + " - " + assistidas.get(i).getNome());
                    }

                    System.out.println("Escolha uma série:");
                    int opcaoSerie = lerInt(scanner);
                    if (opcaoSerie > 0 && opcaoSerie <= assistidas.size()) {
                        Serie serieEscolhida = assistidas.get(opcaoSerie - 1);
                        serieEscolhida.printarSerie();
                        System.out.println("Escolha uma ação:");
                        System.out.println("0 - Voltar");
                        System.out.println("1 - Remover das assistidas");

                        System.out.println("Escolha uma ação: ");
                        int opcaoAcao = lerInt(scanner);

                        switch (opcaoAcao) {
                            case 0 -> {
                                break;
                            }
                            case 1 -> usuario.removerAssistida(serieEscolhida);
                            case 2 -> serieEscolhida.printarSerie();
                            default -> System.out.println("Opção inválida");
                        }
                    } else {
                        System.out.println("Opção inválida");
                    }
                }
                case 4 -> {
                    List<Serie> desejadas = usuario.getDesejadas();
                    if (desejadas.isEmpty()) {
                        System.out.println("Nenhuma série desejada encontrada");
                        continue;
                    }

                    System.out.println("Escolha como deseja ordenar a lista:");
                    System.out.println("0 - Voltar");
                    System.out.println("1 - Ordem alfabética (nome)");
                    System.out.println("2 - Nota (maior para menor)");
                    System.out.println("3 - Por estado (status)");
                    System.out.println("4 - Data de estreia (maior para menor)");
                    System.out.println("Escolha: ");

                    switch (lerInt(scanner)) {
                        case 0 -> {
                            break;
                        }
                        case 1 -> desejadas.sort(Comparator.comparing(Serie::getNome));
                        case 2 -> desejadas.sort(
                                Comparator.comparing(Serie::getNota, Comparator.nullsFirst(Comparator.naturalOrder()))
                                        .reversed());
                        case 3 -> desejadas.sort(Comparator.comparing(Serie::getEstado));
                        case 4 -> desejadas.sort(Comparator.comparing(Serie::getData_estreia));
                        default -> System.out.println("Opção inválida");
                    }

                    System.out.println("--- Series desejadas ---");
                    System.out.println("0 - Voltar");
                    for (int i = 0; i < desejadas.size(); i++) {
                        System.out.println(i + 1 + " - " + desejadas.get(i).getNome());
                    }

                    System.out.println("Escolha uma série:");
                    int opcaoSerie = lerInt(scanner);
                    if (opcaoSerie > 0 && opcaoSerie <= desejadas.size()) {
                        Serie serieEscolhida = desejadas.get(opcaoSerie - 1);
                        serieEscolhida.printarSerie();
                        System.out.println("Escolha uma ação:");
                        System.out.println("0 - Voltar");
                        System.out.println("1 - Remover das desejadas");

                        System.out.println("Escolha uma ação: ");
                        int opcaoAcao = lerInt(scanner);

                        switch (opcaoAcao) {
                            case 0 -> {
                                break;
                            }
                            case 1 -> usuario.removerDesejada(serieEscolhida);
                            case 2 -> serieEscolhida.printarSerie();
                            default -> System.out.println("Opção inválida");
                        }
                    } else {
                        System.out.println("Opção inválida");
                    }
                }
                default -> System.out.println("Opção inválida");
            }
        }
    }
}
