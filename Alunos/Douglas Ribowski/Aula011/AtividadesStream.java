import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AtividadesStream {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha a atividade (1 a 6):");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                atividade1();
                break;
            case 2:
                atividade2();
                break;
            case 3:
                atividade3();
                break;
            case 4:
                atividade4();
                break;
            case 5:
                atividade5();
                break;
            case 6:
                atividade6();
                break;
        
            default:
                System.out.println("Opção inválida.");
        }

        scanner.close();
    }

    public static void atividade1() {
        List<Integer> numeros = Arrays.asList(3, 8, 15, 22, 7, 10, 4, 19, 6);
        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Números pares: " + pares);
    }

    public static void atividade2() {
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> maiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Nomes em maiúsculas: " + maiusculos);
    }

    public static void atividade3() {
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

        Map<String, Long> frequencia = palavras.stream()
            .collect(Collectors.groupingBy(
                palavra -> palavra,
                Collectors.counting()
            ));

        System.out.println("Frequência das palavras:");
        frequencia.forEach((palavra, qtd) -> System.out.println(palavra + ": " + qtd));
    }

    public static void atividade4() {
    List<Produto> produtos = Arrays.asList(
        new Produto("Notebook", 3500.00),
        new Produto("Mouse", 80.00),
        new Produto("Teclado", 150.00),
        new Produto("Caderno", 20.00)
    );

    List<Produto> produtosCaros = produtos.stream()
        .filter(p -> p.getPreco() > 100.00)
        .collect(Collectors.toList());

    System.out.println("Produtos com preço maior que R$ 100,00:");
    produtosCaros.forEach(System.out::println);
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
        return nome + " - R$ " + preco;
    }
}

public static void atividade5() {
    List<Produto> produtos = Arrays.asList(
        new Produto("Notebook", 3500.00),
        new Produto("Mouse", 80.00),
        new Produto("Teclado", 150.00),
        new Produto("Caderno", 20.00)
    );

    double somaTotal = produtos.stream()
        .mapToDouble(Produto::getPreco)
        .sum();

    System.out.println("Valor total dos produtos: R$ " + somaTotal);
}

public static void atividade6() {
    List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");

    List<String> ordenadasPorTamanho = linguagens.stream()
        .sorted(Comparator.comparingInt(String::length))
        .collect(Collectors.toList());

    System.out.println("Linguagens ordenadas por tamanho:");
    ordenadasPorTamanho.forEach(System.out::println);
}


}

