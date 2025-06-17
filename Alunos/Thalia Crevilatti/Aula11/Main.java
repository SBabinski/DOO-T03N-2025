import java.util.*;
import java.util.stream.*;
 
public class Main {
    public static void main(String[] args) {

        // ATV1 - Filtrar números pares
        System.out.println("Atividade 01:");
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> pares = numeros.stream()
                                     .filter(n -> n % 2 == 0)
                                     .collect(Collectors.toList());
        System.out.println("Números pares: " + pares);

        // ATV2 - Converter nomes para maiúsculas
        System.out.println("\n// Atividade 02:");
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
                                            .map(String::toUpperCase)
                                            .collect(Collectors.toList());
        System.out.println("Nomes em maiúsculas: " + nomesMaiusculos);

        // ATV3 - Contar frequência de palavras
        System.out.println("\nAtividade 03:");
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagem = palavras.stream()
                                             .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        System.out.println("Contagem de palavras: " + contagem);

        // ATV4 - Filtrar produtos com preço > 100
        System.out.println("\nAtividade 04:");
        List<Produto> produtos = Arrays.asList(
            new Produto("Notebook", 2500.00),
            new Produto("Mouse", 80.00),
            new Produto("Teclado", 120.00),
            new Produto("Fone", 90.00)
        );
        List<Produto> produtosCaros = produtos.stream()
                                              .filter(p -> p.getPreco() > 100.00)
                                              .collect(Collectors.toList());
        System.out.println("Produtos com preço > R$100: ");
        produtosCaros.forEach(p -> System.out.println(p.getNome() + " - R$" + p.getPreco()));

        // ATV5 - Soma total dos preços
        System.out.println("\nAtividade 05:");
        double soma = produtos.stream()
                              .mapToDouble(Produto::getPreco)
                              .sum();
        System.out.println("Soma total dos preços: R$" + soma);

        // ATV6 - Ordenar palavras por tamanho
        System.out.println("\nAtividade 06:");
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> ordenadas = linguagens.stream()
                                           .sorted(Comparator.comparingInt(String::length))
                                           .collect(Collectors.toList());
        System.out.println("Ordenadas por tamanho: " + ordenadas);
    }
}

// Classe Produto usada na ATV4 e ATV5
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