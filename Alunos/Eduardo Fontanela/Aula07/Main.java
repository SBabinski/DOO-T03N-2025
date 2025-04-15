import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Endereco enderecoCliente = new Endereco("SP", "São Paulo", "Centro", 100, "Apto 21");
        Cliente cliente = new Cliente("Julia", 28, enderecoCliente);

        Endereco enderecoVendedor = new Endereco("SP", "São Paulo", "Bela Vista", 321, "Loja 1");
        Vendedor vendedor = new Vendedor("Carlos", 32, enderecoVendedor, "Loja 01");

        Endereco enderecoGerente = new Endereco("SP", "São Paulo", "Moema", 45, "Sala 3");
        double[] salarios = {5000, 5200, 5300};
        Gerente gerente = new Gerente("Renata", 40, enderecoGerente, "Loja 01", 5000, salarios);

        int opcao;
        do {
            System.out.println("\n--- MENU MY PLANT ---");
            System.out.println("1. Criar Pedido");
            System.out.println("2. Mostrar dados do Gerente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    criarPedido(cliente, vendedor);
                    break;

                case 2:
                    gerente.apresentarSe();
                    System.out.println("Média Salarial: R$" + gerente.calcularMedia());
                    System.out.println("Bônus: R$" + gerente.calcularBonus());
                    break;

                case 0:
                    System.out.println("Encerrando o programa.");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }

    private static void criarPedido(Cliente cliente, Vendedor vendedor) {
        List<Item> itens = List.of(
            new Item(1, "Planta Espada de São Jorge", "Planta", 45.0),
            new Item(2, "Vaso de Cerâmica", "Acessório", 30.0)
        );

        Date dataCriacao = new Date();
        Date dataPagamento = new Date();
        Date dataVencimentoReserva = new Date(dataCriacao.getTime() + 86400000); // +1 dia

        ProcessaPedido processador = new ProcessaPedido();
        Pedido pedido = processador.processar(
            new Random().nextInt(1000), 
            dataCriacao,
            dataPagamento,
            dataVencimentoReserva,
            cliente,
            vendedor,
            "Loja 01",
            itens
        );

        if (pedido != null) {
            pedido.gerarDescricaoVenda();
        } else {
            System.out.println("Não foi possível processar o pedido.");
        }
    }
}
