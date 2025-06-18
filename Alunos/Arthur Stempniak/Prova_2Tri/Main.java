import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final JsonStorage jsonStorage = new JsonStorage();
    private static final SerieManager serieManager = new SerieManager();
    private static final TvMazeAPI api = new TvMazeAPI();
    private static User user;

    public static void main(String[] args) {
        System.out.println("### Sistema de Acompanhamento de Séries ###");
        carregarUsuario();
        exibirMenuPrincipal();
        System.out.println("==========================================");
    }

    private static void carregarUsuario() {
        user = jsonStorage.carregarUsuario();
        if (user == null || user.getNome() == null || user.getNome().trim().isEmpty()) {
            System.out.print("Bem-vindo(a)! Por favor, digite seu nome de usuário: ");
            String nome = scanner.nextLine().trim();
            user = new User(nome);
            System.out.println("Olá, " + nome + "!");
        } else {
            System.out.println("Bem-vindo(a) de volta, " + user.getNome() + "!");
        }
    }

    private static void exibirMenuPrincipal() {
        while (true) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Buscar Série por Nome");
            System.out.println("2. Visualizar Minhas Listas");
            System.out.println("3. Salvar e Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine().trim();
            switch (opcao) {
                case "1":
                    buscarSerie();
                    break;
                case "2":
                    visualizarListas();
                    break;
                case "3":
                    jsonStorage.salvarUsuario(user);
                    System.out.println("Dados salvos com sucesso. Até logo!");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    break;
            }
        }
    }

    private static void buscarSerie() {
        System.out.print("\nDigite o nome da série para buscar: ");
        String termo = scanner.nextLine().trim();
        if (termo.isEmpty()) {
            System.out.println("O termo de busca não pode ser vazio.");
            return;
        }

        List<Serie> resultados = api.buscarSeriesPorNome(termo);
        if (resultados.isEmpty()) {
            System.out.println("Nenhuma série encontrada com o termo '" + termo + "'.");
            return;
        }

        System.out.println("\n--- Resultados da Busca ---");
        for (int i = 0; i < resultados.size(); i++) {
            System.out.printf("%d. %s%n", (i + 1), resultados.get(i).getNome());
        }

        System.out.print("Selecione o número da série para ver detalhes (ou 0 para voltar): ");
        int escolha = lerInteiro();
        if (escolha > 0 && escolha <= resultados.size()) {
            Serie serieSelecionada = resultados.get(escolha - 1);
            exibirDetalhesESalvar(serieSelecionada);
        }
    }

    private static void exibirDetalhesESalvar(Serie serie) {
        System.out.println("\n--- Detalhes da Série ---");
        System.out.println(serie);

        System.out.println("--- Adicionar à Lista ---");
        System.out.println("1. Favoritas");
        System.out.println("2. Assistidas");
        System.out.println("3. Desejadas");
        System.out.println("0. Voltar ao Menu");
        System.out.print("Escolha uma lista: ");

        int escolhaLista = lerInteiro();
        switch (escolhaLista) {
            case 1:
                adicionarSerieNaLista(user.getSeriesFavoritas(), serie, "Favoritas");
                break;
            case 2:
                adicionarSerieNaLista(user.getSeriesAssistidas(), serie, "Assistidas");
                break;
            case 3:
                adicionarSerieNaLista(user.getSeriesDesejadas(), serie, "Desejadas");
                break;
            default:
                break;
        }
    }
    
    private static void visualizarListas() {
        System.out.println("\n--- MINHAS LISTAS ---");
        System.out.println("1. Favoritas");
        System.out.println("2. Assistidas");
        System.out.println("3. Desejadas");
        System.out.print("Qual lista você deseja visualizar? ");
        
        int escolha = lerInteiro();
        List<Serie> lista;
        String nomeLista;

        switch (escolha) {
            case 1:
                lista = user.getSeriesFavoritas();
                nomeLista = "Favoritas";
                break;
            case 2:
                lista = user.getSeriesAssistidas();
                nomeLista = "Assistidas";
                break;
            case 3:
                lista = user.getSeriesDesejadas();
                nomeLista = "Desejadas";
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        if (lista.isEmpty()) {
            System.out.println("A lista de '" + nomeLista + "' está vazia.");
            return;
        }

        System.out.println("\n--- Ordenar Lista de " + nomeLista.toUpperCase() + " por: ---");
        System.out.println("1. Nome (A-Z)");
        System.out.println("2. Nota (Maior para Menor)");
        System.out.println("3. Estado (Status)");
        System.out.println("4. Data de Estreia");
        System.out.print("Escolha o critério de ordenação: ");

        int criterio = lerInteiro();
        SerieManager.Ordenacao ordenacao;
        switch (criterio) {
            case 2:
                ordenacao = SerieManager.Ordenacao.NOTA;
                break;
            case 3:
                ordenacao = SerieManager.Ordenacao.ESTADO;
                break;
            case 4:
                ordenacao = SerieManager.Ordenacao.DATA_ESTREIA;
                break;
            default:
                ordenacao = SerieManager.Ordenacao.NOME;
                break;
        }

        List<Serie> listaOrdenada = serieManager.ordenarLista(lista, ordenacao);
        
        System.out.println("\n--- Lista de " + nomeLista + " ---");
        for (int i = 0; i < listaOrdenada.size(); i++) {
            System.out.printf("%d. %s%n", (i + 1), listaOrdenada.get(i).getNome());
        }
        
        gerenciarLista(lista, listaOrdenada, nomeLista);
    }
    
    /**
     * NOVO: Gerencia a lista exibida com um menu contextual para mover ou remover séries.
     */
    private static void gerenciarLista(List<Serie> listaOriginal, List<Serie> listaOrdenada, String nomeLista) {
        System.out.println("\n--- Gerenciar Lista: " + nomeLista.toUpperCase() + " ---");

        // Monta o menu de acordo com a lista atual
        switch (nomeLista) {
            case "Favoritas":
                System.out.println("1. Mover para 'Assistidas'");
                System.out.println("2. Remover dos Favoritos");
                break;
            case "Assistidas":
                System.out.println("1. Remover das Assistidas");
                break;
            case "Desejadas":
                System.out.println("1. Mover para 'Assistidas'");
                System.out.println("2. Mover para 'Favoritas'");
                System.out.println("3. Remover das Desejadas");
                break;
        }
        System.out.println("0. Voltar");
        System.out.print("Escolha uma ação: ");
        int acao = lerInteiro();
        if (acao == 0) return;

        System.out.print("Digite o número da série para aplicar a ação (ou 0 para cancelar): ");
        int escolha = lerInteiro();
        if (escolha <= 0 || escolha > listaOrdenada.size()) {
            return;
        }

        Serie serieSelecionada = listaOrdenada.get(escolha - 1);

        // Executa a ação escolhida
        switch (nomeLista) {
            case "Favoritas":
                if (acao == 1) { // Mover para Assistidas
                    adicionarSerieNaLista(user.getSeriesAssistidas(), serieSelecionada, "Assistidas");
                    serieManager.removerSeriePorNome(listaOriginal, serieSelecionada.getNome());
                    System.out.println("'" + serieSelecionada.getNome() + "' movida de Favoritas para Assistidas.");
                } else if (acao == 2) { // Remover
                    serieManager.removerSeriePorNome(listaOriginal, serieSelecionada.getNome());
                    System.out.println("'" + serieSelecionada.getNome() + "' removida dos Favoritos.");
                }
                break;
            case "Assistidas":
                if (acao == 1) { // Remover
                    serieManager.removerSeriePorNome(listaOriginal, serieSelecionada.getNome());
                    System.out.println("'" + serieSelecionada.getNome() + "' removida das Assistidas.");
                }
                break;
            case "Desejadas":
                if (acao == 1) { // Mover para Assistidas
                    adicionarSerieNaLista(user.getSeriesAssistidas(), serieSelecionada, "Assistidas");
                    serieManager.removerSeriePorNome(listaOriginal, serieSelecionada.getNome());
                    System.out.println("'" + serieSelecionada.getNome() + "' movida de Desejadas para Assistidas.");
                } else if (acao == 2) { // Mover para Favoritas
                    adicionarSerieNaLista(user.getSeriesFavoritas(), serieSelecionada, "Favoritas");
                    serieManager.removerSeriePorNome(listaOriginal, serieSelecionada.getNome());
                    System.out.println("'" + serieSelecionada.getNome() + "' movida de Desejadas para Favoritas.");
                } else if (acao == 3) { // Remover
                    serieManager.removerSeriePorNome(listaOriginal, serieSelecionada.getNome());
                    System.out.println("'" + serieSelecionada.getNome() + "' removida das Desejadas.");
                }
                break;
        }
    }

    private static void adicionarSerieNaLista(List<Serie> lista, Serie serie, String nomeLista) {
        if (serieManager.contemSerie(lista, serie.getNome())) {
            System.out.println("'" + serie.getNome() + "' já está na sua lista de " + nomeLista + ".");
        } else {
            lista.add(serie);
            System.out.println("'" + serie.getNome() + "' adicionada com sucesso à lista de " + nomeLista + "!");
        }
    }

    private static int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}