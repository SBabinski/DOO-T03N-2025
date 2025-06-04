package listaStreamApi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListaStreamApi {
	
	
	// Classe Produto para atividades 4 e 5
    static class Produto {
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
                   ", preco=" + String.format("%.2f", preco) +
                   '}';
        }
    }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("--- Atividade 1: Números Pares ---");
        atividade1();
        System.out.println("\n--- Atividade 2: Nomes em Maiúsculas ---");
        atividade2();
        System.out.println("\n--- Atividade 3: Contagem de Palavras Únicas ---");
        atividade3();
        System.out.println("\n--- Atividade 4: Filtrar Produtos por Preço ---");
        List<Produto> produtosParaAtv5 = atividade4(); // Guardar a lista original para Atv5
        System.out.println("\n--- Atividade 5: Soma Total dos Preços dos Produtos ---");
        atividade5(produtosParaAtv5); // Passar a lista original para somar todos
        System.out.println("\n--- Atividade 6: Ordenar Palavras por Tamanho ---");
        atividade6();
		
	}
	
	// Atividade 01
	 public static void atividade1() {
	        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
	        System.out.println("Lista original: " + numeros);

	        List<Integer> numerosPares = numeros.stream()
	                                            .filter(n -> n % 2 == 0) 
	                                            .collect(Collectors.toList());

	        System.out.println("Números pares: " + numerosPares);
	    }
	 
	 
	 // Atividade 02
	 public static void atividade2() {
	        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
	        System.out.println("Nomes originais: " + nomes);

	        List<String> nomesMaiusculos = nomes.stream()
	                                            .map(String::toUpperCase) // Converte cada nome para maiúsculo
	                                            .collect(Collectors.toList());

	        System.out.println("Nomes em maiúsculas: " + nomesMaiusculos);
	    }
	 
	 
	 // Atividade 03
	 public static void atividade3() {
	        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
	        System.out.println("Lista de palavras: " + palavras);

	        Map<String, Long> contagemPalavras = palavras.stream()
	                .collect(Collectors.groupingBy(
	                        palavra -> palavra,       
	                        Collectors.counting()     
	                ));

	        System.out.println("Contagem de palavras: " + contagemPalavras);
	    }
	 
	 
	 // Atividade 04
	 public static List<Produto> atividade4() {
	        List<Produto> produtos = Arrays.asList(
	                new Produto("Notebook", 2500.00),
	                new Produto("Mouse", 89.90),
	                new Produto("Teclado Gamer", 150.50),
	                new Produto("Monitor HD", 750.00),
	                new Produto("Cadeira Office", 99.00) 
	        );
	        System.out.println("Lista de produtos original:");
	        produtos.forEach(System.out::println);

	        List<Produto> produtosCaros = produtos.stream()
	                                              .filter(p -> p.getPreco() > 100.00) // Filtra produtos com preço > 100
	                                              .collect(Collectors.toList());

	        System.out.println("\nProdutos com preço maior que R$ 100,00:");
	        produtosCaros.forEach(System.out::println);
	        return produtos; // Retorna a lista original para ser usada na atividade 05
	    }
	 
	 
	 // Atividade 05
	 public static void atividade5(List<Produto> produtos) { // Recebe a lista de produtos
	        System.out.println("Calculando soma total dos preços da lista original de produtos:");
	        produtos.forEach(System.out::println); 

	        double somaTotal = produtos.stream()
	                                   .mapToDouble(Produto::getPreco)
	                                   .sum();                         

	        System.out.println("Soma total dos preços: R$ " + String.format("%.2f", somaTotal));
	    }
	 
	 
	 // Atividade 06
	 public static void atividade6() {
	        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
	        System.out.println("Linguagens originais: " + linguagens);

	        List<String> linguagensOrdenadas = linguagens.stream()
	                .sorted(Comparator.comparingInt(String::length)) // Ordena pelo tamanho da string
	                .collect(Collectors.toList());

	        System.out.println("Linguagens ordenadas por tamanho: " + linguagensOrdenadas);
	    }

}
