import java.util.*;
import java.util.stream.*;
import java.util.function.Function;

// Classe Produto usada nas atividades 4 e 5
class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + " - R$" + preco;
    }
}
public class Main {
    public static void main(String[] args) {

        // ATV1 - Filtrar números pares de uma lista
        List<Integer> numeros = Arrays.asList(10, 21, 32, 43, 54, 65, 76, 87);
        List<Integer> pares = numeros.stream()
                                     .filter(n -> n % 2 == 0)
                                     .collect(Collectors.toList());
        System.out.println("//ATV1 - Números pares: " + pares);

        // ATV2 - Converter nomes para letras maiúsculas
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
                                            .map(String::toUpperCase)
                                            .collect(Collectors.toList());
        System.out.println("//ATV2 - Nomes em maiúsculo: " + nomesMaiusculos);

        // ATV3 - Contar frequência de palavras
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> frequencia = palavras.stream()
                                               .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("//ATV3 - Frequência das palavras: " + frequencia);

        // ATV4 - Filtrar produtos com preço > R$ 100
        List<Produto> produtos = Arrays.asList(
            new Produto("Notebook", 2500.0),
            new Produto("Mouse", 80.0),
            new Produto("Teclado", 150.0),
            new Produto("Monitor", 900.0)
        );
        List<Produto> produtosCaros = produtos.stream()
                                              .filter(p -> p.getPreco() > 100.0)
                                              .collect(Collectors.toList());
        System.out.println("//ATV4 - Produtos com preço > R$ 100: " + produtosCaros);

        // ATV5 - Soma dos preços dos produtos
        double soma = produtos.stream()
                              .mapToDouble(Produto::getPreco)
                              .sum();
        System.out.println("//ATV5 - Soma total dos produtos: R$" + soma);

        // ATV6 - Ordenar linguagens por tamanho
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> ordenadasPorTamanho = linguagens.stream()
                                                     .sorted(Comparator.comparingInt(String::length))
                                                     .collect(Collectors.toList());
        System.out.println("//ATV6 - Linguagens ordenadas por tamanho: " + ordenadasPorTamanho);
    }
}
