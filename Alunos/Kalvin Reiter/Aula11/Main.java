import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Atividades StreamAPI:");

        //Atv1, codigo abaixo v
        System.out.println("\nAtividade 1:");
        List<Integer> numeros = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8));
        List<Integer> pares = numeros.stream()
                                     .filter(n -> n % 2 == 0)
                                     .collect(Collectors.toList());
        System.out.println("Números pares: " + pares);
        //Fim Atv1

        //Atv2, codigo abaixo v
        System.out.println("\nAtividade 2:");
        List<String> nomes = new ArrayList<>(Arrays.asList("roberto", "josé", "caio", "vinicius"));
        List<String> nomesMaiusculos = nomes.stream()
                                            .map(String::toUpperCase)
                                            .collect(Collectors.toList());
        System.out.println("Nomes em maiúsculas: " + nomesMaiusculos);
        //Fim Atv2

        //Atv 3, codigo abaixo v
        System.out.println("\nAtividade 3:");
        List<String> palavras = new ArrayList<>(Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado"));
        Map<String, Long> contagem = palavras.stream()
                                             .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        contagem.forEach((palavra, qtd) ->
            System.out.println("Palavra: " + palavra + " - Quantidade: " + qtd)
        );
        //Fim Atv3

        //Atv 4, codigo abaixo v
        System.out.println("\nAtividade 4:");
        List<Produto> produtos = Arrays.asList(
            new Produto("Kg da Alcatra", 120.0),
            new Produto("Kg do Patinho", 50.0),
            new Produto("Kg da Picanha", 800.0),
            new Produto("Kg do Musculo", 60.0)
        );
        List<Produto> produtosCaros = produtos.stream()
                                              .filter(p -> p.getPreco() > 100.0)
                                              .collect(Collectors.toList());
        produtosCaros.forEach(p ->
            System.out.println(p.getNome() + " - R$ " + p.getPreco())
        );
        //Fim Atv4

        //Atv 5, codigo abaixo v
        System.out.println("\nAtividade 5:");
        double soma = produtos.stream()
                              .mapToDouble(Produto::getPreco)
                              .sum();
        System.out.println("Soma total dos produtos: R$ " + soma);
        //Fim Atv 5

        //Atv 6, codigo abaixo v
        System.out.println("\nAtividade 6:");
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> ordenadas = linguagens.stream()
                                          .sorted(Comparator.comparingInt(String::length))
                                          .collect(Collectors.toList());
        System.out.println("Linguagens ordenadas por tamanho: " + ordenadas);
        //Fim Atv 6
    }
}

// Classe Produto Atv 4 e 5
class Produto {
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
}