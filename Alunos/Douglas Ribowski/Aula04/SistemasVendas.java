package SistemaVendas;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SistemaVendas {
    private static final Map<String, Integer> vendas = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Registrar vendas do dia");
            System.out.println("2. Consultar vendas por dia");
            System.out.println("3. Consultar vendas por mês");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    registrarVendas();
                    break;
                case 2:
                    consultarVendasPorDia();
                    break;
                case 3:
                    consultarVendasPorMes();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 4);
    }

    private static void registrarVendas() {
        System.out.print("Digite o mês (ex: Janeiro): ");
        String mes = scanner.nextLine().toLowerCase();
        System.out.print("Digite o dia: ");
        int dia = scanner.nextInt();
        System.out.print("Digite a quantidade de vendas: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        String chave = mes + "-" + dia;
        vendas.put(chave, vendas.getOrDefault(chave, 0) + quantidade);

        System.out.println("Vendas registradas com sucesso!");
    }

    private static void consultarVendasPorDia() {
        System.out.print("Digite o mês (ex: Janeiro): ");
        String mes = scanner.nextLine().toLowerCase();
        System.out.print("Digite o dia: ");
        int dia = scanner.nextInt();
        scanner.nextLine();

        String chave = mes + "-" + dia;
        int quantidade = vendas.getOrDefault(chave, 0);
        System.out.println("Total de vendas no dia " + dia + " de " + mes + ": " + quantidade);
    }

    private static void consultarVendasPorMes() {
        System.out.print("Digite o mês (ex: Janeiro): ");
        String mes = scanner.nextLine().toLowerCase();

        int total = vendas.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(mes))
                .mapToInt(Map.Entry::getValue)
                .sum();

        System.out.println("Total de vendas no mês de " + mes + ": " + total);
    }
}
