import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class RastreadorSeriesApp {
    private static Usuario usuario;
    private static final GerenciadorDados gerenciador = new GerenciadorDados();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        usuario = gerenciador.carregarUsuario(scanner);
        System.out.println("\nBem-vindo ao Rastreador de Séries, " + usuario.getNome() + "!");
        loopPrincipal();
    }

    private static void loopPrincipal() {
        boolean sair = false;
        while (!sair) {
            exibirMenuPrincipal();
            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: buscarSeries(); break;
                    case 2: exibirListas(); break;
                    case 3: gerenciarListas(); break;
                    case 0: sair = true; break;
                    default: System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
            } catch (Exception e) {
                System.err.println("Ocorreu um erro na operação: " + e.getMessage());
                e.printStackTrace();
            }
        }
        gerenciador.salvarUsuario(usuario);
        System.out.println("Até a próxima!");
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n===== Rastreador de Séries de TV =====");
        System.out.println("1. Procurar por Série");
        System.out.println("2. Exibir Minhas Listas");
        System.out.println("3. Adicionar/Remover Série de uma Lista");
        System.out.println("0. Salvar e Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void buscarSeries() throws IOException, InterruptedException {
        System.out.print("\nDigite o nome da série para procurar: ");
        String nome = scanner.nextLine();
        List<Serie> resultados = gerenciador.buscarSeriesPorNome(nome);

        if (resultados.isEmpty()) {
            System.out.println("Nenhuma série encontrada com este nome.");
            return;
        }

        System.out.println("\nResultados encontrados:");
        for (Serie serie : resultados) {
            serie.exibir();
            gerenciador.adicionarSerieAoDB(serie);
        }
        System.out.println("As séries encontradas foram salvas no seu banco de dados local.");
        System.out.println("Use a opção '3' do menu para adicioná-las às suas listas usando o ID.");
    }

    private static void exibirListas() {
        System.out.println("\nQual lista deseja exibir?");
        System.out.println("1. Favoritas");
        System.out.println("2. Séries Já Assistidas");
        System.out.println("3. Séries que Desejo Assistir");
        System.out.print("Opção: ");
        
        int opcao = Integer.parseInt(scanner.nextLine());
        Set<Integer> ids;
        String nomeLista;

        switch (opcao) {
            case 1: ids = usuario.getSeriesFavoritas(); nomeLista = "Favoritas"; break;
            case 2: ids = usuario.getSeriesAssistidas(); nomeLista = "Já Assistidas"; break;
            case 3: ids = usuario.getSeriesParaAssistir(); nomeLista = "Desejo Assistir"; break;
            default: System.out.println("Opção inválida."); return;
        }

        if (ids.isEmpty()) {
            System.out.println("\nA lista '" + nomeLista + "' está vazia.");
            return;
        }
        
        List<Serie> seriesNaLista = new ArrayList<>();
        for (int id : ids) {
            Serie s = gerenciador.getSeriePorId(id);
            if (s != null) {
                seriesNaLista.add(s);
            }
        }

        ordenarEImprimirLista(seriesNaLista, nomeLista);
    }

    private static void ordenarEImprimirLista(List<Serie> lista, String nomeLista) {
        System.out.println("\nComo deseja ordenar a lista '" + nomeLista + "'?");
        System.out.println("1. Ordem Alfabética (Nome)");
        System.out.println("2. Nota Geral (Maior para menor)");
        System.out.println("3. Estado (Status)");
        System.out.println("4. Data de Estreia");
        System.out.print("Opção de ordenação: ");
        
        int sortOption = Integer.parseInt(scanner.nextLine());
        switch (sortOption) {
            case 1: lista.sort(Comparator.comparing(Serie::getName, String.CASE_INSENSITIVE_ORDER)); break;
            case 2: lista.sort(Comparator.comparingDouble((Serie s) -> (s.getRating() != null && s.getRating().average != null) ? s.getRating().average : 0.0).reversed()); break;
            case 3: lista.sort(Comparator.comparing(Serie::getStatus)); break;
            case 4: lista.sort(Comparator.comparing(Serie::getPremiered).reversed()); break;
            default: System.out.println("Opção inválida. Exibindo na ordem padrão.");
        }
        
        System.out.println("\n--- Lista: " + nomeLista + " ---");
        lista.forEach(Serie::exibir);
    }
    
    private static void gerenciarListas() {
        System.out.print("\nDigite o ID da série que deseja gerenciar: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (gerenciador.getSeriePorId(id) == null) {
            System.out.println("ID não encontrado no seu banco de dados. Busque pela série primeiro.");
            return;
        }

        System.out.println("O que deseja fazer com a série #" + id + "?");
        System.out.println("1. Adicionar/Remover dos Favoritos");
        System.out.println("2. Adicionar/Remover das Já Assistidas");
        System.out.println("3. Adicionar/Remover das 'Desejo Assistir'");
        System.out.print("Opção: ");
        int escolhaAcao = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Digite (1) para ADICIONAR ou (2) para REMOVER: ");
        int escolhaAddRemove = Integer.parseInt(scanner.nextLine());

        switch (escolhaAcao) {
            case 1: 
                if (escolhaAddRemove == 1) usuario.addFavorita(id); else usuario.removeFavorita(id);
                System.out.println("Lista de Favoritos atualizada.");
                break;
            case 2:
                if (escolhaAddRemove == 1) usuario.addAssistida(id); else usuario.removeAssistida(id);
                System.out.println("Lista de 'Já Assistidas' atualizada.");
                break;
            case 3:
                if (escolhaAddRemove == 1) usuario.addParaAssistir(id); else usuario.removeParaAssistir(id);
                System.out.println("Lista de 'Desejo Assistir' atualizada.");
                break;
            default:
                System.out.println("Ação inválida.");
        }
    }
}