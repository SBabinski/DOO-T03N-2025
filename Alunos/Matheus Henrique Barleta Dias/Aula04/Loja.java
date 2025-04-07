import java.util.*;

public class Loja {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RegistroVendas registroVendas = new RegistroVendas();
        int opcao;

        do {
            System.out.println("\n[1] Registrar Venda");
            System.out.println("[2] Histórico de Vendas");
            System.out.println("[3] Consultar Vendas por Dia");
            System.out.println("[4] Consultar Vendas por Mês");
            System.out.println("[5] Sair");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    registroVendas.registrarVenda(sc);
                    break;
                case 2:
                    registroVendas.exibirHistorico();
                    break;
                case 3:
                    registroVendas.consultarVendasPorDia(sc);
                    break;
                case 4:
                    registroVendas.consultarVendasPorMes(sc);
                    break;
                case 5:
                    System.out.println("Você saiu.");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
        sc.close();
    }
}
