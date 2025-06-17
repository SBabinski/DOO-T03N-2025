import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.Map.Entry;

class Produto {
    String nome;
    double preco;

    Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    public String toString() {
        return nome + " - R$" + preco;
    }
}

public class Aula10 {
    public static void main(String[] args) {

        // Atividade nº1
        List<Integer> numeros = Arrays.asList(1, 4, 7, 10, 12, 15, 18, 20);
        List<Integer> pares = numeros.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("// Atividade nº1 - Números pares: " + pares);

        // Atividade nº2
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println("// Atividade nº2 - Nomes em maiúsculas: " + nomesMaiusculos);

        // Atividade nº3
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagem = palavras.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("// Atividade nº3 - Contagem de palavras:");
        contagem.forEach((palavra, qtd) -> System.out.println(palavra + ": " + qtd));

        // Atividade nº4
        List<Produto> produtos = Arrays.asList(
            new Produto("Mouse", 250.00),
            new Produto("Teclado", 90.00),
            new Produto("Monitor", 800.00),
            new Produto("Fone", 70.00)
        );
        List<Produto> produtosCaros = produtos.stream()
            .filter(p -> p.getPreco() > 100.00)
            .collect(Collectors.toList());
        System.out.println("// Atividade nº4 - Produtos com preço > R$100,00:");
        produtosCaros.forEach(System.out::println);

        // Atividade nº5
        double soma = produtos.stream()
            .mapToDouble(Produto::getPreco)
            .sum();
        System.out.println("// Atividade nº5 - Soma total dos produtos: R$" + soma);

        // Atividade nº6
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> ordenadas = linguagens.stream()
            .sorted(Comparator.comparingInt(String::length))
            .collect(Collectors.toList());
        System.out.println("// Atividade nº6 - Linguagens ordenadas por tamanho: " + ordenadas);
    }
}