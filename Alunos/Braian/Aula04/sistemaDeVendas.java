package Alunos.Braian.Aula04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transacao {
    private int quantidade;
    private double precoUnitario;
    private double valorTotal;
    private double desconto;
    private String data;

    public Transacao(int quantidade, double precoUnitario, String data) {
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.data = data;
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

    public String getData() {
        return data;
    }
}

class SistemaDeVendas {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Transacao> transacoes = new ArrayList<>();

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int opcao = 0;
        while (opcao != 6) {
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
                    exibirRegistroVendas();
                    break;
                case 4:
                    quantidadeVendasDiarias();
                    break;
                case 5:
                    quantidadeVendasTotais();
                    break;
                case 6:
                    System.out.println("Encerrando o programa...");
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
        System.out.println("4 - Quantidade de Vendas Diárias");
        System.out.println("5 - Quantidade de Vendas Totais");
        System.out.println("6 - Sair");
    }

    private static void realizarVenda() {
        System.out.print("Digite a quantidade de plantas: ");
        int quantidade = scanner.nextInt();

        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();

        scanner.nextLine();
        System.out.print("Digite a data da venda (formato dd/MM/yyyy): ");
        String data = scanner.nextLine();

        Transacao transacao = new Transacao(quantidade, precoUnitario, data);
        transacoes.add(transacao);

        System.out.println("Valor total a pagar: R$ " + transacao.getValorTotal());
        if (transacao.getDesconto() > 0) {
            System.out.println("Desconto de 5% aplicado: R$ " + transacao.getDesconto());
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

    private static void exibirRegistroVendas() {
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma venda registrada ainda.");
            return;
        }
        System.out.println("Registro de Vendas:");
        for (Transacao transacao : transacoes) {
            System.out.println("Quantidade: " + transacao.getQuantidade() +
                    " | Preço Unitário: R$ " + transacao.getPrecoUnitario() +
                    " | Valor Total: R$ " + transacao.getValorTotal() +
                    " | Desconto: R$ " + transacao.getDesconto() +
                    " | Data: " + transacao.getData());
        }
    }

    private static void quantidadeVendasDiarias() {
        scanner.nextLine();
        System.out.print("Digite a data que deseja verificar (formato dd/MM/yyyy): ");
        String data = scanner.nextLine();
        int quantidade = 0;
        for (Transacao transacao : transacoes) {
            if (transacao.getData().equals(data)) {
                quantidade += transacao.getQuantidade();
            }
        }
        System.out.println("Quantidade de vendas no dia " + data + ": " + quantidade);
    }

    private static void quantidadeVendasTotais() {
        int quantidade = 0;
        for (Transacao transacao : transacoes) {
            quantidade += transacao.getQuantidade();
        }
        System.out.println("Quantidade de vendas totais: " + quantidade);
    }
}
