import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        System.out.println("Atv1 numeros pares = " + evens());
        System.out.println("Atv2 nomes em maiúsculas = " + upperCaseNames());
        System.out.println("Atv3 frequência de palavras = " + wordFrequency());
        System.out.println("Atv4 produtos acima de R$100 = " + productAbove100());
        System.out.println("Atv5 soma dos produtos acima de R$100 = R$" + sumOfProductAbove100(productAbove100()));
        System.out.println("Atv6 linguagens ordenadas por tamanho = " + sortByStringLength());
    }

    private static List<Integer> evens() {
        List<Integer> numeros = Arrays.asList(3, 8, 15, 22, 7, 10, 31, 44, 55, 60);
        return numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
    }

    private static List<String> upperCaseNames() {
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        return nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    private static Map<String, Long> wordFrequency() {
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        return palavras.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
    }

    private static List<Produto> productAbove100() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Bola quadrada", 500.00));
        produtos.add(new Produto("Bola redonda", 85.50));
        produtos.add(new Produto("Moto de 2 rodas", 450.75));
        produtos.add(new Produto("Mouse Gaymer", 60.00));
        return produtos.stream()
                .filter(p -> p.getPreco() > 100.00)
                .collect(Collectors.toList());
    }

    private static int sumOfProductAbove100(List<Produto> productAbove100) {
        return (int) productAbove100.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
    }

    private static List<String> sortByStringLength() {
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        return linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
    }

    static class Produto {
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
            return nome;
        }
    }
}
