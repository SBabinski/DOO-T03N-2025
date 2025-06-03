import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.Map.Entry;

public class Main {
    public static void main(String[] args) {

       
        System.out.println("// ATV1");
            List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
            List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
            System.out.println("Números pares: " + pares);

        
        System.out.println("\n// ATV2");
            List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
            List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
            System.out.println("Nomes em maiúsculas: " + nomesMaiusculos);

        
        System.out.println("\n// ATV3");
            List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
            Map<String, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            System.out.println("Contagem de palavras:");
            contagem.forEach((palavra, qtd) -> System.out.println(palavra + ": " + qtd));

        
        System.out.println("\n// ATV4");
            List<Produto> produtos = Arrays.asList(
                new Produto("Celular", 1500.0),
                new Produto("Fone de ouvido", 85.0),
                new Produto("Teclado", 120.0),
                new Produto("Mousepad", 35.0)
            );
        List<Produto> produtosCaros = produtos.stream()
                .filter(p -> p.getPreco() > 100.0)
                .collect(Collectors.toList());
        System.out.println("Produtos com preço maior que R$ 100,00:");
        produtosCaros.forEach(p -> System.out.println(p.getNome() + " - R$ " + p.getPreco()));

        
        System.out.println("\n// ATV5");
        double somaTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
        System.out.println("Soma total dos produtos: R$ " + somaTotal);

        
        System.out.println("\n// ATV6");
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> ordenadasPorTamanho = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println("Linguagens ordenadas por tamanho: " + ordenadasPorTamanho);
    }
}

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
