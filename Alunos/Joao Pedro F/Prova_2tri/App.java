package com.joaoedro.tvmaze;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;




public class App {
    private static TVMazeService tvMazeService;
    private static PersistenciaService persistenciaService;
    private static OrdenacaoService ordenacaoService;
    private static Usuario usuario;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        System.out.println("Iniciando o sistema de acompanhamento de séries...");
        
        
        tvMazeService = new TVMazeService();
        persistenciaService = new PersistenciaService();
        ordenacaoService = new OrdenacaoService();
        scanner = new Scanner(System.in);
        
        try {
            
            if (persistenciaService.existeArquivoUsuario()) {
                try {
                    usuario = persistenciaService.carregarUsuario();
                } catch (IOException e) {
                    System.err.println("Erro ao carregar dados do usuário: " + e.getMessage());
                    System.out.println("Criando novo usuário...");
                    criarNovoUsuario();
                }
            } else {
                criarNovoUsuario();
            }
            
            System.out.println("Olá, " + usuario.getNome() + "!");
            
            carregarDadosIniciais();
            
            boolean sair = false;
            while (!sair) {
                try {
                    exibirMenu();
                    int opcao = lerOpcaoNumerica();
                    
                    if (opcao == -1) continue; // Entrada inválida
                    
                    switch (opcao) {
                        case 1:
                            buscarSeries();
                            break;
                        case 2:
                            exibirFavoritos();
                            break;
                        case 3:
                            exibirSeriesAssistidas();
                            break;
                        case 4:
                            exibirSeriesParaAssistir();
                            break;
                        case 0:
                            sair = true;
                            break;
                        default:
                            System.out.println("Opção inválida! Por favor, escolha uma opção do menu.");
                    }
                } catch (Exception e) {
                    System.err.println("Ocorreu um erro: " + e.getMessage());
                    System.out.println("Pressione Enter para continuar...");
                    scanner.nextLine();
                }
            }
            
            System.out.println("Obrigado por usar o sistema de acompanhamento de séries!");
            
        } catch (Exception e) {
            System.err.println("Erro fatal: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fechar recursos
            if (scanner != null) {
                scanner.close();
            }
            
            if (tvMazeService != null) {
                try {
                    tvMazeService.fechar();
                } catch (IOException e) {
                    System.err.println("Erro ao fechar o serviço: " + e.getMessage());
                }
            }
        }
    }
    
    /**
     * Cria um novo usuário solicitando o nome
     */
    private static void criarNovoUsuario() {
        System.out.print("Bem-vindo! Por favor, digite seu nome: ");
        String nome = scanner.nextLine();
        
        // Validar entrada
        while (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome inválido. Por favor, digite um nome válido:");
            nome = scanner.nextLine();
        }
        
        usuario = new Usuario(nome);
        
        try {
            persistenciaService.salvarUsuario(usuario);
        } catch (IOException e) {
            System.err.println("Aviso: Não foi possível salvar os dados do usuário: " + e.getMessage());
        }
    }
    
