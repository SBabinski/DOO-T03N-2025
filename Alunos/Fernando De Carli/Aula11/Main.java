import java.util.*;
import java.util.stream.Collectors;

public class Main {
     static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        menu();

    }

    public static void menu() {
        int op;
        do {
            System.out.println("Testar atividades");
            System.out.println("1- Ativiade \n2- Atividade \n3- Atividade \n4- Atividade \n5- Atividade \n6- Atividade");
            System.out.println("0- Sair");
            op = sc.nextInt();

            switch (op) {
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
                    break;

            }
        } while (op != 0);
    }
    public static void atividade1(){
        List<Integer> numeros = Arrays.asList(3,10,15,22,8,7,12,9,14);

        System.out.println("Gostaria de ver a lista antes de separar os pares? \nSim ou Nao");
        String resp = sc.next();
        if(resp.equalsIgnoreCase("sim")){
            System.out.println("Lista : " + numeros);
        }

        List <Integer> numerosPares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Numeros pares: " + numerosPares);
        System.out.println("-----------------------");

    }
    public static void atividade2(){
        List<String> nomes = Arrays.asList("roberto","josé", "caio", "vinicius");

        System.out.println("Gostaria de ver a lista de nomes antes de modificar para maiusculo? \nSim ou Nao");
        String resp = sc.next();
        if(resp.equalsIgnoreCase("sim")){
            System.out.println("Lista de nomes: " + nomes);
        }
            List<String> nomesMaiusculo = nomes.stream()
                    .map(String::toUpperCase)
                    .collect(Collectors.toUnmodifiableList());

            System.out.println("Maiusculo : " + nomesMaiusculo + " atualizado");
            System.out.println("-----------------------");
    }
    public static void atividade3(){
        List<String> palavras = Arrays.asList("se", "talves", "hoje", "sabado", "se", "quarta", "sabado");

        Map<String, Long> contagem = palavras.stream()
        .collect(Collectors.groupingBy(
                palavra -> palavra,
                Collectors.counting()
        ));
        System.out.println("Contagem: " + contagem);
        System.out.println("-----------------------");

    }
    public static void atividade4(){

        List<Produto> produtos = Arrays.asList(
                new Produto("Celular", 1500.00),
                new Produto("Fone de ouvido", 90.00),
                new Produto("Notebook", 3500.00),
                new Produto("Mouse", 80.00),
                new Produto("Table", 2000.00),
                new Produto("Teclado", 99.00)
        );

        List<Produto> produtosFiltrados = produtos.stream()
                .filter(p -> p.preco > 100.00)
                .collect(Collectors.toList());

        System.out.println("Produtos com preço maior que R$ 100,00:");
        produtosFiltrados.forEach(System.out::println);
        System.out.println("-----------------------");
    }
    public static void atividade5(){
        List<Produto> produtos = Arrays.asList(
                new Produto("Celular", 1500.00),
                new Produto("Fone de ouvido", 90.00),
                new Produto("Notebook", 3500.00),
                new Produto("Mouse", 80.00)
        );

        double soma = produtos.stream()
                .mapToDouble(p -> p.preco)
                .sum();

        System.out.println("Soma total dos produtos: R$ " + soma);
        System.out.println("-----------------------");
    }
    public static void atividade6() {
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");

        List<String> ordenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("Ordenadas pelo tamanho:");
        ordenadas.forEach(System.out::println);
        System.out.println("-----------------------");
    }
}