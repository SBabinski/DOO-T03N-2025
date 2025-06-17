package Ativ;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		
	// Ativ1 - Filtrar uma lista com números pares
	List<Integer> numeros = Arrays.asList(3, 14, 8, 12, 4, 2, 11, 5, 13);
	List<Integer> pares = numeros.stream()
			.filter(n -> n % 2 == 0)
			.collect(Collectors.toList());
	System.out.println("------------------------------------\n");
	System.out.println("Atividade 1: \nNúmeros pares:"+pares+"\n");

	//Ativ2 - Colocar os nomes com a primeira letra maiúscula
	List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
	List<String> nomesMaiusculos = nomes.stream()
			.map(String::toUpperCase)
			.collect(Collectors.toList());
	System.out.println("------------------------------------\n");
	System.out.println("Atividade 2: \nNomes em maiúsculo: "+nomesMaiusculos+"\n");
	
	//Ativ3 - Frequência das palavras
	List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
	Map<String, Long> contagem = palavras.stream()
			.collect(Collectors.groupingBy
			(p -> p, Collectors.counting()));
	System.out.println("------------------------------------\n");
	System.out.println("Atividade 3: \nFrequência das palavras: ");
	contagem.forEach((palavra, qtd) -> System.out.println(palavra + ": " + qtd));
	System.out.println("\n");

	//Ativ4 -Filtrar produtos >$100
	List<Produto> produtos = Arrays.asList
			(new Produto("Notebook", 2500.00),
			 new Produto("Fone", 97.90),
			 new Produto ("Celular", 3600.95),
			 new Produto("Mouse", 64.90));
	List<Produto> caros = produtos.stream()
			.filter(p -> p.getPreco()> 100.00)
			.collect(Collectors.toList());
	System.out.println("------------------------------------\n");
	System.out.println("Atividade 4: \nProdutos com preço >R$100,00: ");
	caros.forEach(p -> System.out.println(p.getNome()+" R$"+p.getPreco()));
	System.out.println("\n");
	
	//Ativ5 - Somar o total dos preços
	double somaTotal = produtos.stream()
			.mapToDouble(Produto::getPreco)
			.sum();
	System.out.println("------------------------------------\n");
	System.out.println("Atividade 5: \nSoma total dos produtos: R$" +somaTotal);
	System.out.println("\n");
	
	//Ativ6 - Ordenar linguagens por tamanho
	List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
	List<String> ordenadasPorTamanho = linguagens.stream().
			sorted(Comparator.comparingInt(String::length))
			.collect(Collectors.toList());
	System.out.println("------------------------------------\n");
	System.out.println("Atividade 6: \nLinguagens ordenadas por tamanho"+ordenadasPorTamanho+"\n");
	}
}
 