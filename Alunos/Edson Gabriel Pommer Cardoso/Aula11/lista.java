package Aula11;
import java.util.*;
import java.util.stream.Collectors;

public class lista {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int option;
        do {
            System.out.println("[1] - Lista números pares.");
            System.out.println("[2] - Nomes com letras maiúsculas.");
            System.out.println("[3] - Quatidade única de cada palavra.");
            System.out.println("[4] - Produtos com preço acima de R$ 100,00.");
            System.out.println("[5] - Soma produtos atividade 4.");
            System.out.println("[6] - Ordenar pelo tamanho da palavra.");
            System.out.println("[7] - Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    atv1(scanner);
                    break;
                case 2:
                    atv2();
                    break;
                case 3:
                    atv3();
                    break;
                case 4:
                    atv4();
                    break;
                case 5:
                    atv5();
                    break;
                case 6:
                    atv6();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 7);

        scanner.close();
    }

    public static void atv1(Scanner scanner) {
        List<Integer> numeros = new ArrayList<>();

        System.out.println("Digite 8 números inteiros:");
        for (int i = 0; i < 8; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, digite um número inteiro válido.");
                System.out.print("Número " + (i + 1) + ": ");
                scanner.next();
            }
            numeros.add(scanner.nextInt());
        }

        List<Integer> pares = numeros.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());

        System.out.println("Números pares: " + pares);
    }

    public static void atv2() {
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "viniciu");
        List<String> nomesMaiusculos = nomes.stream().map(String::toUpperCase).collect(Collectors.toList());

        System.out.println("Nomes em maiúsculas: " + nomesMaiusculos);
    }

    public static void atv3() {
        List<String> frases = Arrays.asList(
            "se", "talvez", "hoje", "sábado", "se", "quarta", "sábado"
        );

        Map<String, Integer> contagem = new HashMap<>();

        for (String frase : frases) {
            String[] palavras = frase.split("\\s+");

            for (String palavra : palavras) {
                palavra = palavra.toLowerCase();

                contagem.put(palavra, contagem.getOrDefault(palavra, 0) + 1);
            }
        }

        System.out.println("Contagem de palavras:");
        for (Map.Entry<String, Integer> entrada : contagem.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue());
        }
    }

    public static List<Produto> getProdutos() {
        return Arrays.asList(
                new Produto("Produto A", 150.00),
                new Produto("Produto B", 80.00),
                new Produto("Produto C", 120.00),
                new Produto("Produto D", 200.00)
        );
    }

    public static void atv4() {
        List<Produto> produtos = getProdutos();

        List<Produto> produtosAcimaDe100 = produtos.stream()
                .filter(produto -> produto.getPreco() > 100)
                .collect(Collectors.toList());

        System.out.println("Produtos com preço acima de R$ 100,00:");
        for (Produto produto : produtosAcimaDe100) {
            System.out.println(produto.getNome() + " - R$ " + produto.getPreco());
        }
    }

    public static void atv5() {
        List<Produto> produtos = getProdutos();
        System.out.println("Todos os produtos:");
        for (Produto produto : produtos) {
            System.out.println(produto.getNome() + " - R$ " + produto.getPreco());
        }
        double soma = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
        System.out.println("Soma dos preços dos produtos: " + soma);
    }
    

    public static void atv6(){
        List<String> palavras = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        
        List<String> palavrasOrdenadas = palavras.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("Palavras ordenadas pelo tamanho: " + palavrasOrdenadas);
    }
}


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
}
