import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Calculadora {

    static Scanner scanner = new Scanner(System.in);
    static List<String> registroVendas = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Calcular conta");
            System.out.println("2. Calcular troco");
            System.out.println("3. Ver registro de vendas");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Digite um número válido!");
                scanner.next(); // Limpa a entrada inválida
                continue;
            }

            switch (opcao) {
                case 1:
                    conta();
                    break;
                case 2:
                    troco();
                    break;
                case 3:
                    mostrarVendas();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // Função 1 - Calcular Conta
    public static void conta() {
        try {
            System.out.print("Quantas plantas você pegou? ");
            int plantas = scanner.nextInt();

            System.out.print("Qual o preço da planta? ");
            float preco = scanner.nextFloat();

            float total = plantas * preco;
            float desconto = 0;

            if (plantas > 10) {
                desconto = total * 0.05f; // 5% de desconto
                total -= desconto;
                System.out.println("Você ganhou 5% de desconto!");
            }

            System.out.printf("O total é: R$ %.2f\n", total);

            // Armazena a venda
            String venda = "Plantas: " + plantas + ", Total: R$ " + String.format("%.2f", total) + ", Desconto: R$ " + String.format("%.2f", desconto);
            registroVendas.add(venda);

        } catch (InputMismatchException e) {
            System.out.println("Digite números válidos!");
            scanner.next();
        }
    }

    // Função 2 - Calcular Troco
    public static void troco() {
        try {
            System.out.print("Qual o valor da compra? ");
            float valor = scanner.nextFloat();

            System.out.print("Quanto você pagou? ");
            float pago = scanner.nextFloat();

            if (pago < valor) {
                System.out.println("Valor pago insuficiente.");
            } else {
                float troco = pago - valor;
                System.out.printf("O troco é: R$ %.2f\n", troco);
            }

        } catch (InputMismatchException e) {
            System.out.println("Digite números válidos!");
            scanner.next();
        }
    }

    // Função 3 - Mostrar Registro de Vendas
    public static void mostrarVendas() {
        if (registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            System.out.println("\n=== Registro de Vendas ===");
            for (String venda : registroVendas) {
                System.out.println(venda);
            }
        }
    }
}
