
import Produto;
public class Main {

    public static void main(String[] args) {

        //ATV1
        System.out.println("--- Ativ 1: Filtrar Números Pares ---");
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> numerosPares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Números originais: " + numeros);
        System.out.println("Números pares: " + numerosPares);
        System.out.println("---");

        //ATV2
        System.out.println("--- Ativ 2: Converter Nomes para Maiúsculas ---");
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Nomes originais: " + nomes);
        System.out.println("Nomes em maiúsculas: " + nomesMaiusculos);
        System.out.println("---");

        //ATV3
        System.out.println("--- Ativ 3: Contar Frequência de Palavras ---");
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        System.out.println("Lista de palavras: " + palavras);
        System.out.println("Contagem de palavras: " + contagemPalavras);
        System.out.println("---");

        //ATV4
        System.out.println("--- Ativ 4: Filtrar Produtos por Preço ---");
        List<Produto> produtos = Arrays.asList(
                new Produto("Notebook", 1500.00),
                new Produto("Mouse", 80.00),
                new Produto("Teclado Mecânico", 250.00),
                new Produto("Monitor", 700.00)
        );
        List<Produto> produtosCaros = produtos.stream()
                .filter(p -> p.getPreco() > 100.00)
                .collect(Collectors.toList());
        System.out.println("Todos os produtos: " + produtos);
        System.out.println("Produtos com preço maior que R$ 100,00: " + produtosCaros);
        System.out.println("---");

        //ATV5
        System.out.println("--- Ativ 5: Somar Valor Total dos Produtos ---");
        double somaTotalProdutos = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
        System.out.println("Soma total do valor dos produtos: R$ " + String.format("%.2f", somaTotalProdutos));
        System.out.println("---");

        //ATV6
        System.out.println("--- Ativ 6: Ordenar Lista por Tamanho da Palavra ---");
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted((s1, s2) -> Integer.compare(s1.length(), s2.length()))
                .collect(Collectors.toList());
        System.out.println("Linguagens originais: " + linguagens);
        System.out.println("Linguagens ordenadas por tamanho: " + linguagensOrdenadas);
        System.out.println("---");
    }
}
