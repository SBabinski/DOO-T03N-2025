package seriesapp;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final ServicoTvMaze servicoTvMaze = new ServicoTvMaze();
    private static final ServicoPersistencia servicoPersistencia = new ServicoPersistencia();
    private static Usuario usuario;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        carregarOuCriarUsuario();
        exibirMenu();
        // Apenas salva os dados se o usuário não tiver resetado e saído
        if (usuario != null) {
            servicoPersistencia.salvarDados(usuario);
            System.out.println("\nPrograma finalizado. Dados salvos!");
        } else {
            System.out.println("\nPrograma finalizado.");
        }
    }

    private static void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Menu Principal de Séries ---");
            System.out.println("1. Buscar e Adicionar Série");
            System.out.println("2. Ver Minhas Listas");
            System.out.println("3. Remover Série de uma Lista");
            System.out.println("4. Configurações"); // NOVA OPÇÃO
            System.out.println("0. Sair e Salvar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 -> buscarEAdicionarSerie();
                    case 2 -> menuExibirListas();
                    case 3 -> menuRemoverSerie();
                    case 4 -> menuConfiguracoes(); // CHAMA O NOVO MENU
                    case 0 -> {}
                    default -> System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("ERRO: Por favor, digite um número válido.");
            }
        }
    }

    // NOVO MÉTODO PARA O MENU DE CONFIGURAÇÕES
    private static void menuConfiguracoes() {
        System.out.println("\n--- Configurações ---");
        System.out.println("1. Resetar Dados (apaga seu nome e todas as listas)");
        System.out.println("0. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");

        try {
            int escolha = Integer.parseInt(scanner.nextLine());
            if (escolha == 1) {
                resetarDados();
            }
        } catch (NumberFormatException e) {
            System.out.println("ERRO: Opção inválida.");
        }
    }

    // NOVO MÉTODO PARA A LÓGICA DE RESET
    private static void resetarDados() {
        System.out.println("\nATENÇÃO: Esta ação não pode ser desfeita.");
        System.out.print("Você tem CERTEZA que deseja apagar todos os seus dados? (Digite 'S' para confirmar): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            boolean sucesso = servicoPersistencia.apagarDados();
            if (sucesso) {
                System.out.println("Dados resetados com sucesso!");
                System.out.println("O programa será encerrado. Por favor, inicie-o novamente para criar um novo usuário.");
                usuario = null; // Invalida o usuário atual para não salvar nada na saída
                System.exit(0); // Fecha o programa
            } else {
                System.out.println("Ocorreu um erro e não foi possível apagar os dados.");
            }
        } else {
            System.out.println("Operação de reset cancelada.");
        }
    }

    private static void carregarOuCriarUsuario() {
        usuario = servicoPersistencia.carregarDados();
        if (usuario == null) {
            System.out.print("Bem-vindo! Parece ser seu primeiro acesso. Digite seu nome ou apelido: ");
            String nome = scanner.nextLine();
            usuario = new Usuario(nome);
        }
        System.out.println("Olá, " + usuario.getNome() + "!");
    }

    private static void buscarEAdicionarSerie() {
        System.out.print("Digite o nome da série para buscar: ");
        String nomeBusca = scanner.nextLine();
        List<Serie> resultados = servicoTvMaze.buscarSerie(nomeBusca);

        if (resultados.isEmpty()) {
            System.out.println("Nenhuma série encontrada com esse nome.");
            return;
        }

        System.out.println("\nResultados da busca:");
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println((i + 1) + " - " + resultados.get(i).getName());
        }

        System.out.print("Escolha o número da série para ver detalhes (0 para voltar): ");
        int escolha = Integer.parseInt(scanner.nextLine());

        if (escolha > 0 && escolha <= resultados.size()) {
            Serie serieEscolhida = resultados.get(escolha - 1);
            System.out.println("\n--- Detalhes da Série ---");
            System.out.println(serieEscolhida);

            System.out.println("Adicionar a qual lista?");
            System.out.println("1. Favoritos");
            System.out.println("2. Já assistidas");
            System.out.println("3. Desejo assistir");
            System.out.print("Opção (0 para não adicionar): ");
            int listaOpcao = Integer.parseInt(scanner.nextLine());

            switch(listaOpcao) {
                case 1 -> {
                    usuario.getFavoritos().add(serieEscolhida);
                    System.out.println("'" + serieEscolhida.getName() + "' adicionada aos FAVORITOS.");
                }
                case 2 -> {
                    usuario.getAssistidas().add(serieEscolhida);
                    System.out.println("'" + serieEscolhida.getName() + "' adicionada às JÁ ASSISTIDAS.");
                }
                case 3 -> {
                    usuario.getDesejoAssistir().add(serieEscolhida);
                    System.out.println("'" + serieEscolhida.getName() + "' adicionada à lista de DESEJOS.");
                }
            }
        }
    }

    private static void menuExibirListas() {
        System.out.println("\nQual lista você quer exibir?");
        System.out.println("1. Favoritos");
        System.out.println("2. Já assistidas");
        System.out.println("3. Desejo assistir");
        System.out.print("Opção: ");
        int listaOpcao = Integer.parseInt(scanner.nextLine());

        List<Serie> listaSelecionada;
        switch(listaOpcao) {
            case 1 -> {
                System.out.println("\n--- Meus Favoritos ---");
                listaSelecionada = usuario.getFavoritos();
            }
            case 2 -> {
                System.out.println("\n--- Séries que Já Assisti ---");
                listaSelecionada = usuario.getAssistidas();
            }
            case 3 -> {
                System.out.println("\n--- Séries que Desejo Assistir ---");
                listaSelecionada = usuario.getDesejoAssistir();
            }
            default -> {
                System.out.println("Opção inválida.");
                return;
            }
        }

        if (listaSelecionada.isEmpty()) {
            System.out.println("Esta lista está vazia.");
            return;
        }

        System.out.println("\nComo deseja ordenar?");
        System.out.println("1. Ordem Alfabética (Nome)");
        System.out.println("2. Nota Geral (Da maior para a menor)");
        System.out.println("3. Status da Série");
        System.out.println("4. Data de Estreia (Da mais nova para a mais antiga)");
        System.out.print("Opção de ordenação: ");
        int sortOpcao = Integer.parseInt(scanner.nextLine());

        List<Serie> listaOrdenada = new java.util.ArrayList<>(listaSelecionada);

        switch (sortOpcao) {
            case 1 -> listaOrdenada.sort(Comparator.comparing(Serie::getName, String.CASE_INSENSITIVE_ORDER));
            case 2 -> listaOrdenada.sort(Comparator.comparing(Serie::getNotaGeral).reversed());
            case 3 -> listaOrdenada.sort(Comparator.comparing(Serie::getStatus, String.CASE_INSENSITIVE_ORDER));
            case 4 -> listaOrdenada.sort(Comparator.comparing(Serie::getPremiered).reversed());
            default -> System.out.println("Opção de ordenação inválida. Exibindo na ordem padrão.");
        }

        System.out.println("------------------------");
        for (Serie s : listaOrdenada) {
            System.out.println(s);
            System.out.println("------------------------");
        }
    }

    private static void menuRemoverSerie() {
        System.out.println("\nDe qual lista você quer remover uma série?");
        System.out.println("1. Favoritos");
        System.out.println("2. Já assistidas");
        System.out.println("3. Desejo assistir");
        System.out.print("Opção: ");
        int listaOpcao = Integer.parseInt(scanner.nextLine());

        List<Serie> listaSelecionada;
        switch(listaOpcao) {
            case 1 -> listaSelecionada = usuario.getFavoritos();
            case 2 -> listaSelecionada = usuario.getAssistidas();
            case 3 -> listaSelecionada = usuario.getDesejoAssistir();
            default -> {
                System.out.println("Opção inválida.");
                return;
            }
        }

        if (listaSelecionada.isEmpty()) {
            System.out.println("Não há nada para remover. Esta lista está vazia.");
            return;
        }

        System.out.println("\nSéries na lista selecionada:");
        for (int i = 0; i < listaSelecionada.size(); i++) {
            System.out.println((i + 1) + " - " + listaSelecionada.get(i).getName());
        }

        System.out.print("Digite o número da série que deseja remover (0 para cancelar): ");
        int escolhaParaRemover = Integer.parseInt(scanner.nextLine());

        if (escolhaParaRemover > 0 && escolhaParaRemover <= listaSelecionada.size()) {
            Serie serieRemovida = listaSelecionada.remove(escolhaParaRemover - 1);
            System.out.println("A série '" + serieRemovida.getName() + "' foi removida com sucesso!");
        } else if (escolhaParaRemover == 0) {
            System.out.println("Operação cancelada.");
        } else {
            System.out.println("Número inválido.");
        }
    }
}