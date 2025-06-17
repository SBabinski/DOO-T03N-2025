import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // ATV1 - Filtrar números pares de uma lista de inteiros
        System.out.println("//ATV1");
        var numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        var numerosPares = numeros.stream()
                .filter(numero -> numero % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Números pares: " + numerosPares);

        // ATV2 - Converter nomes para letras maiúsculas
        System.out.println("\n//ATV2");
        var nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        var nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Nomes em maiúsculas: " + nomesMaiusculos);

        // ATV3 - Contar aparições únicas de palavras
        System.out.println("\n//ATV3");
        var palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        var contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(palavra -> palavra, Collectors.counting()));
        System.out.println("Contagem de palavras: " + contagemPalavras);

        // ATV4 - Filtrar produtos com preço maior que R$ 100,00
        System.out.println("\n//ATV4");
        var produtos = Arrays.asList(
                new Produto("Produto A", 150.0),
                new Produto("Produto B", 90.0),
                new Produto("Produto C", 120.0),
                new Produto("Produto D", 80.0)
        );
        var produtosCaros = produtos.stream()
                .filter(produto -> produto.getPreco() > 100.0)
                .collect(Collectors.toList());
        System.out.println("Produtos com preço maior que R$ 100,00:");
        produtosCaros.forEach(produto -> System.out.println(produto.getNome() + " - R$ " + produto.getPreco()));

        // ATV5 - Somar valor total dos produtos utilizando BigDecimal
        System.out.println("\n//ATV5");
        var somaPrecos = produtos.stream()
                .map(produto -> BigDecimal.valueOf(produto.getPreco()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Formatar o valor como moeda (utiliza o Locale do Brasil)
        var formato = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        System.out.println("Soma total dos preços dos produtos: " + formato.format(somaPrecos));

        // ATV6 - Ordenar palavras pelo tamanho com desempate
        System.out.println("\n//ATV6");
        var linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        var linguagensOrdenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList());
        System.out.println("Linguagens ordenadas pelo tamanho: " + linguagensOrdenadas);
    }
}