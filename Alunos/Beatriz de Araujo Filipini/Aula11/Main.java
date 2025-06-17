import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ATV1 - Filtrar números pares
        System.out.println("// ATV1 - Digite ao menos 8 números inteiros separados por espaço:");
        List<Integer> numeros = Arrays.stream(scanner.nextLine().split(" "))
                                      .map(Integer::parseInt)
                                      .collect(Collectors.toList());

        List<Integer> pares = numeros.stream()
                                     .filter(n -> n % 2 == 0)
                                     .collect(Collectors.toList());
        System.out.println("Números pares: " + pares);

        // ATV2 - Converter nomes para maiúsculas
        System.out.println("\n// ATV2 - Digite nomes separados por vírgula (ex: roberto,josé,caio):");
        List<String> nomes = Arrays.stream(scanner.nextLine().split(","))
                                   .map(String::trim)
                                   .collect(Collectors.toList());

        List<String> nomesMaiusculos = nomes.stream()
                                            .map(String::toUpperCase)
                                            .collect(Collectors.toList());
        System.out.println("Nomes em maiúsculas: " + nomesMaiusculos);

        // ATV3 - Contar palavras únicas
        System.out.println("\n// ATV3 - Digite palavras separadas por espaço:");
        List<String> palavras = Arrays.asList(scanner.nextLine().split(" "));

        Map<String, Long> contagem = palavras.stream()
                                             .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Contagem de palavras:");
        contagem.forEach((palavra, qtd) -> System.out.println(palavra + ": " + qtd + " vez(es)"));

        // ATV4 - Criar e filtrar produtos
        System.out.println("\n// ATV4 - Digite 4 produtos no formato nome,preço (ex: Teclado,150):");
        List<Produto> produtos = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String[] entrada = scanner.nextLine().split(",");
            String nomeProduto = entrada[0].trim();
            double precoProduto = Double.parseDouble(entrada[1].trim());
            produtos.add(new Produto(nomeProduto, precoProduto));
        }

        List<Produto> produtosCaros = produtos.stream()
                                              .filter(p -> p.getPreco() > 100.0)
                                              .collect(Collectors.toList());

        System.out.println("Produtos com preço > R$100,00:");
        produtosCaros.forEach(p -> System.out.println(p.getNome() + " - R$" + p.getPreco()));

        // ATV5 - Soma dos preços
        double soma = produtos.stream()
                              .mapToDouble(Produto::getPreco)
                              .sum();
        System.out.println("\n// ATV5 - Soma total dos preços: R$ " + soma);

        // ATV6 - Ordenar linguagens por tamanho
        System.out.println("\n// ATV6 - Digite linguagens separadas por vírgula (ex: Java,Python,C):");
        List<String> linguagens = Arrays.stream(scanner.nextLine().split(","))
                                        .map(String::trim)
                                        .collect(Collectors.toList());

        List<String> ordenadas = linguagens.stream()
                                           .sorted(Comparator.comparingInt(String::length))
                                           .collect(Collectors.toList());

        System.out.println("Linguagens ordenadas por tamanho: " + ordenadas);

        scanner.close();
    }
}

// Classe Produto
class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
