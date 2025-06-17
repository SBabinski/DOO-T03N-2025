import java.util.*;
import java.util.stream.*;

public class Main {
    
    // Classe Produto para as atividades 4 e 5
    static class Produto {
        String nome;
        double preco;

        public Produto(String nome, double preco) {
            this.nome = nome;
            this.preco = preco;
        }

        public double getPreco() {
            return preco;
        }
    }

    public static void main(String[] args) {
        
        List<Integer> numeros = Arrays.asList(12, 7, 23, 4, 8, 15, 10, 6, 9, 11);
        List<Integer> pares = numeros.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("Atv1 - Números pares: " + pares);

        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println("Atv2 - Nomes em maiúsculas: " + nomesMaiusculos);

        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagem = palavras.stream()
            .collect(Collectors.groupingBy(
                palavra -> palavra, 
                Collectors.counting()
            ));
        System.out.println("Atv3 - Contagem de palavras:");
        contagem.forEach((palavra, freq) -> 
            System.out.println(palavra + ": " + freq));

        List<Produto> produtos = Arrays.asList(
            new Produto("Monitor", 450.0),
            new Produto("Teclado", 80.0),
            new Produto("Mouse", 50.0),
            new Produto("Headset", 150.0)
        );
        List<Produto> produtosCaros = produtos.stream()
            .filter(p -> p.getPreco() > 100.0)
            .collect(Collectors.toList());
        System.out.println("Atv4 - Produtos acima de R$ 100:");
        produtosCaros.forEach(p -> 
            System.out.println(p.nome + " - R$" + p.preco));

        double total = produtos.stream()
            .mapToDouble(Produto::getPreco)
            .sum();
        System.out.printf("Atv5 - Valor total: R$%.2f%n", total);

        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> linguagensOrdenadas = linguagens.stream()
            .sorted(Comparator.comparingInt(String::length))
            .collect(Collectors.toList());
        System.out.println("Atv6 - Linguagens ordenadas: " + linguagensOrdenadas);
    }
}