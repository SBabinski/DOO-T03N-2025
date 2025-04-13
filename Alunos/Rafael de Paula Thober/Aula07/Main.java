import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Pedido> pedidos = new ArrayList<>();

        while (true) {
            System.out.println("\n=== My Plant - Sistema ===");
            System.out.println("[1] Criar pedido (dados fakes)");
            System.out.println("[0] Sair");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();

            if (opcao == 0) break;

            if (opcao == 1) {
                // Endereço
                Endereco endereco = new Endereco();
                endereco.estado = "PR";
                endereco.cidade = "Curitiba";
                endereco.bairro = "Centro";
                endereco.rua = "Rua das Plantas";
                endereco.numero = "123";

                // Cliente e vendedor
                Cliente cliente = new Cliente("Julia", 34, endereco);
                Vendedor vendedor = new Vendedor("Carlos", 29, endereco, "My Plant", 2500, new double[]{2500, 2600, 2550});

                // Itens
                List<Item> itens = new ArrayList<>();
                itens.add(new Item(1, "Orquídea", "Flor", 79.90));
                itens.add(new Item(2, "Vaso Cerâmica", "Acessório", 45.00));

                // Pedido
                Date hoje = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(hoje);
                cal.add(Calendar.DATE, 2); // vencimento em 2 dias
                Date vencimento = cal.getTime();

                ProcessaPedido pp = new ProcessaPedido();
                Pedido pedido = pp.processar(1, hoje, vencimento, cliente, vendedor, "My Plant", itens);
                pedidos.add(pedido);
                pedido.gerarDescricaoVenda();
            }
        }

        scanner.close();
    }
}
