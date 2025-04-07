import java.util.ArrayList;
import java.util.Scanner;

public class Aula03 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double totalVenda;
        double descontoAplicado;
        int quantidade;
        double precoUnitario;

        ArrayList<String> historicoVendas = new ArrayList<>();

        while (true) {
            System.out.println("Menu de Vendas:");
            System.out.println("1. Registrar Venda");
            System.out.println("2. Visualizar Histórico de Vendas");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("1")) {
                System.out.print("Digite a quantidade de plantas: ");
                quantidade = Integer.parseInt(scanner.nextLine());
                System.out.print("Digite o preço unitário da planta: ");
                precoUnitario = Double.parseDouble(scanner.nextLine());
                totalVenda = quantidade * precoUnitario;
                if (quantidade > 10) {
                    descontoAplicado = totalVenda * 0.05; // 5% de desconto
                    totalVenda -= descontoAplicado;
                } else {
                    descontoAplicado = 0;
                }

                String venda = "Venda: Quantidade = " + quantidade + ", Total = R$" + totalVenda + ", Desconto = R$" + descontoAplicado;
                historicoVendas.add(venda);

                System.out.printf("Venda registrada! Total: R$%.2f (Desconto: R$%.2f)\n\n", totalVenda, descontoAplicado);

            } else if (opcao.equals("2")) {
                if (historicoVendas.isEmpty()) {
                    System.out.println("Nenhuma venda registrada ainda.\n");
                } else {
                    System.out.println("Histórico de Vendas:");
                    for (String venda : historicoVendas) {
                        System.out.println(venda);
                    }
                    System.out.println();
                }

            } else if (opcao.equals("3")) {
                break;

            } else {
                System.out.println("Opção inválida! Tente novamente.\n");
            }
        }

        scanner.close();
    }
}
