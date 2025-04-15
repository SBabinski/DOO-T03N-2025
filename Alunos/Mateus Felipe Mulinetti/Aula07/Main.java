import objetos.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    static ArrayList<Loja> lojas = new ArrayList<>();
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Vendedor> vendedores = new ArrayList<>();
    static ArrayList<Item> itens = new ArrayList<>();
    static ArrayList<Pedido> pedidos = new ArrayList<>();

    public static void main(String[] args) {
        criarInformacoes();
        menu();
    }


    public static void menu() {
        int escolha = 0;
        while (escolha != 7) {
            System.out.println("[1] - Adicionar itens \n" + "[2] - Criar Pedido \n" + "[3] - Lojas \n" + "[4] - Clientes \n" +
                    "[5] - Vendedores \n" + "[6] - Pedido \n" + "[7] - Sair");
            escolha = scanner.nextInt();
            switch (escolha) {
                case 1:
                    criarItem(scanner);
                    break;
                case 2:
                    criarPedido(scanner);
                    break;
                case 3:
                    listarLoja();
                    break;
                case 4:
                    listarCliente();
                    break;
                case 5:
                    listarVendedor();
                    break;
                case 6:
                    listarPedido();
                    break;
                case 7:
                    System.out.println("Ate mais!!");
                    break;

                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.  \n ");
                    break;
            }
        }
    }


    public static void criarInformacoes(){
        Endereco enderecoLoja = new Endereco("Pr", "Curitiba", "Centro", "Rua dos mangue",
                1000, "Predio roxo");
        Loja loja = new Loja("My Plant ", "My Plant Comércio LTDA", "40.0289.222/0001-69",
                enderecoLoja);
        lojas.add(loja);
        System.out.println("Loja criada com sucesso!");


        Endereco enderecoCliente1 = new Endereco("Pr", "Curitiba", "Centro", "Rua Jacobe",
                1000, "Predio roxo");
        Cliente cliente1 = new Cliente("Lucas", 13, enderecoCliente1);
        clientes.add(cliente1);
        System.out.println("Cliente Resgistrado com sucesso!");
        Endereco enderecoCliente2 = new Endereco("Pr", "Curitiba", "Centro", "Rua dos mangue",
                1032, "Predio Vermelho");
        Cliente cliente2 = new Cliente("Mateus", 25, enderecoCliente2);
        clientes.add(cliente2);
        System.out.println("Cliente Resgistrado com sucesso!");


        Endereco enderecoGerente1 = new Endereco("Pr", "Cascavel", "Centro", "Av Paraná",
                1000, "casa azul");
        Gerente gerente1 = new Gerente("Fernanda", 65, enderecoGerente1, "My Plant", 4000,
                new double[]{4000.0, 4600.0, 5000.0});
        vendedores.add(gerente1);
        System.out.println("Gerente resgistrado com sucesso!");
        Endereco enderecoGerente2 = new Endereco("Pr", "Cascavel", "Centro", "Av Paraná", 
                1000, "casa azul");
        Gerente gerente2 = new Gerente("Pedro", 70, enderecoGerente2, "My Plant", 5000,
                new double[]{4000.0, 4600.0, 5000.0});
        vendedores.add(gerente2);
        System.out.println("Gerente resgistrado com sucesso!");



        Endereco enderecoVendedor1 = new Endereco("Pr", "Curitiba", "Centro", "Rua dos mangue",
                1002, "Predio roxo");
        Vendedor vendedor1 = new Vendedor("Jacinto", 13, enderecoVendedor1, "My Plant", 2000,
                new double[]{3000.0, 3200.0, 3300.0});
        vendedores.add(vendedor1);
        System.out.println("Vendedor Resgistrado com sucesso!");

        Endereco enderecoVendedor2 = new Endereco("Pr", "Curitiba", "Centro", "Rua dos mananciais",
                1002, "Predio roxo");
        Vendedor vendedor2 = new Vendedor("Joao", 13, enderecoVendedor2, "My Plant", 3000,
                new double[]{3000.0, 3200.0, 3300.0});
        vendedores.add(vendedor2);
        System.out.println("Vendedor Resgistrado com sucesso!");


    }


    public static void criarItem(Scanner scanner) {
        System.out.println("Criar Item");
        System.out.print("Nome: ");
        String nome = scanner.next();
        System.out.print("Tipo: ");
        String tipo = scanner.next();
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();

        int id = itens.size() + 1;
        Item item = new Item(id, nome, tipo, valor);
        itens.add(item);
        System.out.println("Item criado com sucesso!");
    }

    public static void criarPedido(Scanner scanner) {
        System.out.println("Criar Pedido");

        System.out.println("Selecione o Cliente:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println("[ " + i + " ] " + clientes.get(i).nome);
        }
        int clienteEscolhido = scanner.nextInt();

        System.out.println("Selecione o Vendedor:");
        for (int i = 0; i < vendedores.size(); i++) {
            System.out.println("[ " + i + " ] " + vendedores.get(i).nome);
        }
        int vendedorEscolhido = scanner.nextInt();


        System.out.println("Selecione os Itens:");
        for (int i = 0; i < itens.size(); i++) {
            System.out.println("[ " + i + " ] " + itens.get(i).nome);
        }
        int[] itensEscolhidosIndices = new int[itens.size()];
        double[] itensEscolhidosValores = new double[itens.size()];
        Item[] itensEscolhidos = new Item[itensEscolhidosIndices.length];

        for (int i = 0; i < itensEscolhidosIndices.length; i++) {
            System.out.print("Selecione o item " + (i + 1) + ": ");
            itensEscolhidosIndices[i] = scanner.nextInt();
            itensEscolhidos[i] = itens.get(itensEscolhidosIndices[i]);
            itensEscolhidosValores[i] = itens.get(i).valor;

        }


        Date agora = new Date();
        Date dataPagamento = new Date();
        Date vencimento = new Date(agora.getTime() + (2 * 24 * 60 * 60 * 1000));
        int id = 0;
        id += 1;
        Pedido pedido = new Pedido(id, agora, dataPagamento, vencimento, clientes.get(clienteEscolhido),
                vendedores.get(vendedorEscolhido), lojas.get(0), itensEscolhidos, itensEscolhidosValores);
        pedidos.add(pedido);
        
        System.out.println("Pedido Realizado com sucesso!");
    }

    public static void listarLoja() {
        System.out.println("[ Lojas");
        for (Loja loja : lojas) {
            loja.apresentarse();
        }
        System.out.println("]");
    }

    public static void listarVendedor() {
        System.out.println("[ Listar ]Vendedores");
        for (Vendedor vendedor : vendedores) {
            vendedor.apresentarVendedor();
        }
        System.out.println("]");
    }


    public static void listarPedido() {
        System.out.println("[ Pedidos");
        for (Pedido pedido : pedidos) {
            pedido.venda();
        }
        System.out.println("]");
    }

    public static void listarCliente() {
        System.out.println("[ Clientes");
        for (Cliente cliente : clientes) {
            cliente.apresentarCliente();
        }
        System.out.println("]");
    }

}