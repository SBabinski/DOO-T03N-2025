package com.trabalhotvmaze.series;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TvMazeApiClient apiClient = new TvMazeApiClient(); // Instancia do cliente dA API
    private static final GerenciadorDeDados gerenciadorDeDados = new GerenciadorDeDados(); //Instancia do gerenciadr de arquivos
    private static Usuario usuario; //Usuario logado na sessão atual

    public static void main(String[] args) {
        carregarOuCriarUsuario();

        boolean rodando = true;
        while (rodando) {
            exibirMenuPrincipal();
            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    procurarSerie();
                    break;
                case "2":
                    gerenciarListas();
                    break;
                case "3":
                    gerenciadorDeDados.salvarUsuario(usuario);
                    rodando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
        System.out.println("Sistema encerrado.");
    }
    
    /**
     * Solicita o nome de usuário e tenta carregar os seus dados. Se não existir, cria um novo.
     */
    private static void carregarOuCriarUsuario() {
        System.out.print("Bem-vindo a MilenaFlix! Digite seu nome de usuário: ");
        String nomeUsuario = scanner.nextLine();

        // Tenta carregar o usuário
        usuario = gerenciadorDeDados.carregarUsuario(nomeUsuario);
        //Chama o gerenciador de dados e verifica se ele existe na memória
        // Se o usuário não existe, cria um novo
        if (usuario == null) {
            System.out.println("Usuário '" + nomeUsuario + "' não encontrado. Uma nova conta foi criada.");
            usuario = new Usuario(nomeUsuario);
        }
        
        System.out.println("Que bom ter você de volta, " + usuario.getNome() + "!");
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n----------- MilenaFlix -----------");
        System.out.println("1. Procurar por uma série");
        System.out.println("2. Ver/Gerenciar minhas listas");
        System.out.println("3. Salvar e Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void procurarSerie() {
        System.out.print("\nDigite o nome da série para buscar: ");
        String nomeBusca = scanner.nextLine();
        //Chama o cliente API para obter a lista de resultados.
        List<Serie> resultados = apiClient.buscarSeries(nomeBusca);

        if (resultados.isEmpty()) {
            System.out.println("Nenhuma série encontrada com este nome.");
            return; // Retorna ao menu principal
        }

        System.out.println("\nResultados da busca:");
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println((i + 1) + ". " + resultados.get(i).getNome());
        }

        System.out.print("\nEscolha o número da série para ver detalhes (ou 0 para voltar): ");
        int escolha = lerInteiro();
        if (escolha > 0 && escolha <= resultados.size()) {
        	//Obtem a série escolhida da lista, ajustando o índice de base 1 para 0.
            Serie serieEscolhida = resultados.get(escolha - 1);
            serieEscolhida.exibirDetalhes();
            //Depois de ver os detalher, mostra o menu pra adicionar a série em alguma lista
            menuAdicionarEmLista(serieEscolhida);
        }
    }

    private static void menuAdicionarEmLista(Serie serie) {
        System.out.println("\nInforme sua ação para com o título '" + serie.getNome() + "'?");
        System.out.println("1. Adicionar aos Favoritos");
        System.out.println("2. Adicionar às Séries Já Assistidas");
        System.out.println("3. Adicionar à Lista 'Para Assistir'");
        System.out.println("0. Voltar ao menu principal");
        System.out.print("Escolha: ");
        
        String escolha = scanner.nextLine();
        switch (escolha) {
            case "1": usuario.adicionarFavorito(serie); break;
            case "2": usuario.adicionarAssistida(serie); break;
            case "3": usuario.adicionarParaAssistir(serie); break;
            case "0": break;
            default: System.out.println("Opção inválida.");
        }
    }

    private static void gerenciarListas() {
        System.out.println("\nQual lista você deseja ver/gerenciar?");
        System.out.println("1. Favoritos");
        System.out.println("2. Séries Já Assistidas");
        System.out.println("3. Lista 'Para Assistir'");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");

        String escolha = scanner.nextLine();
        List<Serie> listaSelecionada = null;
        String tituloLista = "";

        switch (escolha) {
            case "1":
                listaSelecionada = usuario.getFavoritos();
                tituloLista = "Favoritos";
                break;
            case "2":
                listaSelecionada = usuario.getSeriesAssistidas();
                tituloLista = "Séries Já Assistidas";
                break;
            case "3":
                listaSelecionada = usuario.getSeriesParaAssistir();
                tituloLista = "Lista 'Para Assistir'";
                break;
            case "0":
                return;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        exibirEGerenciarLista(listaSelecionada, tituloLista);
    }
    
    private static void exibirEGerenciarLista(List<Serie> lista, String titulo) {
        if (lista.isEmpty()) {
            System.out.println("\nA lista '" + titulo + "' está vazia.");
            return;
        }

        // Ordenação
        System.out.println("\nComo deseja ordenar a lista '" + titulo + "'?");
        System.out.println("1. Ordem Alfabética (A-Z)");
        System.out.println("2. Melhor Nota");
        System.out.println("3. Status (em andamento, finalizada, etc)");
        System.out.println("4. Data de Estreia (mais antiga primeiro)");
        System.out.println("5. Não ordenar (padrão)");
        System.out.print("Escolha a ordenação: ");
        String escolhaOrdenacao = scanner.nextLine();
        
        Comparator<Serie> comparador = null;
        switch(escolhaOrdenacao) {
            case "1": comparador = Comparator.comparing(Serie::getNome); break;
            case "2": comparador = Comparator.comparing(Serie::getNota).reversed(); break;
            case "3": comparador = Comparator.comparing(Serie::getStatus); break;
            case "4": comparador = Comparator.comparing(Serie::getDataEstreiaAsDate, Comparator.nullsLast(Comparator.naturalOrder())); break;
        }
        
        final List<Serie> listaOrdenada;
        if (comparador != null) {
        	//Usa a Stream API pra criar uma nova lista ordenada sem modificar a original.
            listaOrdenada = lista.stream().sorted(comparador).collect(Collectors.toList());
        } else {
        	//Usa a lista original.
            listaOrdenada = lista;
        }
        
        // Exibição
        System.out.println("\n===== " + titulo.toUpperCase() + " =====");
        for (int i = 0; i < listaOrdenada.size(); i++) {
            Serie s = listaOrdenada.get(i);
            System.out.printf("%d. %s (Nota: %.1f, Status: %s)\n", (i + 1), s.getNome(), s.getNota(), s.getStatus());
        }

        // Gerenciamento (remover ou ver detalhes)
        System.out.print("\nEscolha o número da série para REMOVER ou ver DETALHES (ou 0 para voltar): ");
        int escolhaSerie = lerInteiro();
        if (escolhaSerie > 0 && escolhaSerie <= listaOrdenada.size()) {
            Serie serieSelecionada = listaOrdenada.get(escolhaSerie - 1);
            serieSelecionada.exibirDetalhes();

            System.out.print("Deseja remover '" + serieSelecionada.getNome() + "' desta lista? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                if (titulo.equals("Favoritos")) usuario.removerFavorito(serieSelecionada);
                else if (titulo.equals("Séries Já Assistidas")) usuario.removerAssistida(serieSelecionada);
                else if (titulo.equals("Lista 'Para Assistir'")) usuario.removerParaAssistir(serieSelecionada);
            }
        }
    }
    
    // Função auxiliar para ler inteiros de forma segura
    private static int lerInteiro() {
        while (true) {
            try {
            	// Tenta converter a linha lida para um inteiro.
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
            	// Se der erro, imprime:
                System.out.print("Entrada inválida. Por favor, digite um número: ");
            }
        }
    }
}
