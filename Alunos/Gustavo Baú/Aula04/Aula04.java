import java.util.ArrayList;
import java.util.Scanner;

public class Aula04 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double totalVenda;
        double descontoAplicado;
        int quantidade;
        double precoUnitario;
        String dataVenda;

        ArrayList<String> historicoVendas = new ArrayList<>();
        ArrayList<String> datasVendas = new ArrayList<>();
        ArrayList<Integer> quantidadesVendas = new ArrayList<>();

        while (true) {
            System.out.println("Menu de Vendas:");
            System.out.println("1. Registrar Venda");
            System.out.println("2. Visualizar Histórico de Vendas");
            System.out.println("3. Consultar Vendas por Mês e Dia");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("1")) {
                System.out.print("Digite a quantidade de plantas: ");
                quantidade = Integer.parseInt(scanner.nextLine());
                System.out.print("Digite o preço unitário da planta: ");
                precoUnitario = Double.parseDouble(scanner.nextLine());
                System.out.print("Digite a data da venda (formato MM/DD): ");
                dataVenda = scanner.nextLine();
                totalVenda = quantidade * precoUnitario;

                if (quantidade > 10) {
                    descontoAplicado = totalVenda * 0.05;
                    totalVenda -= descontoAplicado;
                } else {
                    descontoAplicado = 0;
                }

                String venda = "Venda: Quantidade = " + quantidade + ", Total = R$" + totalVenda + ", Desconto = R$" + descontoAplicado;
                historicoVendas.add(venda);
                datasVendas.add(dataVenda);
                quantidadesVendas.add(quantidade);

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
                System.out.print("Digite o mês e o dia para consulta (formato MM/DD): ");
                String dataConsulta = scanner.nextLine();

                boolean encontrouVenda = false;
                int totalVendasDia = 0;

                for (int i = 0; i < datasVendas.size(); i++) {
                    if (datasVendas.get(i).equals(dataConsulta)) {
                        totalVendasDia += quantidadesVendas.get(i);
                        encontrouVenda = true;
                    }
                }

                if (encontrouVenda) {
                    System.out.println("Total de vendas no dia " + dataConsulta + ": " + totalVendasDia + " plantas.");
                } else {
                    System.out.println("Nenhuma venda registrada para a data " + dataConsulta + ".\n");
                }

            } else if (opcao.equals("4")) {
                break;

            } else {
                System.out.println("Opção inválida! Tente novamente.\n");
            }
        }

        scanner.close();
    }
}
