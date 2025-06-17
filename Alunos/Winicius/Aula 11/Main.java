import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // ATV1 - Filtrar números pares de uma lista de inteiros
        System.out.println("// ATV1");
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> numerosPares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Números pares: " + numerosPares);

        // ATV2 - Converter lista de nomes para letras maiúsculas
        System.out.println("\n// ATV2");
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Nomes em maiúsculo: " + nomesMaiusculos);

        // ATV3 - Contar ocorrências de palavras únicas
        System.out.println("\n// ATV3");
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        System.out.println("Contagem de palavras: " + contagemPalavras);

        // ATV4 - Filtrar produtos com preço > 100,00
        System.out.println("\n// ATV4");
        List<Produto> produtos = Arrays.asList(
                new Produto("Notebook", 3000.0),
                new Produto("Teclado", 80.0),
                new Produto("Mouse", 50.0),
                new Produto("Monitor", 700.0)
        );
        List<Produto> produtosCaros = produtos.stream()
                .filter(p -> p.getPreco() > 100.0)
                .collect(Collectors.toList());
        System.out.println("Produtos com preço > R$ 100,00:");
        produtosCaros.forEach(System.out::println);

        // ATV5 - Soma total dos preços dos produtos
        System.out.println("\n// ATV5");
        double somaTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
        System.out.println("Soma total dos preços: R$ " + somaTotal);

        // ATV6 - Ordenar lista de palavras pelo tamanho
        System.out.println("\n// ATV6");
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println("Linguagens ordenadas por tamanho: " + linguagensOrdenadas);
    }
}

// Classe Produto para ATV4 e ATV5
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

    @Override
    public String toString() {
        return String.format("%s - R$ %.2f", nome, preco);
    }
}
