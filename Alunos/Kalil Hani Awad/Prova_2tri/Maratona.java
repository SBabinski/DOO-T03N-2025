package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.model.Network;
import org.example.model.Rating;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Maratona {
    public static Scanner scanner = new Scanner(System.in);
    @JsonProperty("name")
    private String nome;
    @JsonProperty("language")
    private String idioma;
    @JsonProperty("genres")
    private String[] generos;
    @JsonProperty("rating")
    private Rating rating;
    @JsonProperty("status")
    private String estado;
    @JsonProperty("premiered")
    private String dataEstreia;
    @JsonProperty("ended")
    private String dataTerminio;
    @JsonProperty("network")
    private Network network;
    private static ArrayList<Maratona> series = new ArrayList<>();
    private static ArrayList<Maratona> favorito = new ArrayList<>();
    private static ArrayList<Maratona> assistida = new ArrayList<>();
    private static ArrayList<Maratona> assistirei = new ArrayList<>();
    private static ArrayList<Maratona> Nullos = new ArrayList<>();


    public Maratona() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String[] getGeneros() {
        return generos;
    }

    public void setGeneros(String[] generos) {
        this.generos = generos;
    }

    public String getNotaGeral() {
        return rating != null && rating.getAverage() != null ? rating.getAverage() : "null";
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDataEstreia() {
        return dataEstreia;
    }

    public void setDataEstreia(String dataEstreia) {
        this.dataEstreia = dataEstreia;
    }

    public String getDataTerminio() {
        return dataTerminio;
    }

    public void setDataTerminio(String dataTerminio) {
        this.dataTerminio = dataTerminio;
    }

    public String getNomeEmissora() {
        return network != null && network.getName() != null ? network.getName() : "null";
    }

    @Override
    public String toString() {
        return "Maratona{" +
                "nome='" + nome + '\'' +
                ", idioma='" + idioma + '\'' +
                ", generos=" + Arrays.toString(generos) +
                ", notaGeral='" + getNotaGeral() + '\'' +
                ", estado='" + estado + '\'' +
                ", dataEstreia='" + dataEstreia + '\'' +
                ", dataTerminio='" + dataTerminio + '\'' +
                ", nomeEmissora='" + getNomeEmissora() + '\'' +
                '}';
    }


    public static void inicio() {
        try {
            System.out.println("Coloque o nome da série: ");
            String nome = scanner.nextLine().trim();
            String nomeAjustado = URLEncoder.encode(nome, StandardCharsets.UTF_8);

            HttpClient httpClient = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(5))
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://api.tvmaze.com/search/shows?q=" + nomeAjustado))
                    .timeout(Duration.ofSeconds(5))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Erro na chamada da API: Código " + response.statusCode());
                return;
            }

            ObjectMapper objectMapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            JavaType tipoLista = objectMapper.getTypeFactory()
                    .constructCollectionType(ArrayList.class, SearchResult.class);
            List<SearchResult> resultadoBusca = objectMapper.readValue(response.body(), tipoLista);

            if (resultadoBusca.isEmpty()) {
                System.out.println("Nenhuma série encontrada para: " + nome);
                return;
            }

            for (SearchResult item : resultadoBusca) {
                if (item.getShow() != null && item.getShow().getNome() != null &&
                        item.getShow().getNome().equalsIgnoreCase(nome)) {
                    series.add(item.getShow());
                }

            }

            if (series.isEmpty()) {
                System.out.println("Nenhuma série com o nome exato '" + nome + "' encontrada.");

                return;
            }

            System.out.println("Série encontrada:");
            for (Maratona serie : series) {
                if (serie.getNome().equalsIgnoreCase(nome)) {
                    System.out.println(serie);
                }
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao acessar a API: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    public static void Favoritar_ou_Desfavoritar() {
        try {
            if (series.isEmpty()) {
                System.out.println("A lista de séries está vazia. Busque uma série primeiro.");
                return;
            }

            System.out.println("Séries disponíveis:");
            for (Maratona serie : series) {
                System.out.println(serie);
            }

            System.out.println("Qual série você quer favoritar ou desfavoritar? Coloque o nome da série:");
            String nomeSer = scanner.nextLine().trim();

            Maratona serieSelecionada = null;
            for (Maratona serie : series) {
                if (serie.getNome() != null && serie.getNome().equalsIgnoreCase(nomeSer)) {
                    serieSelecionada = serie;
                    break;
                }
            }

            if (serieSelecionada == null) {
                System.out.println("Não possui a série: " + nomeSer);
                return;
            }

            System.out.println("Aperte 1 para favoritar e 2 para desfavoritar");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
                if (!favorito.contains(serieSelecionada)) {
                    favorito.add(serieSelecionada);
                    System.out.println("Série favoritada: " + serieSelecionada.getNome());
                } else {
                    System.out.println("Série já está favoritada: " + serieSelecionada.getNome());
                }
            } else if (escolha == 2) {
                if (favorito.isEmpty()) {
                    System.out.println("A lista de favoritos está vazia.");
                } else if (favorito.contains(serieSelecionada)) {
                    favorito.remove(serieSelecionada);
                    System.out.println("Série desfavoritada: " + serieSelecionada.getNome());
                } else {
                    System.out.println("Série não está na lista de favoritos: " + nomeSer);
                }
            } else {
                System.out.println("Opção inválida");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida: " + e.getMessage());
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    public static void Lista_Assistida() {
        try {
            if (series.isEmpty()) {
                System.out.println("A lista de séries está vazia. Busque uma série primeiro.");
                return;
            }

            System.out.println("Séries disponíveis:");
            for (Maratona serie : series) {
                System.out.println(serie);
            }

            System.out.println("Qual série você quer marcar como assistida ou remover da lista de assistidas? Coloque o nome da série:");
            String nomeSer = scanner.nextLine().trim();

            Maratona serieSelecionada = null;
            for (Maratona serie : series) {
                if (serie.getNome() != null && serie.getNome().equalsIgnoreCase(nomeSer)) {
                    serieSelecionada = serie;
                    break;
                }
            }

            if (serieSelecionada == null) {
                System.out.println("Não possui a série: " + nomeSer);
                return;
            }

            System.out.println("Aperte 1 para marcar como assistida e 2 para remover da lista de assistidas");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
                if (!assistida.contains(serieSelecionada)) {
                    assistida.add(serieSelecionada);
                    System.out.println("Série adicionada à lista de assistidas: " + serieSelecionada.getNome());
                } else {
                    System.out.println("Série já está na lista de assistidas: " + serieSelecionada.getNome());
                }
            } else if (escolha == 2) {
                if (assistida.isEmpty()) {
                    System.out.println("A lista de assistidas está vazia.");
                } else if (assistida.contains(serieSelecionada)) {
                    assistida.remove(serieSelecionada);
                    System.out.println("Série removida da lista de assistidas: " + serieSelecionada.getNome());
                } else {
                    System.out.println("Série não está na lista de assistidas: " + nomeSer);
                }
            } else {
                System.out.println("Opção inválida");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida: " + e.getMessage());
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    public static void Lista_Assistirei() {
        try {
            if (series.isEmpty()) {
                System.out.println("A lista de séries está vazia. Busque uma série primeiro.");
                return;
            }

            System.out.println("Séries disponíveis:");
            for (Maratona serie : series) {
                System.out.println(serie);
            }

            System.out.println("Qual série você quer marcar como assistirei ou remover da lista de assistirei? Coloque o nome da série:");
            String nomeSer = scanner.nextLine().trim();

            Maratona serieSelecionada = null;
            for (Maratona serie : series) {
                if (serie.getNome() != null && serie.getNome().equalsIgnoreCase(nomeSer)) {
                    serieSelecionada = serie;
                    break;
                }
            }

            if (serieSelecionada == null) {
                System.out.println("Não possui a série: " + nomeSer);
                return;
            }

            System.out.println("Aperte 1 para marcar como assistirei e 2 para remover da lista de assistirei");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
                if (!assistirei.contains(serieSelecionada)) {
                    assistirei.add(serieSelecionada);
                    System.out.println("Série adicionada à lista de assistirei: " + serieSelecionada.getNome());
                } else {
                    System.out.println("Série já está na lista de assistirei: " + serieSelecionada.getNome());
                }
            } else if (escolha == 2) {
                if (assistirei.isEmpty()) {
                    System.out.println("A lista de assistirei está vazia.");
                } else if (assistirei.contains(serieSelecionada)) {
                    assistirei.remove(serieSelecionada);
                    System.out.println("Série removida da lista de assistirei: " + serieSelecionada.getNome());
                } else {
                    System.out.println("Série não está na lista de assistirei: " + nomeSer);
                }
            } else {
                System.out.println("Opção inválida");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida: " + e.getMessage());
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }


    public static void Procurando() {
        try {
            boolean Encontrando = true;
            System.out.println("Qual o nome da serie que você quer procurar?");
            String Titulo = scanner.next();

            System.out.println("Em qual das listas gostaria de procurar: 1 - series, 2 - favorito, 3 - assistidos, 4 - assistirei?");
            int listar = scanner.nextInt();

            if (listar == 1) {
                if (series.isEmpty()) {
                    System.out.println("A lista series esta vazia");
                } else {
                    for (Maratona S : series) {
                        if (S.getNome().equals(Titulo)) {
                            System.out.println("Serie encontrada");
                            System.out.println(S);
                            Encontrando = true;
                            break;
                        }
                    }
                    if (!Encontrando) {
                        System.out.println("Serie não encontrada com o nome:" + Titulo);
                    }
                }
            } else if (listar == 2) {
                if (favorito.isEmpty()) {
                    System.out.println("A lista favorito esta vazia");
                } else {
                    for (Maratona S : favorito) {
                        if (S.getNome().equals(Titulo)) {
                            System.out.println("Serie encontrada");
                            System.out.println(S);
                            Encontrando = true;
                            break;
                        }
                    }
                    if (!Encontrando) {
                        System.out.println("Serie não encontrada com o nome:" + Titulo);
                    }
                }
            } else if (listar == 3) {
                if (assistida.isEmpty()) {
                    System.out.println("A lista assistida esta vazia");
                } else {
                    for (Maratona S : assistida) {
                        if (S.getNome().equals(Titulo)) {
                            System.out.println("Serie encontrada");
                            System.out.println(S);
                            Encontrando = true;
                            break;
                        }
                    }
                    if (!Encontrando) {
                        System.out.println("Serie não encontrada com o nome:" + Titulo);
                    }
                }
            } else if (listar == 4) {
                if (assistirei.isEmpty()) {
                    System.out.println("A lista assistirei esta vazia");
                } else {
                    for (Maratona S : assistirei) {
                        if (S.getNome().equals(Titulo)) {
                            System.out.println("Serie encontrada");
                            System.out.println(S);
                            Encontrando = true;
                            break;
                        }
                    }
                    if (!Encontrando) {
                        System.out.println("Serie não encontrada com o nome:" + Titulo);
                    }
                }
            } else {
                System.out.println("Opção invalida");
            }
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida: " + e.getMessage());
            scanner.nextLine();
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void Ord_ALF() {
        try {
            if (series.isEmpty()) {
                System.out.println("Lista series esta vazia");
            } else {
                System.out.println("Lista series ordenada por ordem Alafabetica");
                Nullos.clear();
                for (Maratona t : series) {
                    String nome = t.getNome();
                    if (nome == null || nome.equalsIgnoreCase("null")) {
                        Nullos.add(t);
                    }
                }
                series.removeAll(Nullos);
                if (series.size() > 1) {
                    series.sort(Comparator.comparing(Maratona::getNome, String.CASE_INSENSITIVE_ORDER));
                }
                series.addAll(Nullos);
                series.forEach(System.out::println);
            }

            if (assistida.isEmpty()) {
                System.out.println("Lista assistida esta vazia");
            } else {
                System.out.println("Lista assistida ordenada por ordem alfabetica");
                Nullos.clear();
                for (Maratona t : assistida) {
                    String nome = t.getNome();
                    if (nome == null || nome.equalsIgnoreCase("null")) {
                        Nullos.add(t);
                    }
                }
                assistida.removeAll(Nullos);
                if (assistida.size() > 1) {
                    assistida.sort(Comparator.comparing(Maratona::getNome, String.CASE_INSENSITIVE_ORDER));
                }
                assistida.addAll(Nullos);
                assistida.forEach(System.out::println);
            }

            if (assistirei.isEmpty()) {
                System.out.println("Lista assistirei esta vazia");
            } else {
                System.out.println("Lista assistirei ordenada por ordem alfabetica");
                Nullos.clear();
                for (Maratona t : assistirei) {
                    String nome = t.getNome();
                    if (nome == null || nome.equalsIgnoreCase("null")) {
                        Nullos.add(t);
                    }
                }
                assistirei.removeAll(Nullos);
                if (assistirei.size() > 1) {
                    assistirei.sort(Comparator.comparing(Maratona::getNome, String.CASE_INSENSITIVE_ORDER));
                }
                assistirei.addAll(Nullos);
                assistirei.forEach(System.out::println);
            }

            if (favorito.isEmpty()) {
                System.out.println("lista favorito esta vazio");
            } else {
                System.out.println("lista favorito ordenada por ordem alfabetica");
                Nullos.clear();
                for (Maratona t : favorito) {
                    String nome = t.getNome();
                    if (nome == null || nome.equalsIgnoreCase("null")) {
                        Nullos.add(t);
                    }
                }
                favorito.removeAll(Nullos);
                if (favorito.size() > 1) {
                    favorito.sort(Comparator.comparing(Maratona::getNome, String.CASE_INSENSITIVE_ORDER));
                }
                favorito.addAll(Nullos);
                favorito.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.err.println("Erro ao ordenar alfabeticamente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void Estado_ALL() {
        try {
            if (series.isEmpty()) {
                System.out.println("A lista esta vazia");
            } else {
                System.out.println("A lista series foi ordenada por Estado");
                Nullos.clear();
                for (Maratona t : series) {
                    String estado = t.getEstado();
                    if (estado == null || estado.equalsIgnoreCase("null")) {
                        Nullos.add(t);
                    }
                }
                series.removeAll(Nullos);
                if (series.size() > 1) {
                    series.sort(Comparator.comparing(Maratona::getEstado, String.CASE_INSENSITIVE_ORDER));
                }
                series.addAll(Nullos);
                series.forEach(System.out::println);
            }

            if (assistida.isEmpty()) {
                System.out.println("Lista assistida esta vazia");
            } else {
                System.out.println("A lista assistida foi ordenada por Estado");
                Nullos.clear();
                for (Maratona t : assistida) {
                    String estado = t.getEstado();
                    if (estado == null || estado.equalsIgnoreCase("null")) {
                        Nullos.add(t);
                    }
                }
                assistida.removeAll(Nullos);
                if (assistida.size() > 1) {
                    assistida.sort(Comparator.comparing(Maratona::getEstado, String.CASE_INSENSITIVE_ORDER));
                }
                assistida.addAll(Nullos);
                assistida.forEach(System.out::println);
            }

            if (assistirei.isEmpty()) {
                System.out.println("Lista assistirei esta vazia");
            } else {
                System.out.println("A lista assistirei foi ordenada por Estado");
                Nullos.clear();
                for (Maratona t : assistirei) {
                    String estado = t.getEstado();
                    if (estado == null || estado.equalsIgnoreCase("null")) {
                        Nullos.add(t);
                    }
                }
                assistirei.removeAll(Nullos);
                if (assistirei.size() > 1) {
                    assistirei.sort(Comparator.comparing(Maratona::getEstado, String.CASE_INSENSITIVE_ORDER));
                }
                assistirei.addAll(Nullos);
                assistirei.forEach(System.out::println);
            }

            if (favorito.isEmpty()) {
                System.out.println("Lista favorito esta vazia");
            } else {
                System.out.println("A lista favorito foi ordenada por Estado");
                Nullos.clear();
                for (Maratona t : favorito) {
                    String estado = t.getEstado();
                    if (estado == null || estado.equalsIgnoreCase("null")) {
                        Nullos.add(t);
                    }
                }
                favorito.removeAll(Nullos);
                if (favorito.size() > 1) {
                    favorito.sort(Comparator.comparing(Maratona::getEstado, String.CASE_INSENSITIVE_ORDER));
                }
                favorito.addAll(Nullos);
                favorito.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.err.println("Erro ao ordenar por estado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void Nota() {
        try {
            if (series.isEmpty()) {
                System.out.println("Lista series esta vazia");
            } else if (series.size() == 1) {
                System.out.println("Só contem 1 item na lista");
            } else {
                System.out.println("Lista series ordenado por ordem de Nota");
                Nullos.clear();
                for (Maratona t : series) {
                    String nota = t.getNotaGeral();
                    if (nota == null || nota.equals("null")) {
                        Nullos.add(t);
                    }
                }
                series.removeAll(Nullos);
                series.sort(Comparator.comparingDouble(Maratona::getNumero_Double));
                series.addAll(Nullos);
                series.forEach(System.out::println);
            }

            if (assistirei.isEmpty()) {
                System.out.println("Lista assistirei esta vazia");
            } else if (assistirei.size() == 1) {
                System.out.println("Só contem 1 item na lista");
            } else {
                System.out.println("Lista assistirei ordenado por ordem de Nota");
                Nullos.clear();
                for (Maratona t : assistirei) {
                    String nota = t.getNotaGeral();
                    if (nota == null || nota.equals("null")) {
                        Nullos.add(t);
                    }
                }
                assistirei.removeAll(Nullos);
                assistirei.sort(Comparator.comparingDouble(Maratona::getNumero_Double));
                assistirei.addAll(Nullos);
                assistirei.forEach(System.out::println);
            }

            if (assistida.isEmpty()) {
                System.out.println("Lista assistida esta vazia");
            } else if (assistida.size() == 1) {
                System.out.println("Só contem 1 item na lista");
            } else {
                System.out.println("Lista assistida ordenado por ordem de Nota");
                Nullos.clear();
                for (Maratona t : assistida) {
                    String nota = t.getNotaGeral();
                    if (nota == null || nota.equals("null")) {
                        Nullos.add(t);
                    }
                }
                assistida.removeAll(Nullos);
                assistida.sort(Comparator.comparingDouble(Maratona::getNumero_Double));
                assistida.addAll(Nullos);
                assistida.forEach(System.out::println);
            }

            if (favorito.isEmpty()) {
                System.out.println("Lista favorito esta vazia");
            } else if (favorito.size() == 1) {
                System.out.println("Só contem 1 item na lista");
            } else {
                System.out.println("Lista favorito ordenado por ordem de Nota");
                Nullos.clear();
                for (Maratona t : favorito) {
                    String nota = t.getNotaGeral();
                    if (nota == null || nota.equals("null")) {
                        Nullos.add(t);
                    }
                }
                favorito.removeAll(Nullos);
                favorito.sort(Comparator.comparingDouble(Maratona::getNumero_Double));
                favorito.addAll(Nullos);
                favorito.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.err.println("Erro ao ordenar por nota: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void Ordenar_por_data() {
        Maratona Datas_arrumadas_Estreia = new Maratona();
        Datas_arrumadas_Estreia.getDatas_series();
        Datas_arrumadas_Estreia.getDatas_assistida();
        Datas_arrumadas_Estreia.getDatas_assistirei();
        Datas_arrumadas_Estreia.getDatas_Favorito();
    }

    public String getDatas_series() {
        try {
            if (series.isEmpty()) {
                System.out.println("Lista series esta vazia");
            } else {
                System.out.println("A lista series ordenada por Data de Estreia");
                Nullos.clear();
                for (Maratona t : series) {
                    String data = t.getDataEstreia();
                    if (data == null || data.equalsIgnoreCase("null")) {
                        Nullos.add(t);
                    }
                }
                series.removeAll(Nullos);
                if (series.size() > 1) {
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    series.sort(Comparator.comparing(reg -> {
                        try {
                            return LocalDate.parse(reg.getDataEstreia(), fmt);
                        } catch (DateTimeParseException e) {
                            throw new IllegalArgumentException(
                                    "Data inválida em registro: " + reg.getDataEstreia(), e);
                        }
                    }));
                }
                series.addAll(Nullos);
                series.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.err.println("Erro ao ordenar series por data: " + e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    public String getDatas_assistida() {
        try {
            if (assistida.isEmpty()) {
                System.out.println("Lista assistida esta vazia");
            } else {
                System.out.println("A lista assistida ordenada por Data de Estreia");
                Nullos.clear();
                for (Maratona t : assistida) {
                    String data = t.getDataEstreia();
                    if (data == null || data.equalsIgnoreCase("null")) {
                        Nullos.add(t);
                    }
                }
                assistida.removeAll(Nullos);
                if (assistida.size() > 1) {
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    assistida.sort(Comparator.comparing(reg -> {
                        try {
                            return LocalDate.parse(reg.getDataEstreia(), fmt);
                        } catch (DateTimeParseException e) {
                            throw new IllegalArgumentException(
                                    "Data inválida em registro: " + reg.getDataEstreia(), e);
                        }
                    }));
                }
                assistida.addAll(Nullos);
                assistida.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.err.println("Erro ao ordenar assistida por data: " + e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    public String getDatas_assistirei() {
        try {
            if (assistirei.isEmpty()) {
                System.out.println("Lista assistirei esta vazia");
            } else {
                System.out.println("A lista assistirei ordenada por Data de Estreia");
                Nullos.clear();
                for (Maratona t : assistirei) {
                    String data = t.getDataEstreia();
                    if (data == null || data.equalsIgnoreCase("null")) {
                        Nullos.add(t);
                    }
                }
                assistirei.removeAll(Nullos);
                if (assistirei.size() > 1) {
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    assistirei.sort(Comparator.comparing(reg -> {
                        try {
                            return LocalDate.parse(reg.getDataEstreia(), fmt);
                        } catch (DateTimeParseException e) {
                            throw new IllegalArgumentException(
                                    "Data inválida em registro: " + reg.getDataEstreia(), e);
                        }
                    }));
                }
                assistirei.addAll(Nullos);
                assistirei.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.err.println("Erro ao ordenar assistirei por data: " + e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    public String getDatas_Favorito() {
        try {
            if (favorito.isEmpty()) {
                System.out.println("A lista favorita esta vazia");
            } else {
                System.out.println("A lista favorita ordenada por Data de Estreia");
                Nullos.clear();
                for (Maratona t : favorito) {
                    String data = t.getDataEstreia();
                    if (data == null || data.equalsIgnoreCase("null")) {
                        Nullos.add(t);
                    }
                }
                favorito.removeAll(Nullos);
                if (favorito.size() > 1) {
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    favorito.sort(Comparator.comparing(reg -> {
                        try {
                            return LocalDate.parse(reg.getDataEstreia(), fmt);
                        } catch (DateTimeParseException e) {
                            throw new IllegalArgumentException(
                                    "Data inválida em registro: " + reg.getDataEstreia(), e);
                        }
                    }));
                }
                favorito.addAll(Nullos);
                favorito.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.err.println("Erro ao ordenar favorito por data: " + e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    public static void Exibir_listas() {
        if (series.isEmpty()) {
            System.out.println("A lista /series/ esta vazia");
        } else {
            System.out.println("--------------Series------------");
            for (Maratona p : series) {
                System.out.println(p);
            }
        }
        if (favorito.isEmpty()) {
            System.out.println("A lista /favorito/ esta vazia");
        } else {
            System.out.println("--------------Favorito------------");
            for (Maratona f : favorito) {
                System.out.println(f);
            }
        }

        if (assistirei.isEmpty()) {
            System.out.println("A lista /assistirei/ esta vazia");
        } else {
            System.out.println("--------------Assistirei------------");
            for (Maratona r : assistirei) {
                System.out.println(r);
            }
        }

        if (assistida.isEmpty()) {
            System.out.println("A lista /assistida/ esta vazia");
        } else {
            System.out.println("--------------Assistida------------");
            for (Maratona d : assistida) {
                System.out.println(d);
            }
        }
    }

    public Double getNumero_Double() {
        try {
            return Double.parseDouble(getNotaGeral());
        } catch (NumberFormatException e) {
            System.err.println("Erro na formatação: " + e.getMessage());
            return 0.0;
        }
    }


    public static void pessoinha() {
        Pessoa humano = new Pessoa("Wanderlei", 55, 123456789);
        System.out.println("Seja bem vindo:" + humano);
    }

    public static void salvarSeriesEmArquivo() {

        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(new File("series.json"), series);
            System.out.println("Arquivo series.json gravado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao gravar arquivo JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void carregarSeriesDoArquivo() {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        File arquivo = new File("series.json");
        if (!arquivo.exists()) {
            System.out.println("Arquivo series.json não encontrado. Iniciando com lista vazia.");
            return;
        }
        try {
            List<Maratona> lista = mapper.readValue(
                    arquivo,
                    new TypeReference<List<Maratona>>() {
                    }
            );
            series.clear();
            series.addAll(lista);
            System.out.println("Carregadas " + series.size() + " séries de series.json");
        } catch (IOException e) {
            System.err.println("Erro ao carregar series.json: " + e.getMessage());
            e.printStackTrace();
        }
    }


}