package Alunos.Gabriel.Aula11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        return "Produto{" +
               "nome='" + nome +   "'" +
               ", preco=" + preco +
               "}";
    }



//Atividade 1
 public static void atv1(){
List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
System.out.println("Lista original: " + numeros);

List<Integer> numerosPares = numeros.stream()
                                    .filter(n -> n % 2 == 0) 
                                    .collect(Collectors.toList()); 

System.out.println("Lista de números pares: " + numerosPares);
 }




 //Atividade 2
 public static void atv2(){

    List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
System.out.println("Lista original de nomes: " + nomes);

List<String> nomesMaiusculos = nomes.stream()
                                    .map(String::toUpperCase) 
                                    .collect(Collectors.toList()); 

System.out.println("Lista de nomes em maiúsculas: " + nomesMaiusculos);
 }



 //Atividade 3
 public static void atv3(){

    List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
System.out.println("Lista original de palavras: " + palavras);

Map<String, Long> contagemPalavras = palavras.stream()
                                             .collect(Collectors.groupingBy(
                                                 Function.identity(), 
                                                 Collectors.counting() 
                                             ));

System.out.println("Contagem de cada palavra única: " + contagemPalavras);
 }




 //Atividade 4
 public static void atv4(){
   
    List<Produto> produtos = Arrays.asList(
    new Produto("PC Gamer", 3500.00),
    new Produto("Mouse", 75.50),
    new Produto("Teclado Gamer", 499.99),
    new Produto("Monitor", 1200.00),
    new Produto("Fone", 99.90)
   
);
System.out.println("Lista original de produtos: " + produtos);

List<Produto> produtosCaros = produtos.stream()
                                      .filter(p -> p.getPreco() > 100.00) 
                                      .collect(Collectors.toList()); 

System.out.println("Produtos com preço maior que R$ 100,00: " + produtosCaros);
 }


 

 //Atividade 5
 public static void atv5(){

    List<Produto> produtos = Arrays.asList(
    new Produto("PC Gamer", 3500.00),
    new Produto("Mouse", 75.50),
    new Produto("Teclado Gamer", 499.99),
    new Produto("Monitor", 1200.00),
    new Produto("Fone", 99.90)
    
);
System.out.println("Lista original de produtos: " + produtos);


System.out.println("Lista de produtos considerada: " + produtos);

double valorTotal = produtos.stream()
                            .mapToDouble(Produto::getPreco) 
                            .sum(); 

System.out.printf("Soma do valor total dos produtos: R$ %.2f%n", valorTotal);
 }




 //Atividade 6
 public static void atv6(){
    List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
System.out.println("Lista original de linguagens: " + linguagens);

List<String> linguagensOrdenadas = linguagens.stream()
                                             .sorted(Comparator.comparingInt(String::length)) 
                                             .collect(Collectors.toList()); 

System.out.println("Linguagens ordenadas por tamanho (menor para maior): " + linguagensOrdenadas);
 }

}

