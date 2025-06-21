package org.aplicacao;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public final Scanner scanner;
    public final Apiservico apiServico;

    public final JsonFileModel jsonFileModel;

    {
        jsonFileModel = new JsonFileModel();
    }

    public Main() {
        this.scanner = new Scanner(System.in);
        this.apiServico = new Apiservico();
        carregarJsonFileModel();
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.iniciar();
    }


    public void iniciar() {
        if (jsonFileModel.getUserName() == null) {
            System.out.println("Olá, bem vindo a consulta de filme dos guri!");
            System.out.print("Digite seu nome: ");
            jsonFileModel.setUserName(scanner.nextLine());
            jsonFileModel.setFavoritos(new ArrayList<>());
            jsonFileModel.setAssistidas(new ArrayList<>());
            jsonFileModel.setParaAssistir(new ArrayList<>());
            jsonFileModel.saveToFile();
            System.out.println("Seja bem-vindo, " + jsonFileModel.getUserName() + "!");
        } else {
            System.out.println("Olá " + jsonFileModel.getUserName() + ", bem vindo de volta a consulta de filme dos guri!");
        }
        exibirMenu();
    }

    public void exibirMenu() {
        while (true) {
            System.out.println("\n=== Menu Principal ===" + "\n1. Buscar série" + "\n2. Mostrar favoritos" +
                    "\n3. Mostrar séries assistidas" + "\n4. Mostrar séries para assistir" +
                    "\n5. Remover série" + "\n6. Sair" + "\nEscolha uma opção: ");
            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        buscarSerie();
                        break;
                    case 2:
                        mostrarLista(jsonFileModel.getFavoritos(), "Favoritos");
                        break;
                    case 3:
                        mostrarLista(jsonFileModel.getAssistidas(), "Assistidas");
                        break;
                    case 4:
                        mostrarLista(jsonFileModel.getParaAssistir(), "Para Assistir");
                        break;
                    case 5:
                        removerSerie();
                        break;
                    case 6: {
                        System.out.println("Até logo, " + jsonFileModel.getUserName() + "!");
                        jsonFileModel.saveToFile();
                        return;
                    }
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido!");
            }
        }
    }

    public void buscarSerie() {
        int id = 0;
        System.out.print("Digite o nome da série: ");
        String nome = scanner.nextLine();
        try {
            Series serie = apiServico.buscarSerie(nome);
            if (serie != null) {
                System.out.println("\nSérie encontrada:");
                System.out.println(serie);
                id = serie.getId();
                System.out.println("\n1. Adicionar aos favoritos" + "\n2. Adicionar às séries assistidas" +
                        "\n3. Adicionar à lista para assistir" + "\n4. Voltar ao menu principal" + "\nEscolha uma opção: ");


                int opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        if (serieExisteEmAlgumaLista(serie) == 0) {
                            jsonFileModel.getFavoritos().add(serie);
                            jsonFileModel.saveToFile();
                            System.out.println("Série adicionada aos favoritos!");
                        } else {
                            System.out.println("Esta serie já na lista!");
                        }
                        break;
                    case 2:
                        if (serieExisteEmAlgumaLista(serie) == 0) {
                            jsonFileModel.getAssistidas().add(serie);
                            jsonFileModel.saveToFile();
                            System.out.println("Série adicionada aos assistidos!");
                        } else {
                            System.out.println("Esta serie já na lista!");
                        }
                        break;
                    case 3:
                        if (serieExisteEmAlgumaLista(serie) == 0) {
                            jsonFileModel.getParaAssistir().add(serie);
                            jsonFileModel.saveToFile();
                            System.out.println("Serie adicionada ao quero assistir!");
                        } else {
                            System.out.println("Esta serie já na lista!");
                        }
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Opcao inválida!");
                }
            } else {
                System.out.println("Série não encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar série: " + e.getMessage());
        }
    }

    public void mostrarLista(List<Series> lista, String titulo) {
        if (lista.isEmpty()) {
            System.out.println("A lista esta vazia.");
            return;
        }

        System.out.println("\nComo deseja ordenar a lista?");
        System.out.println("1. Ordem alfabética do nome \n2. Nota geral \n3. Estado da série \n4. Data de estreia" +
                "\n5. Sem ordenação");

        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            List<Series> listaOrdenada = new ArrayList<>(lista);

            switch (opcao) {
                case 1:
                    listaOrdenada.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
                    break;
                case 2:
                    listaOrdenada.sort((s1, s2) -> Double.compare(s2.getRating(), s1.getRating()));
                    break;
                case 3:
                    listaOrdenada.sort((s1, s2) -> s1.getStatus().compareToIgnoreCase(s2.getStatus()));
                    break;
                case 4:
                    listaOrdenada.sort((s1, s2) -> s1.getPremiered().compareToIgnoreCase(s2.getPremiered()));
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opção inválida! Mostrando lista sem ordenação.");
            }

            System.out.println("\n== " + titulo + " ==");
            for (Series s : listaOrdenada) {
                System.out.println(s + "\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida! Mostrando lista sem ordenação.");
            System.out.println("\n== " + titulo + " ==");
            for (Series s : lista) {
                System.out.println(s + "\n");
            }
        }
    }

    public int serieExisteEmAlgumaLista(Series serie) {
        int repetido = 0;

        for (Series s : jsonFileModel.getFavoritos()) {
            if (s.getId() == serie.getId()) {
                repetido = 1;
                break;
            }
        }
        for (Series s : jsonFileModel.getAssistidas()) {
            if (s.getId() == serie.getId()) {
                repetido = 1;
                break;
            }
        }
        for (Series s : jsonFileModel.getParaAssistir()) {
            if (s.getId() == serie.getId()) {
                repetido = 1;
                break;
            }
        }
        return repetido;
    }

    public void carregarJsonFileModel() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("usuario.json");
            if (file.exists()) {
                JsonFileModel loaded = objectMapper.readValue(file, JsonFileModel.class);
                if (loaded != null) {
                    jsonFileModel.setUserName(loaded.getUserName());
                    jsonFileModel.setFavoritos(loaded.getFavoritos() != null ? loaded.getFavoritos() : new ArrayList<>());
                    jsonFileModel.setAssistidas(loaded.getAssistidas() != null ? loaded.getAssistidas() : new ArrayList<>());
                    jsonFileModel.setParaAssistir(loaded.getParaAssistir() != null ? loaded.getParaAssistir() : new ArrayList<>());
                }
            }
        } catch (IOException e) {
            System.out.println("Não foi possível carregar o arquivo de usuário ou não existe nenhum salvo. ");
        }
    }

    public void removerSerie() {
        System.out.println("\n=== Remover Série ===");
        System.out.println("De qual lista você deseja remover? \n " +
                "\n1. Favoritos" + "\n2. Séries assistidas" +
                "\n3. Para assistir" + "\n4. Voltar ao menu principal" + "\nEscolha uma opção: " + "\n");

        try {
            int opcao = Integer.parseInt(scanner.nextLine());

            if (opcao == 4) return;
            if (opcao < 1 || opcao > 3) {
                System.out.println("Opção inválida!");
                return;
            }

            List<Series> lista;
            String nomeLista;

            switch (opcao) {
                case 1 -> {
                    lista = jsonFileModel.getFavoritos();
                    nomeLista = "Favoritos";
                }
                case 2 -> {
                    lista = jsonFileModel.getAssistidas();
                    nomeLista = "Séries Assistidas";
                }
                default -> {
                    lista = jsonFileModel.getParaAssistir();
                    nomeLista = "Séries Para Assistir";
                }
            }

            if (lista.isEmpty()) {
                System.out.println("A lista " + nomeLista + " está vazia.");
                return;
            }

            System.out.println("\nSéries na lista " + nomeLista + ":");
            for (int i = 0; i < lista.size(); i++) {
                System.out.println((i + 1) + ". " + lista.get(i).getName());
            }

            System.out.print("\nDigite o número da série que deseja remover (ou 0 para cancelar): ");
            int indice = Integer.parseInt(scanner.nextLine()) - 1;

            if (indice == -1) {
                System.out.println("operacão cancelada.");
                return;
            }

            if (indice >= 0 && indice < lista.size()) {
                String nomeSerieRemovida = lista.get(indice).getName();
                lista.remove(indice);
                jsonFileModel.saveToFile();
                System.out.printf("série: " + nomeSerieRemovida + " removida com sucesso da lista " + nomeLista);
            } else {
                System.out.println("Número inválido!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Por favor, digite um número válido!");
        }
    }
}
