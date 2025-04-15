import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create addresses first
        Endereco enderecoLoja = new Endereco("PR", "Cascavel", "Centro", "R. Paraná", "123", "Sala 1");
        Endereco enderecoVendedor1 = new Endereco("PR", "Cascavel", "Cascavel Velho", "R. Gilberto Freire", "456", null);
        Endereco enderecoVendedor2 = new Endereco("PR", "Cascavel", "Centro", "R. Paraná", "789", "Apto 2");
        Endereco enderecoCliente1 = new Endereco("PR", "Cascavel", "Centro", "R. Paraná", "101", null);
        Endereco enderecoCliente2 = new Endereco("PR", "Cascavel", "Cascavel Velho", "R. Gilberto Freire", "202", "Casa");

        // Create Loja
        Loja loja = new Loja("My Plant", "Gabrielinha's Garden Center LTDA", 
            "12.345.678/0001-90", enderecoLoja);

        // Create Vendedores
        Vendedor vendedor1 = new Vendedor("João da Silva", 30, enderecoVendedor1, loja, 3000.0);
        Vendedor vendedor2 = new Vendedor("Maria Oliveira", 28, enderecoVendedor2, loja, 5250.0);
        loja.adicionarVendedor(vendedor1);
        loja.adicionarVendedor(vendedor2);

        // Create Clientes
        Cliente cliente1 = new Cliente("Ana Santos", 25, enderecoCliente1);
        Cliente cliente2 = new Cliente("Carlos Pereira", 35, enderecoCliente2);
        loja.adicionarCliente(cliente1);
        loja.adicionarCliente(cliente2);

        // Create Gerente
        Gerente gerente = new Gerente("Pedro Souza", 40, enderecoLoja, loja, 8000.0);
        loja.adicionarGerente(gerente);

        Scanner scanner = new Scanner(System.in);
        int opcaoPrincipal;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Apresentar Loja e Funcionários");
            System.out.println("2 - Apresentar Clientes");
            System.out.println("3 - Calculadora");
            System.out.println("4 - Visualizar Salários dos Vendedores");
            System.out.println("5 - Criar Pedido");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");
            opcaoPrincipal = scanner.nextInt();

            switch (opcaoPrincipal) {
                case 1:
                    System.out.println("\n=== INFORMAÇÕES DA LOJA ===");
                    loja.apresentarLoja();

                    System.out.println("\n=== GERENTE ===");
                    gerente.apresentarse();
                    System.out.printf("Média salarial: R$ %.2f\n", gerente.calcularMedia());
                    System.out.printf("Bônus: R$ %.2f\n", gerente.calcularBonus());

                    System.out.println("\n=== VENDEDORES ===");
                    vendedor1.apresentarse();
                    System.out.printf("Média salarial: R$ %.2f\n", vendedor1.calcularMedia());
                    System.out.printf("Bônus: R$ %.2f\n", vendedor1.calcularBonus());

                    vendedor2.apresentarse();
                    System.out.printf("Média salarial: R$ %.2f\n", vendedor2.calcularMedia());
                    System.out.printf("Bônus: R$ %.2f\n", vendedor2.calcularBonus());
                    break;

                case 2:
                    System.out.println("\n=== CLIENTES ===");
                    cliente1.apresentarse();
                    cliente2.apresentarse();
                    break;

                case 3:
                    menuCalculadora(scanner);
                    break;

                case 4:
                    visualizarSalariosVendedores(vendedor1, vendedor2);
                    break;

                case 5:
                    criarPedido(loja, cliente1, vendedor1);
                    break;

                case 6:
                    System.out.println("Saindo... Obrigado por visitar a My Plant!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcaoPrincipal != 6);

        scanner.close();
    }

    private static void criarPedido(Loja loja, Cliente cliente, Vendedor vendedor) {
        // Criar alguns itens de exemplo
        List<Item> itens = new ArrayList<>();
        itens.add(new Item(1, "Rosa", "Flor", 15.99));
        itens.add(new Item(2, "Vaso Pequeno", "Acessório", 29.90));
        itens.add(new Item(3, "Adubo", "Fertilizante", 12.50));

        // Processar o pedido
        ProcessaPedido processador = new ProcessaPedido();
        Pedido pedido = processador.processar(1001, cliente, vendedor, loja, itens);

        // Mostrar detalhes do pedido
        System.out.println("\n=== DETALHES DO PEDIDO ===");
        pedido.gerarDescricaoVenda();
        System.out.println("Itens do pedido:");

        // Corrigido: usando getItens() que agora está implementado
        for (Item item : pedido.getItens()) {
            item.gerarDescricao();
        }

        System.out.printf("Valor total: R$ %.2f%n", pedido.calcularValorTotal());
    }

    private static void visualizarSalariosVendedores(Vendedor vendedor1, Vendedor vendedor2) {
        System.out.println("\n=== SALÁRIOS RECEBIDOS ===");

        System.out.println("\nVendedor: " + vendedor1.getNome());
        System.out.println("Salários recebidos por mês:");

        List<Double> salarios = vendedor1.getSalarioRecebido();
        List<String> meses = vendedor1.getMesesSalario();

        int tamanho = Math.min(salarios.size(), meses.size());

        for (int i = 0; i < tamanho; i++) {
            System.out.printf("%s: R$ %.2f\n", meses.get(i), salarios.get(i));
        }

        System.out.printf("Média salarial: R$ %.2f\n", vendedor1.calcularMedia());
        System.out.printf("Bônus: R$ %.2f\n", vendedor1.calcularBonus());

        System.out.println("\nVendedor: " + vendedor2.getNome());
        System.out.println("Salários recebidos por mês:");

        salarios = vendedor2.getSalarioRecebido();
        meses = vendedor2.getMesesSalario();
        tamanho = Math.min(salarios.size(), meses.size());

        for (int i = 0; i < tamanho; i++) {
            System.out.printf("%s: R$ %.2f\n", meses.get(i), salarios.get(i));
        }

        System.out.printf("Média salarial: R$ %.2f\n", vendedor2.calcularMedia());
        System.out.printf("Bônus: R$ %.2f\n", vendedor2.calcularBonus());
    }

    private static void menuCalculadora(Scanner scanner) {
        int opcaoCalculadora;

        do {
            System.out.println("\n--- Calculadora da Dona Gabrielinha ---");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcaoCalculadora = scanner.nextInt();

            switch (opcaoCalculadora) {
                case 1:
                    calcularPrecoTotal(scanner);
                    break;
                case 2:
                    calcularTroco(scanner);
                    break;
                case 3:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcaoCalculadora != 3);
    }

    private static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade da planta: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();

        double total = quantidade * precoUnitario;
        System.out.printf("Preço total da compra: R$ %.2f\n", total);
    }

    private static void calcularTroco(Scanner scanner) {
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
}