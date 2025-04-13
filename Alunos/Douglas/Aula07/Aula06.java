import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Aula06 {
    static ArrayList<Loja> lojas = new ArrayList<>();
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Vendedor> vendedores = new ArrayList<>();
    static ArrayList<Item> itens = new ArrayList<>();
    static ArrayList<Pedido> pedidos = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        criarLoja();
        while (true) {
            menu(scanner);
        }
    }

    public static void menu(Scanner scanner) {
        System.out.println("Escolha uma opção:");
        System.out.println("1. Criar Loja");
        System.out.println("2. Criar Endereço");
        System.out.println("3. Criar Cliente");
        System.out.println("4. Criar Vendedor");
        System.out.println("5. Criar Gerente");
        System.out.println("6. Criar Item");
        System.out.println("7. Criar Pedido");
        System.out.println("8. Listar Lojas");
        System.out.println("9. Listar Clientes");
        System.out.println("10. Listar Vendedores");
        System.out.println("11. Listar Itens");
        System.out.println("12. Listar Pedidos");
        System.out.println("0. Sair");

        int opcao = scanner.nextInt();
        scanner.nextLine();
        
        if (opcao == 1) {
            criarLoja();
        } else if (opcao == 2) {
            criarEndereco(scanner);
        } else if (opcao == 3) {
            criarCliente(scanner);
        } else if (opcao == 4) {
            criarVendedor(scanner);
        } else if (opcao == 5) {
            criarGerente(scanner);
        } else if (opcao == 6) {
            criarItem(scanner);
        } else if (opcao == 7) {
            criarPedido(scanner);
        } else if (opcao == 8) {
            listarLojas();
        } else if (opcao == 9) {
            listarClientes();
        } else if (opcao == 10) {
            listarVendedores();
        } else if (opcao == 11) {
            listarItens();
        } else if (opcao == 12) {
            listarPedidos();
        } else if (opcao == 0) {
            System.out.println("Saindo...");
            System.exit(0);
        }
    }

    public static void criarLoja() {
        Endereco enderecoLoja = new Endereco("PR", "Cascavel", "Centro", "Av. Brasil", 1000, "Loja 01");
        Loja loja = new Loja("My Plant", "My Plant LTDA", "12.345.678/0001-90", enderecoLoja);
        lojas.add(loja);
        System.out.println("Loja criada com sucesso!");
    }

    public static void criarEndereco(Scanner scanner) {
        System.out.println("Criar Endereço");
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Bairro: ");
        String bairro = scanner.nextLine();
        System.out.print("Rua: ");
        String rua = scanner.nextLine();
        System.out.print("Número: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Complemento: ");
        String complemento = scanner.nextLine();

        Endereco endereco = new Endereco(estado, cidade, bairro, rua, numero, complemento);
        System.out.println("Endereço criado com sucesso!");
    }

    public static void criarCliente(Scanner scanner) {
        System.out.println("Criar Cliente");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Selecione o Endereço:");
        for (int i = 0; i < lojas.size(); i++) {
            System.out.println(i + ". " + lojas.get(i).endereco.rua);
        }
        int enderecoEscolhido = scanner.nextInt();
        scanner.nextLine();
        
        Endereco enderecoCliente = lojas.get(enderecoEscolhido).endereco;
        Cliente cliente = new Cliente(nome, idade, enderecoCliente);
        clientes.add(cliente);
        System.out.println("Cliente criado com sucesso!");
    }

    public static void criarVendedor(Scanner scanner) {
        System.out.println("Criar Vendedor");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Salário Base: ");
        double salarioBase = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.println("Selecione o Endereço:");
        for (int i = 0; i < lojas.size(); i++) {
            System.out.println(i + ". " + lojas.get(i).endereco.rua);
        }
        int enderecoEscolhido = scanner.nextInt();
        scanner.nextLine();
        
        Endereco enderecoVendedor = lojas.get(enderecoEscolhido).endereco;
        Vendedor vendedor = new Vendedor(nome, idade, enderecoVendedor, "My Plant", salarioBase, new double[]{3000.0, 3200.0, 3300.0});
        vendedores.add(vendedor);
        System.out.println("Vendedor criado com sucesso!");
    }

    public static void criarGerente(Scanner scanner) {
        System.out.println("Criar Gerente");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Salário Base: ");
        double salarioBase = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.println("Selecione o Endereço:");
        for (int i = 0; i < lojas.size(); i++) {
            System.out.println(i + ". " + lojas.get(i).endereco.rua);
        }
        int enderecoEscolhido = scanner.nextInt();
        scanner.nextLine();
        
        Endereco enderecoGerente = lojas.get(enderecoEscolhido).endereco;
        Gerente gerente = new Gerente(nome, idade, enderecoGerente, "My Plant", salarioBase, new double[]{6000.0, 6100.0, 6200.0});
        vendedores.add(gerente);
        System.out.println("Gerente criado com sucesso!");
    }

    public static void criarItem(Scanner scanner) {
        System.out.println("Criar Item");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();
    
        int id = itens.size() + 1;
        Item item = new Item(id, nome, tipo, valor);
        itens.add(item);
        System.out.println("Item criado com sucesso!");
    }
    
    public static void criarPedido(Scanner scanner) {
        System.out.println("Criar Pedido");
    
        System.out.println("Selecione o Cliente:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + ". " + clientes.get(i).nome);
        }
        int clienteEscolhido = scanner.nextInt();
        scanner.nextLine();
    
        System.out.println("Selecione o Vendedor:");
        for (int i = 0; i < vendedores.size(); i++) {
            System.out.println(i + ". " + vendedores.get(i).nome);
        }
        int vendedorEscolhido = scanner.nextInt();
        scanner.nextLine();
    
        System.out.println("Selecione os Itens:");
        for (int i = 0; i < itens.size(); i++) {
            System.out.println(i + ". " + itens.get(i).nome);
        }
        int[] itensEscolhidosIndices = new int[scanner.nextInt()];
        scanner.nextLine();
    
        Item[] itensEscolhidos = new Item[itensEscolhidosIndices.length];
        for (int i = 0; i < itensEscolhidosIndices.length; i++) {
            System.out.print("Selecione o item " + (i + 1) + ": ");
            itensEscolhidosIndices[i] = scanner.nextInt();
            scanner.nextLine();
            itensEscolhidos[i] = itens.get(itensEscolhidosIndices[i]);
        }
    
        Date agora = new Date();
        Date dataPagamento = new Date();
        Date vencimento = new Date(agora.getTime() + (2 * 24 * 60 * 60 * 1000));
    
        Pedido pedido = new Pedido(1, agora, dataPagamento, vencimento, clientes.get(clienteEscolhido), vendedores.get(vendedorEscolhido), lojas.get(0), itensEscolhidos);
        pedidos.add(pedido);
    
        System.out.println("Pedido criado com sucesso!");
    }
    

    public static void listarLojas() {
        System.out.println("Listar Lojas");
        for (Loja loja : lojas) {
            loja.apresentarse();
        }
    }

    public static void listarClientes() {
        System.out.println("Listar Clientes");
        for (Cliente cliente : clientes) {
            cliente.apresentarse();
        }
    }

    public static void listarVendedores() {
        System.out.println("Listar Vendedores");
        for (Vendedor vendedor : vendedores) {
            vendedor.apresentarse();
        }
    }

    public static void listarItens() {
        System.out.println("Listar Itens");
        for (Item item : itens) {
            item.gerarDescricao();
        }
    }

    public static void listarPedidos() {
        System.out.println("Listar Pedidos");
        for (Pedido pedido : pedidos) {
            pedido.gerarDescricaoVenda();
        }
    }
}
