import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Venda {
    private int quantidade;
    private double precoUnitario;
    private double desconto;
    private double valorTotal;

    public Venda(int quantidade, double precoUnitario) {
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.desconto = 0;
        this.valorTotal = calcularTotal();
    }

    private double calcularTotal() {
        double total = quantidade * precoUnitario;
        if (quantidade > 10) {
            desconto = total * 0.05;
            total -= desconto;
        }
        return total;
    }

    @Override
    public String toString() {
        return "Quantidade: " + quantidade + ", Preço Unitário: R$" + String.format("%.2f", precoUnitario) +
                ", Desconto: R$" + String.format("%.2f", desconto) + ", Total: R$" + String.format("%.2f", valorTotal);
    }
}

class SistemaVendas {
    private List<Venda> vendas;

    public SistemaVendas() {
        this.vendas = new ArrayList<>();
    }

    public void registrarVenda(int quantidade, double precoUnitario) {
        Venda venda = new Venda(quantidade, precoUnitario);
        vendas.add(venda);
        System.out.println("Venda registrada com sucesso!");
        System.out.println(venda);
    }

    public void exibirRegistroVendas() {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada até o momento.");
        } else {
            System.out.println("\n=== REGISTRO DE VENDAS ===");
            for (Venda venda : vendas) {
                System.out.println(venda);
            }
        }
    }
}

public class Calculadora02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaVendas sistema = new SistemaVendas();

        while (true) {
            System.out.println("\n=== Sistema de Vendas da Dona Gabrielinha ===");
            System.out.println("1. Registrar uma venda");
            System.out.println("2. Exibir registro de vendas");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            if (opcao == 1) {
                System.out.print("Informe a quantidade de plantas: ");
                int quantidade = scanner.nextInt();
                System.out.print("Informe o preço unitário da planta: ");
                double preco = scanner.nextDouble();
                sistema.registrarVenda(quantidade, preco);
            } else if (opcao == 2) {
                sistema.exibirRegistroVendas();
            } else if (opcao == 3) {
                System.out.println("Saindo do sistema...");
                break;
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }
}