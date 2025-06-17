package api;
import java.util.*;
import java.util.stream.*;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 // Atv1 - Filtrar números pares
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 10, 13, 20, 22);
        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Atv1 - Números pares: " + pares);

        // Atv2 - Converter nomes para maiúsculas
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Atv2 - Nomes em maiúsculas: " + nomesMaiusculos);

        // Atv3 - Contar ocorrência de palavras
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        System.out.println("Atv3 - Contagem de palavras: " + contagem);

        // Atv4 - Filtrar produtos com preço > 100
        List<Produto> produtos = Arrays.asList(
                new Produto("Notebook", 3500.00),
                new Produto("Mouse", 50.00),
                new Produto("Teclado", 120.00),
                new Produto("Monitor", 800.00)
        );
        List<Produto> produtosCaros = produtos.stream()
                .filter(p -> p.getPreco() > 100.00)
                .collect(Collectors.toList());
        System.out.println("Atv4 - Produtos com preço > R$100,00:");
        produtosCaros.forEach(System.out::println);

        // Atv5 - Soma dos preços dos produtos
        double total = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
        System.out.println("Atv5 - Soma total dos preços dos produtos: R$ " + total);

        // Atv6 - Ordenar linguagens por tamanho do nome
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> ordenadasPorTamanho = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println("Atv6 - Linguagens ordenadas por tamanho: " + ordenadasPorTamanho);
    }

    // Classe Produto (para Atv4 e Atv5)
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
            return nome + " - R$" + preco;
        }
    }
    
}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	

