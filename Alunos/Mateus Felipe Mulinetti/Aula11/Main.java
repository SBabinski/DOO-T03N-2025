import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //atv 1
        List<Integer> numeros = List.of(1, 2, 3, 4, 5, 67, 86, 28, 75, 234, 46, 13);
        List<Integer> numerosPares = numeros.stream()
                .filter(numero -> numero % 2 == 0)
                .toList();
        System.out.println(numerosPares);
        System.out.println("\n");

        //atv2
        List<String> nomes1 = List.of("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculas = nomes1.stream()
                .map(nome1 -> nome1.toUpperCase())
                .toList();
        System.out.println(nomesMaiusculas);
        System.out.println("\n");

        //atv3
        List<String> nomes2 = List.of("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contarPalavras = nomes2.stream()
                .collect(Collectors.groupingBy(palavra -> palavra, Collectors.counting()));
        System.out.println(contarPalavras);
        System.out.println("\n");

        //atv4
        class Produto {
            private String nome;
            private Double preco;

            public Produto(String nome, Double preco) {
                this.nome = nome;
                this.preco = preco;
            }

            public String getNome() {
                return nome;
            }

            public Double getPreco() {
                return preco;
            }
        }

        List<Produto> produtos = List.of(
                new Produto("bebe reborn", 3500.0),
                new Produto("suco de laranja", 50.0),
                new Produto("cama", 1200.0),
                new Produto("Pinga", 80.0)
        );

        List<Produto> produtosCaros = produtos.stream()
                .filter(produto -> produto.getPreco() > 100.0)
                .toList();

        produtosCaros.forEach(produto ->
                System.out.println("nome: " + produto.getNome() + ", Preço: R$" + produto.getPreco())
        );
        System.out.println("\n");

        //atv5
        double valorTotal = produtos.stream()
                .mapToDouble(produto -> produto.getPreco())
                .sum();
        System.out.println("valor total: R$" + valorTotal);
        System.out.println("\n");

        //atv6
        List<String> nomes3 = List.of("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> ordemTamanho = nomes3.stream()
                .sorted(Comparator.comparingInt(String::length))
                .toList();
        System.out.println(ordemTamanho);


    }


}
