import java.util.ArrayList;
import java.util.Scanner;

public class calculadora {
    static ArrayList<String> registroDeVendas = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== Calculadora de Vendas - Dona Gabrielinha ===");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Ver Registros de Vendas");
            System.out.println("[4] - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    calcularPrecoTotal(scanner);
                    break;
                case 2:
                    calcularTroco(scanner);
                    break;
                case 3:
                    mostrarRegistroDeVendas();
                    break;
                case 4:
                    System.out.println("Pinxando fora... Obrigado por usar a calculadora! Volte sempre.");
                    break;
                default:
                    System.out.println("Não deu boa! Tente denovo.");
            }
        } while (opcao != 4);

        scanner.close();
    }

    public static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade de plantas: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço de cada planta: ");
        double precoUnitario = scanner.nextDouble();

        double precoTotal = quantidade * precoUnitario;
        double desconto = 0;
        
        if (quantidade >= 10) {
            desconto = precoTotal * 0.05;
            precoTotal -= desconto;
            System.out.printf("Desconto especial de 5%% aplicado: R$ %.2f\n", desconto);
        }
        System.out.printf("Preço total da compra: R$ %.2f\n", precoTotal);

        String venda = String.format("Quantidade: %d | Total: R$ %.2f | Desconto: R$ %.2f", 
                quantidade, precoTotal, desconto);
        registroDeVendas.add(venda);        
    }

    public static void calcularTroco(Scanner scanner) {
        System.out.print("Digite o valor que o cliente pagou: ");
        double valorRecebido = scanner.nextDouble();
        System.out.print("Digite o valor da compra: ");
        double valorTotal = scanner.nextDouble();

        if (valorRecebido < valorTotal) {
            System.out.println("Erro: o valor é insuficiente!");
        } else {
            double troco = valorRecebido - valorTotal;
            System.out.printf("Troco a ser devolvido: R$ %.2f\n", troco);
        }
    }

    public static void mostrarRegistroDeVendas() {
        System.out.println("\n=== Registro de Vendas ===");
        if (registroDeVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada ainda.");
        } else {
            for (String venda : registroDeVendas) {
                System.out.println(venda);
            }
        }   
    }
}
