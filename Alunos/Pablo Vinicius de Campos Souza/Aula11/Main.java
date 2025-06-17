package Aula11;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        atv1();
        atv2();
        atv3();
        atv4();
        atv5();
        atv6();
    }

    // ATV1
    public static void atv1() {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 10, 15, 20, 22, 33);
        List<Integer> numerosPares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .toList();
        System.out.println("Atividade 1 - Números pares:");
        System.out.println(numerosPares);
    }

    // ATV2
    public static void atv2() {
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("\nAtividade 2 - Nomes em maiúsculas:");
        System.out.println(nomesMaiusculos);
    }

    // ATV3
    public static void atv3() {
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(palavra -> palavra, Collectors.counting()));
        System.out.println("\nAtividade 3 - Contagem de palavras:");
        contagemPalavras.forEach((palavra, quantidade) ->
                System.out.printf("%s - %dx%n", palavra, quantidade));
    }

    // ATV4
    public static void atv4() {
        List<Produto> produtos = gerarProdutos();
        List<Produto> produtosFiltrados = produtos.stream()
                .filter(p -> p.getPreco() > 100.0)
                .toList();
        System.out.println("\nAtividade 4 - Produtos com preço > R$100,00:");
        produtosFiltrados.forEach(System.out::println);
    }

    // ATV5
    public static void atv5() {
        List<Produto> produtos = gerarProdutos();
        double valorTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
        System.out.println("\nAtividade 5 - Soma total dos produtos:");
        System.out.println("R$ " + valorTotal);
    }

    // ATV6
    public static void atv6() {
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .toList();
        System.out.println("\nAtividade 6 - Linguagens ordenadas por tamanho:");
        System.out.println(linguagensOrdenadas);
    }

    private static List<Produto> gerarProdutos() {
        return Arrays.asList(
                new Produto("Celular", 2000.0),
                new Produto("Teclado", 150.0),
                new Produto("Mouse", 200.0),
                new Produto("Cabo USB", 90.0)
        );
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

    @Override
    public String toString() {
        return nome + " - R$ " + preco;
    }
}
