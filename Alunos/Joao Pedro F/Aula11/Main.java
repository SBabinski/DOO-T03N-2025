import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao;
            
            do {
                // Exibir menu
                System.out.println("\n=== MENU DE ATIVIDADES ===");
                System.out.println("1. Filtrar números pares");
                System.out.println("2. Converter nomes para maiúsculas");
                System.out.println("3. Contar ocorrências de palavras únicas");
                System.out.println("4. Filtrar produtos com preço > R$ 100,00");
                System.out.println("5. Somar o valor total dos produtos");
                System.out.println("6. Ordenar strings por tamanho");
                System.out.println("0. Sair");
                System.out.print("\nEscolha uma opção: ");
                
                // Ler opção do usuário
                opcao = scanner.nextInt();
                
                // Executar a atividade escolhida
                switch (opcao) {
                    case 1 -> filtrarNumerosPares();
                    case 2 -> converterNomesMaiusculas();
                    case 3 -> contarOcorrenciasPalavras();
                    case 4 -> filtrarProdutosCaros();
                    case 5 -> somarValorProdutos();
                    case 6 -> ordenarStringsPorTamanho();
                    case 0 -> System.out.println("Saindo do programa. Até mais!");
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
                
                // Se não for a opção de sair, aguardar usuário pressionar ENTER para continuar
                if (opcao != 0) {
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine(); // Consumir a quebra de linha pendente
                    scanner.nextLine(); // Aguardar o ENTER
                }
                
            } while (opcao != 0);
        }
    }
    
    // ATV1: Filtrar números pares
    private static void filtrarNumerosPares() {
        System.out.println("\n=== ATIVIDADE 1: FILTRAR NÚMEROS PARES ===");
        
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        System.out.println("Lista original: " + numeros);

        List<Integer> numerosPares = numeros.stream()
                                           .filter(n -> n % 2 == 0) // Filtra apenas os números pares
                                           .collect(Collectors.toList()); // Coleta o resultado em uma nova lista

        System.out.println("\nLista de números pares: " + numerosPares);
    }
    




    // ATV2: Converter nomes para maiúsculas
    private static void converterNomesMaiusculas() {
        System.out.println("\n=== ATIVIDADE 2: CONVERTER NOMES PARA MAIÚSCULAS ===");
        
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        System.out.println("Lista original de nomes: " + nomes);

        List<String> nomesMaiusculos = nomes.stream()
                                           .map(String::toUpperCase) // Converte cada nome para maiúsculas
                                           .collect(Collectors.toList()); // Coleta o resultado em uma nova lista

        System.out.println("\nLista de nomes em maiúsculas: " + nomesMaiusculos);
    }
    



    
    // ATV3: Contar ocorrências de palavras únicas
    private static void contarOcorrenciasPalavras() {
        System.out.println("\n=== ATIVIDADE 3: CONTAR OCORRÊNCIAS DE PALAVRAS ÚNICAS ===");
        
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        System.out.println("Lista original de palavras: " + palavras);

        Map<String, Long> contagemPalavras = palavras.stream()
                                                    .collect(Collectors.groupingBy(
                                                        Function.identity(), // Agrupa pela própria palavra
                                                        Collectors.counting() // Conta as ocorrências de cada palavra
                                                    ));

        System.out.println("\nContagem de cada palavra única: " + contagemPalavras);
    }
    

    
    // ATV4: Filtrar produtos com preço > R$ 100,00
    private static void filtrarProdutosCaros() {
        System.out.println("\n=== ATIVIDADE 4: FILTRAR PRODUTOS COM PREÇO > R$ 100,00 ===");
        
        List<Produto> produtos = Arrays.asList(
            new Produto("Notebook", 3500.00),
            new Produto("Mouse", 75.50),
            new Produto("Teclado Gamer", 499.99),
            new Produto("Monitor", 1200.00),
            new Produto("Webcam", 99.90)
        );
        System.out.println("Lista original de produtos: " + produtos);

        List<Produto> produtosCaros = produtos.stream()
                                             .filter(p -> p.getPreco() > 100.00) // Filtra produtos com preço maior que 100
                                             .collect(Collectors.toList()); // Coleta o resultado em uma nova lista

        System.out.println("\nProdutos com preço maior que R$ 100,00: " + produtosCaros);
    }
    


    // ATV5: Somar o valor total dos produtos
    private static void somarValorProdutos() {
        System.out.println("\n=== ATIVIDADE 5: SOMAR O VALOR TOTAL DOS PRODUTOS ===");
        
        List<Produto> produtos = Arrays.asList(
            new Produto("Notebook", 3500.00),
            new Produto("Mouse", 75.50),
            new Produto("Teclado Gamer", 499.99),
            new Produto("Monitor", 1200.00),
            new Produto("Webcam", 99.90)
        );
        System.out.println("Lista de produtos considerada: " + produtos);

        double valorTotal = produtos.stream()
                                   .mapToDouble(Produto::getPreco) // localiza cada produto para seu preço 
                                   .sum(); // Soma todos os preços

        System.out.printf("Soma do valor total dos produtos: R$ %.2f%n", valorTotal);
    }
    



    // ATV6: Ordenar strings por tamanho
    private static void ordenarStringsPorTamanho() {
        System.out.println("\n=== ATIVIDADE 6: ORDENAR STRINGS POR TAMANHO ===");
        
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        System.out.println("Lista original de linguagens: " + linguagens);

        List<String> linguagensOrdenadas = linguagens.stream()
                                                    .sorted(Comparator.comparingInt(String::length)) // arrumaa pela quantidade de caracteres
                                                    .collect(Collectors.toList()); // pega o resultado na nova lita

        System.out.println("\nLinguagens ordenadas por tamanho (menor para maior): " + linguagensOrdenadas);
    }
}
