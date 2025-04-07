import java.util.Scanner;

public class CalculadoraPlantas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        do {
            System.out.println("Calculadora da Dona Gabrielinha");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    System.out.print("Digite a quantidade da planta: ");
                    int quantidade = scanner.nextInt();
                    System.out.print("Digite o preço unitário da planta: ");
                    double preco = scanner.nextDouble();
                    double total = quantidade * preco;
                    System.out.println("Preço total: R$ " + total);
                    break;
                
                case 2:
                    System.out.print("Digite o valor recebido do cliente: ");
                    double valorRecebido = scanner.nextDouble();
                    System.out.print("Digite o valor total da compra: ");
                    double valorCompra = scanner.nextDouble();
                    double troco = valorRecebido - valorCompra;
                    System.out.println("Troco: R$ " + troco);
                    break;
                
                case 3:
                    System.out.println("Saindo...");
                    break;
                
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 3);
        
        scanner.close();
    }
}