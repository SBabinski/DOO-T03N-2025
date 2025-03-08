import java.util.Scanner;

public class Conta {
    public static float totalCompra = 0;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        boolean turn = true;
        while (turn) {
            System.out.println("\nBem-vindo ao Programa de Conta");
            System.out.println("1 - Calcular");
            System.out.println("2 - Troco");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    calcular();
                    break;
                case 2:
                    troco(totalCompra);
                    break;
                case 3:
                    turn = false;
                    sair();
                    break;
                default:
                    System.out.println("Opção Inválida. Tente novamente.");
                    break;
            }
        }
    }

    public static void calcular() {
        System.out.print("Digite o valor do item: ");
        float precoItem = scanner.nextFloat();

        System.out.print("Digite a quantidade de itens: ");
        int quantidade = scanner.nextInt();

        totalCompra = precoItem * quantidade;
        System.out.printf("O valor total da compra é: R$ %.2f\n", totalCompra);
    }

    public static void troco(float totalCompra) {
        if (totalCompra == 0) {
            System.out.println("Nenhuma compra foi registrada.");
            return;
        }

        System.out.print("Digite o valor do pagamento: ");
        float pagamento = scanner.nextFloat();

        float troco = totalCompra - pagamento;

        if (troco > 0) {
            System.out.printf("O troco é: R$ %.2f\n", troco);
        } else if (troco < 0) {
            System.out.printf("Falta dinheiro:"+(-1*troco));
        } else {
            System.out.println("Pagamento exato! Sem troco.");
        }
    }

    public static void sair() {
        System.out.println("Fim do Programa. Obrigado!");
    }
}
