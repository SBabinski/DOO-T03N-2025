package Alunos.Adriel.Aula04;
import java.util.*;

public class Gabrielinha {
    private static List<Venda> registroVendas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        do {
            System.out.println("\nCalculadora da Dona Gabrielinha");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Exibir Registro de Vendas");
            System.out.println("[4] - Consultar vendas por dia");
            System.out.println("[5] - Consultar vendas por mês");
            System.out.println("[6] - Sair");
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
                    consultarVendasPorDia(scanner);
                    break;
                case 5:
                    consultarVendasPorMes(scanner);
                    break;
                case 6:
                    System.out.println("Saindo... Obrigado por usar a calculadora!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 6);
        
        scanner.close();
    }
    
    public static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade da planta: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();
        System.out.print("Digite o dia da venda: ");
        int dia = scanner.nextInt();
        System.out.print("Digite o mês da venda: ");
        int mes = scanner.nextInt();
        
        double total = quantidade * precoUnitario;
        double desconto = (quantidade > 10) ? total * 0.05 : 0;
        double totalComDesconto = total - desconto;
        
        System.out.printf("Preço total da compra: R$ %.2f\n", totalComDesconto);
        if (desconto > 0) {
            System.out.printf("Desconto aplicado: R$ %.2f\n", desconto);
        }
        
        registroVendas.add(new Venda(quantidade, totalComDesconto, desconto, dia, mes));
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
        for (Venda venda : registroVendas) {
            System.out.printf("Dia %d/%d - Quantidade: %d, Valor: R$ %.2f, Desconto: R$ %.2f\n", 
                              venda.dia, venda.mes, venda.quantidade, venda.valorVenda, venda.desconto);
        }
    }
    
    public static void consultarVendasPorDia(Scanner scanner) {
        System.out.print("Digite o dia da venda: ");
        int dia = scanner.nextInt();
        System.out.print("Digite o mês da venda: ");
        int mes = scanner.nextInt();
        
        int totalVendas = registroVendas.stream()
            .filter(venda -> venda.dia == dia && venda.mes == mes)
            .mapToInt(venda -> venda.quantidade)
            .sum();
        
        System.out.println("Total de vendas no dia " + dia + "/" + mes + ": " + totalVendas);
    }
    
    public static void consultarVendasPorMes(Scanner scanner) {
        System.out.print("Digite o mês da venda: ");
        int mes = scanner.nextInt();
        
        int totalVendas = registroVendas.stream()
            .filter(venda -> venda.mes == mes)
            .mapToInt(venda -> venda.quantidade)
            .sum();
        
        System.out.println("Total de vendas no mês " + mes + ": " + totalVendas);
    }
}
