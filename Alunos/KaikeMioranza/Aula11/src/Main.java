package src;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[]args) {
        Scanner sc = new Scanner(System.in);
        boolean Sair = true;

        while(Sair) {

            System.out.println("[1]-Recebendo 10 números inteiros \n" +
                    "[2]-Nomes convertido para maiusculo\n" +
                    "[3]-Palavras iguais\n" +
                    "[4]-Produtos com valores maior que R$100,00\n" +
                    "[5]-Palavras ordenadas de menor para maior\n" +
                    "[0]-Sair do programa");
            int Option = sc.nextInt();
            switch(Option) {
                //ATIVIDADE 1
                case 1:
                    int[] numero = new int[10];
                    for (int i = 0; i < 10; i++) {
                        System.out.println("---Informe um número inteiro entre(-2147483647 a 2147483647)---");
                        numero[i] = sc.nextInt();
                    }
                    if (numero.length == 10) {
                        List<Integer> NumeroPar = Arrays.stream(numero)
                                .filter(numeros -> numeros % 2 == 0)
                                .boxed()
                                .toList();

                        System.out.println(NumeroPar);

                    }
                    break;

                //ATIVIDADE 2
                case 2:
                    List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
                    List<String> nomesEmMaiuscula = nomes.stream()
                            .map(String::toUpperCase)
                            .collect(Collectors.toList());
                    System.out.println("Os nomes todos em maiusculo é:\n"+nomesEmMaiuscula);
                    break;
                //ATIVIDADE 3
                case 3:
                    List<String> Palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
                    Map<String, Long> PalavrasIguais = Palavras.stream()
                            .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
                    System.out.println(PalavrasIguais);

                    break;
                //ATIVIDADE 4 E 5
                case 4:
                    List<Produtos> Produtos = new ArrayList<>();
                    Produtos.add(new Produtos("Notebook Dell G15",5400.00));
                    Produtos.add(new Produtos("Notebook Acer Nitro 5",6000.00));
                    Produtos.add(new Produtos("Macbook Apple M2 pro",12000.00));

                    List<Produtos> produtosVenda = Produtos.stream()
                            .filter(p -> p.getValor() > 100.00)
                            .collect(Collectors.toList());

                    produtosVenda.forEach(System.out::println);

                    double soma = produtosVenda.stream()
                            .mapToDouble(src.Produtos::getValor)
                            .sum();

                    System.out.println("Soma total: R$ " + soma);
                    break;
                    //ATIVIDADE 6
                case 5 :
                    List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");

                    List<String> ordenadas = linguagens.stream()
                            .sorted((a, b) -> Integer.compare(a.length(), b.length()))
                            .collect(Collectors.toList());


                    System.out.println("Linguagens ordenadas por tamanho:");
                    ordenadas.forEach(System.out::println);
                    break;
                case 0:
                    System.out.println("Finalizando o programa");
                    Sair = false;
                    break;

            }
        }
    }
}
