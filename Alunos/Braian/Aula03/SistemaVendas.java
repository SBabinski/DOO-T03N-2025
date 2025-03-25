package Alunos.Braian.Aula03;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Venda {
    private int quantidade;
    private double precoUnitario;
    private double valorTotal;
    private double desconto;

    public Venda(int quantidade, double precoUnitario) {
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        calcularPrecoTotal();
    }

    private void calcularPrecoTotal() {
        valorTotal = quantidade * precoUnitario;
        desconto = (quantidade > 10) ? valorTotal * 0.05 : 0;
        valorTotal -= desconto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getDesconto() {
        return desconto;
    }
}

class RegistroVendas {
    private List<Venda> vendas;

    public RegistroVendas() {
        this.vendas = new ArrayList<>();
    }

    public void adicionarVenda(Venda venda) {
        vendas.add(venda);
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void exibirRegistro() {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada ainda.");
            return;
        }
        System.out.println("Registro de Vendas:");
        for (Venda venda : vendas) {
            System.out.println("Quantidade: " + venda.getQuantidade() +
                    " | Preço Unitário: R$ " + venda.getPrecoUnitario() +
                    " | Valor Total: R$ " + venda.getValorTotal() +
                    " | Desconto: R$ " + venda.getDesconto());
        }
    }
}

public class SistemaVendas {
    private static Scanner scanner = new Scanner(System.in);
    private static RegistroVendas registroVendas = new RegistroVendas();

    public static void main(String[] args) {
        int opcao = 0;
        while (opcao != 4) {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    realizarVenda();
                    break;
                case 2:
                    calcularTroco();
                    break;
                case 3:
                    registroVendas.exibirRegistro();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\nOlá, Dona Gabrielinha!");
        System.out.println("Selecione uma opção:");
        System.out.println("1 - Registrar Venda");
        System.out.println("2 - Calcular Troco");
        System.out.println("3 - Exibir Registro de Vendas");
        System.out.println("4 - Sair");
    }

    private static void realizarVenda() {
        System.out.print("Digite a quantidade de plantas: ");
        int quantidade = scanner.nextInt();

        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();

        Venda venda = new Venda(quantidade, precoUnitario);
        registroVendas.adicionarVenda(venda);

        System.out.println("Valor total a pagar: R$ " + venda.getValorTotal());
        if (venda.getDesconto() > 0) {
            System.out.println("Desconto de 5% aplicado: R$ " + venda.getDesconto());
        }
    }

    private static void calcularTroco() {
        System.out.print("Digite o valor recebido do cliente: ");
        double valorRecebido = scanner.nextDouble();

        System.out.print("Digite o valor total da compra: ");
        double valorTotal = scanner.nextDouble();

        double troco = valorRecebido - valorTotal;
        System.out.println("O troco a ser dado é: R$ " + troco);
    }
}