import java.util.*;

public class Calculadora {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, Integer> vendasPorDia = new HashMap<>();
    static List<Pedido> pedidos = new ArrayList<>();

    public static void main(String[] args) {
        Vendedor v1 = new Vendedor("Gabriel", 30, "My Plant", 2000f);
        Cliente c1 = new Cliente("Lucas", 25);
        Loja loja = new Loja("My Plant", "12.345.678/0001-99", new Vendedor[]{v1}, new Cliente[]{c1});

        int opcao = 0;
        while (opcao != 9) {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Calcular conta");
            System.out.println("2. Calcular troco");
            System.out.println("3. Registrar venda");
            System.out.println("4. Mostrar registro de vendas");
            System.out.println("5. Apresentar Loja");
            System.out.println("6. Apresentar Vendedor");
            System.out.println("7. Apresentar Cliente");
            System.out.println("8. Calcular Média e Bônus do Vendedor");
            System.out.println("9. Sair");
            System.out.println("10. Criar Pedido (dados fictícios)");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Digite um número válido!");
                scanner.next();
                continue;
            }

            switch (opcao) {
                case 1 -> conta();
                case 2 -> troco();
                case 3 -> registrarVendas();
                case 4 -> mostrarVendas();
                case 5 -> {
                    loja.apresentarse();
                    loja.contarVendedores();
                    loja.contarClientes();
                }
                case 6 -> v1.apresentarse();
                case 7 -> c1.apresentarse(); // Faltava no original
                case 8 -> {
                    v1.calcularMedia();
                    v1.calcularBonus();
                }
                case 9 -> System.out.println("Saindo do sistema...");
                case 10 -> criarPedido(c1, v1, loja); // Novo item do menu
                default -> System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }

    public static void conta() {
        try {
            System.out.print("Quantas plantas você pegou? ");
            int plantas = scanner.nextInt();

            System.out.print("Qual o preço da planta? ");
            float preco = scanner.nextFloat();

            float total = plantas * preco;

            if (plantas > 10) {
                float desconto = total * 0.05f;
                total -= desconto;
                System.out.println("Você ganhou 5% de desconto!");
            }

            System.out.printf("O total é: R$ %.2f\n", total);
        } catch (InputMismatchException e) {
            System.out.println("Digite números válidos!");
            scanner.next();
        }
    }

    public static void troco() {
        try {
            System.out.print("Qual o valor da compra? ");
            float valor = scanner.nextFloat();

            System.out.print("Quanto você pagou? ");
            float pago = scanner.nextFloat();

            if (pago < valor) {
                System.out.println("Valor pago insuficiente.");
            } else {
                float troco = pago - valor;
                System.out.printf("O troco é: R$ %.2f\n", troco);
            }
        } catch (InputMismatchException e) {
            System.out.println("Digite números válidos!");
            scanner.next();
        }
    }

    public static void registrarVendas() {
        try {
            System.out.print("Informe o dia da venda: ");
            int dia = scanner.nextInt();

            System.out.print("Informe o mês da venda: ");
            int mes = scanner.nextInt();

            System.out.print("Informe a quantidade de vendas: ");
            int quant = scanner.nextInt();

            String chave = dia + "-" + mes;
            vendasPorDia.put(chave, vendasPorDia.getOrDefault(chave, 0) + quant);

            System.out.println("Venda registrada para " + chave);
        } catch (InputMismatchException e) {
            System.out.println("Digite números válidos!");
            scanner.next();
        }
    }

    public static void mostrarVendas() {
        if (vendasPorDia.isEmpty()) {
            System.out.println("Nenhuma venda registrada");
            return;
        }

        System.out.println("Registro de Vendas (Dia-Mês: Quantidade):");
        for (Map.Entry<String, Integer> entry : vendasPorDia.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue() + " vendas");
        }

        System.out.print("\nDeseja consultar um dia específico? (s/n): ");
        char opcao = scanner.next().toLowerCase().charAt(0);
        if (opcao == 's') {
            try {
                System.out.print("Informe o dia: ");
                int dia = scanner.nextInt();
                System.out.print("Informe o mês: ");
                int mes = scanner.nextInt();

                String chave = dia + "-" + mes;
                int vendas = vendasPorDia.getOrDefault(chave, 0);

                if (vendas > 0) {
                    System.out.println("Vendas no dia " + dia + "/" + mes + ": " + vendas);
                } else {
                    System.out.println("Nenhuma venda registrada para " + dia + "/" + mes + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite números válidos!");
                scanner.next();
            }
        }
    }

    public static void criarPedido(Cliente cliente, Vendedor vendedor, Loja loja) {
        Item[] itens = {
                new Item(1, "Planta Espada-de-São-Jorge", "Planta Ornamental", 35.0f),
                new Item(2, "Vaso Cerâmico", "Vaso", 45.0f)
        };

        Date dataCriacao = new Date();
        Date dataVencimento = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)); // +1 dia

        ProcessaPedido processador = new ProcessaPedido();
        Pedido novoPedido = processador.processar(1, cliente, vendedor, loja, itens, dataVencimento);

        pedidos.add(novoPedido);

        System.out.println("Pedido criado com sucesso:");
        novoPedido.gerarDescricaoVenda();
    }
}
