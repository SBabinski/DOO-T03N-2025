import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        atividade01();
        atividade02();
        atividade03();
        atividade04();
        atividade05();
        atividade06();
    }

    // Atividade 01, abaixo o código.
    public static void atividade01() {
        System.out.println("-- Atividade 01 --");
        List<Integer> listaNumeros = Arrays.asList(5, 8, 9, 10, 12, 15, 20, 28, 41);
        List<Integer> listaNumerosPares = listaNumeros.stream()
                .filter(n -> n % 2 == 0)
                .toList();
        System.out.println("Números pares:");
        System.out.println(listaNumerosPares);
    }

    // Atividade 02, abaixo o código.
    public static void atividade02() {
        System.out.println("-- Atividade 02 --");
        List<String> listaNomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> listaNomesMaiusculos = listaNomes.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("Nomes com as letras maiúsculas:");
        System.out.println(listaNomesMaiusculos);
    }

    // Atividade 03, abaixo o código.
    public static void atividade03() {
        System.out.println("-- Atividade 03 --");
        List<String> listaPalavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> listaContagemPalavras = listaPalavras.stream()
                .collect(Collectors.groupingBy(palavra -> palavra, Collectors.counting()));
        System.out.println("Contagem de palavras:");
        listaContagemPalavras.forEach((palavra, quantidade) -> System.out.printf("%s - %d vez(es)%n", palavra, quantidade));
    }

    // Atividade 04, abaixo o código.
    public static void atividade04() {
        System.out.println("-- Atividade 04 --");
        List<Produto> listaProdutos = gerarProdutos();
        List<Produto> listaProdutosFiltrados = listaProdutos.stream()
                .filter(p -> p.getPreco() > 100.0)
                .toList();
        System.out.println("Produtos com preço maior que R$100,00:");
        listaProdutosFiltrados.forEach(System.out::println);
    }

    // Atividade 05, abaixo o código.
    public static void atividade05() {
        System.out.println("-- Atividade 05 --");
        List<Produto> listaProdutos = gerarProdutos();
        double valorTotal = listaProdutos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
        System.out.println("Soma do total dos produtos:");
        System.out.println("R$ " + valorTotal);
    }

    // Atividade 06, abaixo o código.
    public static void atividade06() {
        System.out.println("-- Atividade 06 --");
        List<String> listaLinguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> listaLinguagensOrdenadas = listaLinguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .toList();
        System.out.println("Linguagens ordenadas por tamanho:");
        System.out.println(listaLinguagensOrdenadas);
    }

    // Método para gerar produtos, abaixo o código.
    private static List<Produto> gerarProdutos() {
        return Arrays.asList(
                new Produto("Colher", 5.0),
                new Produto("Televisão", 3000.0),
                new Produto("Mesa", 500.0),
                new Produto("Geladeira", 4000.0),
                new Produto("Penela", 50.0),
                new Produto("Microondas", 2000.0)
        );
    }

}

// Classe do Produto das atividades 04 e 05.
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