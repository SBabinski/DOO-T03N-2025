import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        while (opcao != 3) {
            System.out.println("Menu:");
            System.out.println(" 1 - Calcular Preço Total");
            System.out.println(" 2 - Calcular Troco");
            System.out.println(" 3 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            if (opcao ==1) {
                    calcularPrecoTotal(scanner);
            }
            else if (opcao == 2) {
                    calcularTroco(scanner);
            }
            else if (opcao == 3) { 
                    System.out.println("Saindo...");
            }
            else {
                    System.out.println("Opção inválida");
            }
        }
    
        scanner.close();
    }

    public static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade da planta: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();
        double precoTotal = quantidade * precoUnitario;
        System.out.println("O preço total é: " + precoTotal);
    }

    public static void calcularTroco(Scanner scanner) {
        System.out.print("Digite o valor recebido do cliente: ");
        double valorRecebido = scanner.nextDouble();
        System.out.print("Digite o valor total da compra: ");
        double valorTotal = scanner.nextDouble();
        double troco = valorRecebido - valorTotal;
        System.out.println("O troco a ser dado é: " + troco);
    }
}