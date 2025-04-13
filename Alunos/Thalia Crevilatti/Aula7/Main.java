import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        do {
            mostrarMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer
            
            switch (opcao) {
                case 1:
                    criarPedidoManual(scanner);
                    break;
                case 2:
                    criarPedidoFake();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 3);
    }

    public static void mostrarMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Criar Pedido");
        System.out.println("2. Criar Pedido (Fake)");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void criarPedidoManual(Scanner scanner) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Usaremos esse formato para capturar datas
            
            // Leitura dos dados
            System.out.println("\n--- Criar Pedido Manual ---");
            
            System.out.print("Nome do item: ");
            String nomeItem = scanner.nextLine();
            
            double preco = lerDouble(scanner, "Preço do item: ");  // Leitura do preço com verificação
            int numeroItem = lerInt(scanner, "Número do item: ");  // Leitura do número do item com verificação
            String complementoItem = lerString(scanner, "Complemento do item: ");  // Leitura do complemento do item
        
            System.out.print("Estado do item: ");
            String estadoItem = scanner.nextLine();
            System.out.print("Cidade do item: ");
            String cidadeItem = scanner.nextLine();
            System.out.print("Bairro do item: ");
            String bairroItem = scanner.nextLine();
            
            int numeroEntrega = lerInt(scanner, "Número de entrega: ");  
            String complementoEntrega = lerString(scanner, "Complemento de entrega: ");  
            System.out.print("Nome do gerente: ");
            String nomeGerente = scanner.nextLine();
            int idadeGerente = lerInt(scanner, "Idade do gerente: ");  
            System.out.print("Loja do gerente: ");
            String lojaGerente = scanner.nextLine();
            double salarioBaseGerente = lerDouble(scanner, "Salário base do gerente: ");  
            
            Item item = new Item(1, nomeItem, "Geral", preco);  // Usando ID fixo para exemplo
            Endereco enderecoEntrega = new Endereco("PR", "CAS", "UNI", numeroEntrega, complementoEntrega);
            Gerente gerente = new Gerente(nomeGerente, idadeGerente, lojaGerente, "Cidade", "Bairro", "Estado", salarioBaseGerente);

            Date dataCriacao = new Date();
            Date dataPagamento = new Date();
            Date dataVencimentoReserva = new Date(System.currentTimeMillis() + 86400000);  

            Pedido pedido = new Pedido(1, dataCriacao, dataPagamento, dataVencimentoReserva, "Cliente A", "Vendedor X", "Loja Central", new Item[]{item});
            pedido.gerarDescricaoVenda();

            ProcessaPedido processador = new ProcessaPedido();
            processador.processar(1, "Cliente A", "Vendedor X", "Loja Central", new Item[]{item});
        } catch (Exception e) {
            System.out.println("Erro ao processar os dados. Tente novamente.");
            e.printStackTrace();
        }
    }

    public static double lerDouble(Scanner scanner, String mensagem) {
        double valor = -1;
        while (valor < 0) {
            System.out.print(mensagem);
            try {
                valor = scanner.nextDouble();
                scanner.nextLine();  // Limpar o buffer
                if (valor < 0) {
                    System.out.println("O valor deve ser positivo!");
                }
            } catch (Exception e) {
                System.out.println("Por favor, insira um valor numérico válido.");
                scanner.nextLine(); 
            }
        }
        return valor;
    }

    public static int lerInt(Scanner scanner, String mensagem) {
        int valor = -1;
        while (valor < 0) {
            System.out.print(mensagem);
            try {
                valor = scanner.nextInt();
                scanner.nextLine();  // Limpar o buffer
                if (valor < 0) {
                    System.out.println("O valor deve ser positivo!");
                }
            } catch (Exception e) {
                System.out.println("Por favor, insira um valor numérico válido.");
                scanner.nextLine(); 
            }
        }
        return valor;
    }

    public static String lerString(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public static void criarPedidoFake() {
        System.out.println("\n--- Criar Pedido Fake ---");

        Item item1 = new Item(1, "Cadeira", "Mobiliário", 300.0);
        Item item2 = new Item(2, "Mesa", "Mobiliário", 500.0);
        Item[] itens = {item1, item2};

        String cliente = "Cliente Fictício";
        String vendedor = "Vendedor Fictício";
        String loja = "Loja Fake";

        Date dataCriacao = new Date(); 
        Date dataPagamento = new Date(); 
        Date dataVencimentoReserva = new Date(System.currentTimeMillis() + 86400000); 
        Pedido pedidoFake = new Pedido(1, dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja, itens);

        pedidoFake.gerarDescricaoVenda();

        ProcessaPedido processador = new ProcessaPedido();
        processador.processar(1, cliente, vendedor, loja, itens);
    }
}