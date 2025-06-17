package org.example;

import java.util.Arrays;
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
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        // ATV1 - Programa que recebe lista de números inteiros e retorna apenas os pares usando Stream API
        System.out.println("=== ATIVIDADE 1 ===");
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> numerosPares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Números pares: " + numerosPares);
        System.out.println();

        // ATV2 - Programa que converte nomes para letras maiúsculas usando Stream API
        System.out.println("=== ATIVIDADE 2 ===");
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Nomes em maiúsculo: " + nomesMaiusculos);
        System.out.println();

        // ATV3 - Programa que conta quantas vezes cada palavra única aparece em uma lista
        System.out.println("=== ATIVIDADE 3 ===");
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Contagem de palavras: " + contagemPalavras);
        System.out.println();

        // ATV4 - Programa que filtra produtos com preço maior que R$ 100,00
        System.out.println("=== ATIVIDADE 4 ===");
        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 1200.00),
                new Produto("Fones de Ouvido", 80.00),
                new Produto("Monitor", 650.00),
                new Produto("Mouse", 45.00)
        );

        List<Produto> produtosCaros = produtos.stream()
                .filter(p -> p.getPreco() > 100.00)
                .collect(Collectors.toList());

        System.out.println("Produtos com preço maior que R$ 100,00:");
        produtosCaros.forEach(System.out::println);
        System.out.println();

        // ATV5 - Soma do valor total dos produtos
        System.out.println("=== ATIVIDADE 5 ===");
        double valorTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
        System.out.println("Valor total dos produtos: R$ " + valorTotal);
        System.out.println();

        // ATV6 - Ordenação de lista de linguagens conforme tamanho da palavra
        System.out.println("=== ATIVIDADE 6 ===");
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted((s1, s2) -> Integer.compare(s1.length(), s2.length()))
                .collect(Collectors.toList());
        System.out.println("Linguagens ordenadas por tamanho: " + linguagensOrdenadas);
    }
}