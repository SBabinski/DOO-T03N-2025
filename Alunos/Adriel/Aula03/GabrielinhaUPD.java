import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Venda {
    int quantidade;
    double valorVenda;
    double desconto;

    public Venda(int quantidade, double valorVenda, double desconto) {
        this.quantidade = quantidade;
        this.valorVenda = valorVenda;
        this.desconto = desconto;
    }
}

public class GabrielinhaUPD {
    private static List<Venda> registroVendas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        do {
            System.out.println("\nCalculadora da Dona Gabrielinha1");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Exibir Registro de Vendas");
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
                    exibirRegistroVendas();
                    break;
                case 4:
                    System.out.println("Saindo... Obrigado por usar a calculadora!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 4);
        
        scanner.close();
    }
    
    public static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade da planta: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();
        
        double total = quantidade * precoUnitario;
        double desconto = (quantidade > 10) ? total * 0.05 : 0;
        double totalComDesconto = total - desconto;
        
        System.out.printf("Preço total da compra: R$ %.2f\n", totalComDesconto);
        if (desconto > 0) {
            System.out.printf("Desconto aplicado: R$ %.2f\n", desconto);
        }
        
        registroVendas.add(new Venda(quantidade, totalComDesconto, desconto));
    }
    
    public static void calcularTroco(Scanner scanner) {
        System.out.print("Digite o valor recebido do cliente: ");
        double valorRecebido = scanner.nextDouble();
        System.out.print("Digite o valor total da compra: ");
        double valorCompra = scanner.nextDouble();
        
        if (valorRecebido < valorCompra) {
            System.out.println("Valor recebido insuficiente!");
        } else {
            double troco = valorRecebido - valorCompra;
            System.out.printf("Troco a ser dado: R$ %.2f\n", troco);
        }
    }
    
    public static void exibirRegistroVendas() {
        if (registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada ainda.");
            return;
        }
        
        System.out.println("\n--- Registro de Vendas ---");
        for (int i = 0; i < registroVendas.size(); i++) {
            Venda venda = registroVendas.get(i);
            System.out.printf("Venda %d: Quantidade: %d, Valor: R$ %.2f, Desconto: R$ %.2f\n", 
                              i + 1, venda.quantidade, venda.valorVenda, venda.desconto);
        }
    }
}