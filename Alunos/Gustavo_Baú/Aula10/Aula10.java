package Alunos.Gustavo_Baú.Aula10;

import java.util.*;
import java.util.stream.Collectors;

class Produto {
    String nome;
    double preco;

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
public class Aula10 {

    public static void main(String[] args) {

        // Atividade 1
        System.out.println("\nNúmeros Pares");
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        List<Integer> numerosPares = numeros.stream()
                                            .filter(n -> n % 2 == 0)
                                            .collect(Collectors.toList());
        System.out.println("Lista original: " + numeros);
        System.out.println("Lista de números pares: " + numerosPares);

        // Atividade 2  
        System.out.println("\nNomes Maiúsculos");
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
                                            .map(String::toUpperCase)
                                            .collect(Collectors.toList());
        System.out.println("Lista original de nomes: " + nomes);
        System.out.println("Lista de nomes em maiúsculas: " + nomesMaiusculos);

        // Atividade 3
        System.out.println("\nContagem de Palavras");
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagemPalavras = palavras.stream()
                                                     .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        System.out.println("Lista original de palavras: " + palavras);
        System.out.println("Contagem de cada palavra única: " + contagemPalavras);

        // Atividade 4
        System.out.println("\nProdutos por Preço");
        List<Produto> produtos = Arrays.asList(
            new Produto("Notebook", 3500.00),
            new Produto("Mouse", 75.50),
            new Produto("Teclado", 150.99),
            new Produto("Monitor", 950.00),
            new Produto("Cadeira Gamer", 1250.00)
        );
        List<Produto> produtosCaros = produtos.stream()
                                              .filter(p -> p.getPreco() > 100.00)
                                              .collect(Collectors.toList());
        System.out.println("Lista original de produtos: " + produtos);
        System.out.println("Produtos com preço maior que R$ 100,00: " + produtosCaros);

        // AAtividade 5
        System.out.println("\nTotal dos Produtos");
        
        double somaTotal = produtos.stream()
                                   .mapToDouble(Produto::getPreco)
                                   .sum();
        System.out.println("Total do preços dos produtos é: R$ " + String.format("%.2f", somaTotal));

        // Atividade 6
        System.out.println("\nOrdenar Palavras por Tamanho");
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> linguagensOrdenadas = linguagens.stream()
                                                     .sorted(Comparator.comparingInt(String::length))
                                                     .collect(Collectors.toList());
        System.out.println("Lista original de linguagens: " + linguagens);
        System.out.println("Linguagens ordenadas por tamanho: " + linguagensOrdenadas);

    }
}