    /**
     * Carrega dados iniciais para demonstração
     */
    private static void carregarDadosIniciais() {
        try {
            if (usuario.getSeriesFavoritas().isEmpty() && 
                usuario.getSeriesAssistidas().isEmpty() && 
                usuario.getSeriesParaAssistir().isEmpty()) {
                
                System.out.println("Carregando dados iniciais para demonstração...");
                
                try {
                    // Adicionar algumas séries populares
                    Serie breakingBad = tvMazeService.buscarSeriePorId(169); // Breaking Bad
                    Serie gameOfThrones = tvMazeService.buscarSeriePorId(82); // Game of Thrones
                    Serie strangerThings = tvMazeService.buscarSeriePorId(2993); // Stranger Things
                    
                    usuario.adicionarSerieAosFavoritos(breakingBad);
                    usuario.adicionarSerieAosFavoritos(gameOfThrones);
                    
                    usuario.adicionarSerieAssistida(breakingBad);
                    
                    usuario.adicionarSerieParaAssistir(strangerThings);
                    
                    persistenciaService.salvarUsuario(usuario);
                    System.out.println("Dados iniciais carregados com sucesso!");
                } catch (IOException e) {
                    System.err.println("Erro ao buscar séries para dados iniciais: " + e.getMessage());
                    System.out.println("Continuando sem dados iniciais...");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar dados iniciais: " + e.getMessage());
            System.out.println("Continuando sem dados iniciais...");
        }
    }
    
    /**
     * Exibe o menu principal
     */
    private static void exibirMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Buscar séries");
        System.out.println("2. Minhas séries favoritas");
        System.out.println("3. Séries já assistidas");
        System.out.println("4. Séries para assistir");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    /**
     * Lê uma opção numérica do usuário com tratamento de exceção
     * @return número digitado ou -1 em caso de erro
     */
    private static int lerOpcaoNumerica() {
        try {
            String entrada = scanner.nextLine();
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite um número.");
            return -1; // Código de erro
        }
    }
    
    /**
     * Busca séries pelo nome e permite adicionar às listas
     * @throws IOException Em caso de erro na API
     */
    private static void buscarSeries() throws IOException {
        System.out.print("Digite o nome da série para buscar: ");
        String termoBusca = scanner.nextLine();
        
        if (termoBusca == null || termoBusca.trim().isEmpty()) {
            System.out.println("Termo de busca inválido!");
            return;
        }
        
        try {
            List<Serie> resultados = tvMazeService.buscarSeriesPorNome(termoBusca);
            
            if (resultados.isEmpty()) {
                System.out.println("Nenhuma série encontrada com esse termo.");
                return;
            }
            
            System.out.println("Resultados encontrados: " + resultados.size());
            
            // Exibir os resultados
            for (int i = 0; i < resultados.size(); i++) {
                Serie serie = resultados.get(i);
                System.out.println((i + 1) + ". " + serie.getNome() + " (" + 
                        (serie.getStatus() != null ? serie.getStatus().getDescricao() : "N/A") + ")");
            }
            
            System.out.println("\nO que deseja fazer?");
            System.out.println("1. Adicionar série aos favoritos");
            System.out.println("2. Adicionar série às já assistidas");
            System.out.println("3. Adicionar série às que desejo assistir");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerOpcaoNumerica();
            
            if (opcao == -1) return; // Entrada inválida
            
            if (opcao >= 1 && opcao <= 3) {
                System.out.print("Digite o número da série: ");
                int escolha = lerOpcaoNumerica();
                
                if (escolha == -1) return; // Entrada inválida
                
                if (escolha > 0 && escolha <= resultados.size()) {
                    Serie serieSelecionada = resultados.get(escolha - 1);
                    
                    switch (opcao) {
                        case 1:
                            usuario.adicionarSerieAosFavoritos(serieSelecionada);
                            System.out.println("'" + serieSelecionada.getNome() + "' adicionada aos favoritos!");
                            break;
                        case 2:
                            usuario.adicionarSerieAssistida(serieSelecionada);
                            System.out.println("'" + serieSelecionada.getNome() + "' adicionada às séries assistidas!");
                            break;
                        case 3:
                            usuario.adicionarSerieParaAssistir(serieSelecionada);
                            System.out.println("'" + serieSelecionada.getNome() + "' adicionada às séries para assistir!");
                            break;
                    }
                    
                    // Salvar as alterações
                    try {
                        persistenciaService.salvarUsuario(usuario);
                    } catch (IOException e) {
                        System.err.println("Erro ao salvar alterações: " + e.getMessage());
                    }
                } else {
                    System.out.println("Número de série inválido!");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao buscar séries: " + e.getMessage());
            throw e; // Propagar para tratamento no nível superior
        }
    }
    
    /**
     * Exibe a lista de séries favoritas
     */
    private static void exibirFavoritos() {
        exibirLista(usuario.getSeriesFavoritas(), "favoritas");
    }
    
    /**
     * Exibe a lista de séries já assistidas
     */
    private static void exibirSeriesAssistidas() {
        exibirLista(usuario.getSeriesAssistidas(), "assistidas");
    }
    
    /**
     * Exibe a lista de séries para assistir
     */
    private static void exibirSeriesParaAssistir() {
        exibirLista(usuario.getSeriesParaAssistir(), "para assistir");
    }
    
    /**
     * Exibe uma lista de séries com opções de ordenação
     * @param series Lista de séries a ser exibida
     * @param tipo Tipo da lista (favoritas, assistidas, para assistir)
     */
    private static void exibirLista(List<Serie> series, String tipo) {
        if (series.isEmpty()) {
            System.out.println("Você não tem séries " + tipo + " ainda.");
            return;
        }
        
        System.out.println("\nSuas séries " + tipo + ":");
        
        // Opções de ordenação
        System.out.println("\nComo deseja ordenar a lista?");
        System.out.println("1. Por nome (A-Z)");
        System.out.println("2. Por nota (maior-menor)");
        System.out.println("3. Por estado (em exibição, finalizada, cancelada)");
        System.out.println("4. Por data de estreia (mais recente primeiro)");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerOpcaoNumerica();
        
        if (opcao == -1) return; // Entrada inválida
        
        // Aplicar ordenação
        try {
            switch (opcao) {
                case 1:
                    ordenacaoService.ordenarPorNome(series);
                    System.out.println("Lista ordenada por nome.");
                    break;
                case 2:
                    ordenacaoService.ordenarPorNota(series);
                    System.out.println("Lista ordenada por nota.");
                    break;
                case 3:
                    ordenacaoService.ordenarPorEstado(series);
                    System.out.println("Lista ordenada por estado.");
                    break;
                case 4:
                    ordenacaoService.ordenarPorDataEstreia(series);
                    System.out.println("Lista ordenada por data de estreia.");
                    break;
                default:
                    System.out.println("Opção inválida! Exibindo sem ordenação.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao ordenar lista: " + e.getMessage());
            System.out.println("Exibindo sem ordenação.");
        }
        
        // Exibir séries
        for (int i = 0; i < series.size(); i++) {
            Serie serie = series.get(i);
            System.out.println("\n" + (i + 1) + ". " + serie.getNome());
            System.out.println("   Idioma: " + serie.getIdioma());
            System.out.println("   Gêneros: " + serie.getGeneros());
            System.out.println("   Nota: " + serie.getNota());
            System.out.println("   Status: " + (serie.getStatus() != null ? serie.getStatus().getDescricao() : "N/A"));
            System.out.println("   Emissora: " + serie.getEmissora());
            
            // Exibir datas se disponíveis
            if (serie.getDataEstreia() != null) {
                System.out.println("   Data de Estreia: " + serie.getDataEstreia());
            }
            if (serie.getDataTermino() != null) {
                System.out.println("   Data de Término: " + serie.getDataTermino());
            }
        }
        
        // Opções para a lista
        System.out.println("\nO que deseja fazer?");
        System.out.println("1. Remover uma série da lista");
        System.out.println("0. Voltar ao menu principal");
        System.out.print("Escolha uma opção: ");
        
        int opcaoLista = lerOpcaoNumerica();
        
        if (opcaoLista == -1) return; // Entrada inválida
        
        if (opcaoLista == 1) {
            System.out.print("Digite o número da série que deseja remover: ");
            int escolha = lerOpcaoNumerica();
            
            if (escolha == -1) return; // Entrada inválida
            
            if (escolha > 0 && escolha <= series.size()) {
                Serie serieRemover = series.get(escolha - 1);
                
                try {
                    if (tipo.equals("favoritas")) {
                        usuario.removerSerieDeFavoritos(serieRemover);
                    } else if (tipo.equals("assistidas")) {
                        usuario.removerSerieAssistida(serieRemover);
                    } else if (tipo.equals("para assistir")) {
                        usuario.removerSerieParaAssistir(serieRemover);
                    }
                    
                    System.out.println("'" + serieRemover.getNome() + "' removida da lista de séries " + tipo + "!");
                    
                    // Salvar as alterações
                    try {
                        persistenciaService.salvarUsuario(usuario);
                    } catch (IOException e) {
                        System.err.println("Erro ao salvar alterações: " + e.getMessage());
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao remover série: " + e.getMessage());
                }
            } else {
                System.out.println("Número de série inválido!");
            }
        }
    }
}
