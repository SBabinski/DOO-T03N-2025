import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        return nome + " - R$" + preco;
    }
}

public class Main {
    public static void main(String[] args) {

        // ATV1
        System.out.println("// ATV1");
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> numerosPares = numeros.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        System.out.println("Números pares: " + numerosPares);

        // ATV2
        System.out.println("\n// ATV2");
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("Nomes em maiúsculas: " + nomesMaiusculos);
        
        // ATV3
        System.out.println("\n// ATV3");
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagemPalavras = palavras.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Contagem de palavras: " + contagemPalavras);

        // ATV4
        System.out.println("\n// ATV4");
        List<Produto> produtos = Arrays.asList(
            new Produto("Notebook", 2500.00),
            new Produto("Mouse", 50.00),
            new Produto("Teclado", 120.00),
            new Produto("Monitor", 700.00)
        );
        List<Produto> produtosCaros = produtos.stream().filter(p -> p.getPreco() > 100.00).collect(Collectors.toList());
        System.out.println("Produtos com preço maior que R$100: " + produtosCaros);

        // ATV5
        System.out.println("\n// ATV5");
        double somaTotal = produtos.stream().mapToDouble(Produto::getPreco).sum();
        System.out.println("Soma total dos produtos: R$" + somaTotal);


        // ATV6
        System.out.println("\n// ATV6");
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> linguagensOrdenadas = linguagens.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        System.out.println("Linguagens ordenadas por tamanho: " + linguagensOrdenadas);
    }
}