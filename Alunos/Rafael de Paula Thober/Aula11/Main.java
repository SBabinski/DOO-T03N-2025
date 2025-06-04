import java.util.*;
import java.util.stream.*;

public class Main {

    static class Produto {
        private final String nome;
        private final double preco;

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
            return nome + " - R$ " + preco;
        }
    }

    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(3, 8, 12, 5, 7, 2, 14, 11);
        List<Integer> pares = numeros.stream()
            .filter(n -> n % 2 == 0)
            .toList();
        System.out.println(pares);

        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
            .map(String::toUpperCase)
            .toList();
        System.out.println(nomesMaiusculos);

        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagem = palavras.stream()
            .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        System.out.println(contagem);

        List<Produto> produtos = List.of(
            new Produto("Celular", 1200.0),
            new Produto("Cabo USB", 45.0),
            new Produto("Fone", 150.0),
            new Produto("Carregador", 80.0)
        );
        List<Produto> produtosCaros = produtos.stream()
            .filter(p -> p.getPreco() > 100)
            .toList();
        System.out.println(produtosCaros);

        double total = produtos.stream()
            .mapToDouble(Produto::getPreco)
            .sum();
        System.out.println("Total: R$ " + total);

        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> ordenadas = linguagens.stream()
            .sorted(Comparator.comparingInt(String::length))
            .toList();
        System.out.println(ordenadas);
    }
}
