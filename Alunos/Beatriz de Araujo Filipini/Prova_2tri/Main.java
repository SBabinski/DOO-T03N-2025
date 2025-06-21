import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Usuario usuario;
    private static HttpClienteMave apiService = new HttpClienteMave();
    private static JsonDataManager dataManager = new JsonDataManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        carregarDadosUsuario();
        if (usuario == null) {
            System.out.print("Bem-vindo! Qual o seu apelido? ");
            String apelido = scanner.nextLine();
            usuario = new Usuario(apelido);
            System.out.println("Olá, " + usuario.getApelido() + "!");
        } else {
            System.out.println("Bem-vindo de volta, " + usuario.getApelido() + "!");
        }

        menuPrincipal();

        salvarDadosUsuario();
        System.out.println("Obrigado por usar o sistema! Dados salvos.");
        scanner.close();
    }

    private static void carregarDadosUsuario() {
        try {
            usuario = dataManager.carregarDados();
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados do usuário: " + e.getMessage());
        }
    }

    private static void salvarDadosUsuario() {
        try {
            dataManager.salvarDados(usuario);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados do usuário: " + e.getMessage());
        }
    }

    private static void menuPrincipal() {
        int opcao;
        do {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Procurar séries");
            System.out.println("2. Ver minhas listas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    procurarSeries();
                    break;
                case 2:
                    menuListas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void procurarSeries() {
        System.out.print("Digite o nome da série: ");
        String nomeSerie = scanner.nextLine();
        try {
            
            List<Serie> resultados = apiService.buscarSeriesPorNome(nomeSerie);
            if (resultados.isEmpty()) {
                System.out.println("Nenhuma série encontrada com esse nome.");
            } else {
                System.out.println("\n--- Resultados da Busca ---");
                for (int i = 0; i < resultados.size(); i++) {
                    Serie serie = resultados.get(i);
                    System.out.println((i + 1) + ". " + serie.getName() + " (Nota: " + serie.getRatingAverage() + ")");
                }
                System.out.println("Digite o número da série para mais detalhes ou 0 para voltar:");
                int escolha = lerInteiro();
                if (escolha > 0 && escolha <= resultados.size()) {
                    exibirDetalhesSerie(resultados.get(escolha - 1));
                    menuAcoesSerie(resultados.get(escolha - 1));
                } else if (escolha != 0) {
                     System.out.println("Número inválido.");
                }
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao buscar séries: " + e.getMessage());
            System.err.println("Verifique sua conexão com a internet ou tente novamente mais tarde.");
            
        }
    }

    private static void exibirDetalhesSerie(Serie serie) {
        System.out.println("\n--- Detalhes da Série: " + serie.getName() + " ---");
        System.out.println("Idioma: " + (serie.getLanguage() != null && !serie.getLanguage().isEmpty() ? serie.getLanguage() : "N/A"));
        System.out.println("Gêneros: " + (serie.getGenres() != null && !serie.getGenres().isEmpty() ? String.join(", ", serie.getGenres()) : "N/A"));
        System.out.println("Nota Geral: " + (serie.getRatingAverage() > 0 ? serie.getRatingAverage() : "N/A"));
        System.out.println("Estado: " + (serie.getStatus() != null && !serie.getStatus().isEmpty() ? serie.getStatus() : "N/A"));
        System.out.println("Data de Estreia: " + (serie.getPremiered() != null && !serie.getPremiered().isEmpty() ? serie.getPremiered() : "N/A"));
        System.out.println("Data de Término: " + (serie.getEnded() != null && !serie.getEnded().isEmpty() ? serie.getEnded() : "Ainda em exibição"));
        System.out.println("Emissora: " + (serie.getNetwork() != null && serie.getNetwork().getName() != null && !serie.getNetwork().getName().isEmpty() ? serie.getNetwork().getName() : "N/A"));
    }

    private static void menuAcoesSerie(Serie serie) {
        int opcao;
        do {
            System.out.println("\n--- Ações para " + serie.getName() + " ---");
            System.out.println("1. Adicionar à Favoritos");
            System.out.println("2. Adicionar a Já Assistidas");
            System.out.println("3. Adicionar a Deseja Assistir");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    usuario.adicionarFavorita(serie);
                    System.out.println(serie.getName() + " adicionada aos favoritos!");
                    break;
                case 2:
                    usuario.adicionarAssistida(serie);
                    System.out.println(serie.getName() + " adicionada às séries assistidas!");
                    break;
                case 3:
                    usuario.adicionarDesejaAssistir(serie);
                    System.out.println(serie.getName() + " adicionada às séries que deseja assistir!");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void menuListas() {
        int opcao;
        do {
            System.out.println("\n--- Minhas Listas ---");
            System.out.println("1. Favoritos");
            System.out.println("2. Já Assistidas");
            System.out.println("3. Deseja Assistir");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma lista para visualizar: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    gerenciarLista(usuario.getFavoritas(), "Favoritos");
                    break;
                case 2:
                    gerenciarLista(usuario.getAssistidas(), "Já Assistidas");
                    break;
                case 3:
                    gerenciarLista(usuario.getDesejaAssistir(), "Deseja Assistir");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void gerenciarLista(List<Serie> lista, String nomeLista) {
        if (lista.isEmpty()) {
            System.out.println("Sua lista de " + nomeLista + " está vazia.");
            return;
        }

        int opcao;
        do {
            System.out.println("\n--- " + nomeLista + " ---");
            exibirLista(lista);

            System.out.println("\n1. Ordenar lista");
            System.out.println("2. Remover série");
            System.out.println("3. Ver detalhes de série");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    ordenarLista(lista);
                    break;
                case 2:
                    removerSerieDaLista(lista, nomeLista);
                    break;
                case 3:
                    verDetalhesDaLista(lista);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void exibirLista(List<Serie> lista) {
        if (lista.isEmpty()) {
            System.out.println("(Lista vazia)");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            Serie serie = lista.get(i);
            System.out.println((i + 1) + ". " + serie.getName() + " (Nota: " + serie.getRatingAverage() + ", Status: " + serie.getStatus() + ")");
        }
    }

    private static void ordenarLista(List<Serie> lista) {
        System.out.println("\n--- Ordenar por ---");
        System.out.println("1. Ordem alfabética do nome");
        System.out.println("2. Nota geral");
        System.out.println("3. Estado da série");
        System.out.println("4. Data de estreia");
        System.out.print("Escolha uma opção: ");
        int opcao = lerInteiro();

        switch (opcao) {
            case 1:
                lista.sort(Comparator.comparing(Serie::getName));
                break;
            case 2:
                lista.sort(Comparator.comparingDouble(Serie::getRatingAverage).reversed());
                break;
            case 3:
                lista.sort(Comparator.comparing(Serie::getStatus));
                break;
            case 4:
                lista.sort(Comparator.comparing(Serie::getPremiered));
                break;
            default:
                System.out.println("Opção inválida.");
        }
        System.out.println("Lista ordenada:");
        exibirLista(lista);
    }

    private static void removerSerieDaLista(List<Serie> lista, String nomeLista) {
        if (lista.isEmpty()) {
            System.out.println("A lista de " + nomeLista + " está vazia.");
            return;
        }
        System.out.println("Qual série deseja remover da lista de " + nomeLista + "?");
        exibirLista(lista);
        System.out.print("Digite o número: ");
        int numero = lerInteiro();
        if (numero > 0 && numero <= lista.size()) {
            Serie serieRemovida = lista.remove(numero - 1);
            System.out.println(serieRemovida.getName() + " removida da lista de " + nomeLista + ".");
        } else {
            System.out.println("Número inválido.");
        }
    }

    private static void verDetalhesDaLista(List<Serie> lista) {
        if (lista.isEmpty()) {
            System.out.println("A lista está vazia.");
            return;
        }
        System.out.println("Qual série deseja ver os detalhes?");
        exibirLista(lista);
        System.out.print("Digite o número: ");
        int numero = lerInteiro();
        if (numero > 0 && numero <= lista.size()) {
            exibirDetalhesSerie(lista.get(numero - 1));
        } else {
            System.out.println("Número inválido.");
        }
    }

    private static int lerInteiro() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Digite um número.");
            scanner.next();
            System.out.print("Escolha uma opção: ");
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }
}