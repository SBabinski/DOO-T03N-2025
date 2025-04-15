package Aula7;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MyPlant2 {

    private static List<Venda> registroVendas = new ArrayList<>();
    private static Map<String, Integer> vendasPorData = new HashMap<>();
    private static List<Vendedor> vendedores = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static Loja loja;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        loja = new Loja("My Plant", "Razão Social My Plant", "12.345.678/0001-90",
                new Endereco("Estado Exemplo", "Cidade Exemplo", "Bairro Exemplo", "123", "Apto 1"));

        Vendedor vendedor1 = new Vendedor("João", 30, loja, 2000.0);
        vendedor1.adicionarSalarioRecebido(2000.0);
        vendedor1.adicionarSalarioRecebido(2200.0);
        vendedor1.adicionarSalarioRecebido(2100.0);

        Vendedor vendedor2 = new Vendedor("Maria", 25, loja, 2500.0);
        vendedor2.adicionarSalarioRecebido(2500.0);
        vendedor2.adicionarSalarioRecebido(2600.0);
        vendedor2.adicionarSalarioRecebido(2700.0);

        vendedores.add(vendedor1);
        vendedores.add(vendedor2);
        loja.setVendedores(vendedores);

        Cliente cliente1 = new Cliente("Carlos", 40,
                new Endereco("Estado Exemplo", "Cidade Exemplo", "Bairro Exemplo", "456", "Casa 2"));
        Cliente cliente2 = new Cliente("Ana", 35,
                new Endereco("Estado Exemplo", "Cidade Exemplo", "Bairro Exemplo", "789", "Apto 3"));

        clientes.add(cliente1);
        clientes.add(cliente2);
        loja.setClientes(clientes);

        loja.apresentarse();

        int option;
        do {
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Visualizar Registro de Vendas");
            System.out.println("[4] - Registrar Vendas por Data");
            System.out.println("[5] - Buscar Vendas por Data");
            System.out.println("[6] - Mostrar Vendedores");
            System.out.println("[7] - Mostrar Clientes");
            System.out.println("[8] - Criar Pedido");
            System.out.println("[9] - Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    calcularPrecoTotal(scanner);
                    break;
                case 2:
                    calcularTroco(scanner);
                    break;
                case 3:
                    visualizarRegistroVendas();
                    break;
                case 4:
                    registrarVendasPorData(scanner);
                    break;
                case 5:
                    buscarVendasPorData(scanner);
                    break;
                case 6:
                    mostrarVendedores();
                    break;
                case 7:
                    mostrarClientes();
                    break;
                case 8:
                    criarPedido(scanner);
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 9);

        scanner.close();
    }

    private static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade da planta: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();

        double precoTotal = quantidade * precoUnitario;
        if (quantidade > 10) {
            double desconto = precoTotal * 0.05; 
            precoTotal -= desconto;
            System.out.println("Desconto aplicado: " + desconto);
        }

        System.out.println("O preço total é: " + precoTotal);
        registrarVenda(quantidade, precoTotal);
    }

    private static void calcularTroco(Scanner scanner) {
        System.out.print("Digite o valor recebido do cliente: ");
        double valorRecebido = scanner.nextDouble();
        System.out.print("Digite o valor total da compra: ");
        double valorTotal = scanner.nextDouble();

        double troco = valorRecebido - valorTotal;
        System.out.println("O troco a ser dado é: " + troco);
    }

    private static void registrarVenda(int quantidade, double valorVenda) {
        Venda novaVenda = new Venda(quantidade, valorVenda);
        registroVendas.add(novaVenda);
        System.out.println("Venda registrada com sucesso!");
    }

    private static void visualizarRegistroVendas() {
        System.out.println("Registro de Vendas:");
        for (Venda venda : registroVendas) {
            System.out.println(venda);
        }
    }

    private static void registrarVendasPorData(Scanner scanner) {
        System.out.print("Digite a data (dd/mm): ");
        String data = scanner.next();
        System.out.print("Digite a quantidade de vendas: ");
        int quantidade = scanner.nextInt();

        vendasPorData.put(data, vendasPorData.getOrDefault(data, 0) + quantidade);
        System.out.println("Vendas registradas para a data " + data + " com sucesso!");
    }

    private static void buscarVendasPorData(Scanner scanner) {
        System.out.print("Digite a data (dd/mm) para buscar: ");
        String data = scanner.next();

        int totalVendas = vendasPorData.getOrDefault(data, 0);
        System.out.println("Total de vendas para a data " + data + ": " + totalVendas);
    }

    private static void mostrarVendedores() {
        System.out.println("Vendedores:");
        for (Vendedor vendedor : loja.getVendedores()) {
            vendedor.apresentarse();
            System.out.println("Média Salarial: " + vendedor.calcularMedia());
            System.out.println("Bônus: " + vendedor.calcularBonus());
            System.out.println();
        }
    }

    private static void mostrarClientes() {
        System.out.println("Clientes:");
        for (Cliente cliente : loja.getClientes()) {
            cliente.apresentarse();
            System.out.println();
        }
    }

    private static void criarPedido(Scanner scanner) {
        System.out.print("Digite o ID do pedido: ");
        int id = scanner.nextInt();
        Date dataCriacao = new Date(); 
        System.out.print("Digite a data de pagamento (dd/mm/yyyy): ");
        String dataPagamentoStr = scanner.next();
        System.out.print("Digite a data de vencimento da reserva (dd/mm/yyyy): ");
        String dataVencimentoStr = scanner.next();

        System.out.print("Escolha um cliente (0 para " + clientes.get(0).getNome() + ", 1 para "
                + clientes.get(1).getNome() + "): ");
        int clienteIndex = scanner.nextInt();
        Cliente cliente = clientes.get(clienteIndex);

        System.out.print("Escolha um vendedor (0 para " + vendedores.get(0).getNome() + ", 1 para "
                + vendedores.get(1).getNome() + "): ");
        int vendedorIndex = scanner.nextInt();
        Vendedor vendedor = vendedores.get(vendedorIndex);

        List<Item> itens = new ArrayList<>();
        System.out.print("Quantos itens deseja adicionar ao pedido? ");
        int numItens = scanner.nextInt();
        for (int i = 0; i < numItens; i++) {
            System.out.print("Digite o ID do item: ");
            int itemId = scanner.nextInt();
            System.out.print("Digite o nome do item: ");
            String nome = scanner.next();
            System.out.print("Digite o tipo do item: ");
            String tipo = scanner.next();
            System.out.print("Digite o valor do item: ");
            double valor = scanner.nextDouble();
            itens.add(new Item(itemId, nome, tipo, valor));
        }

        Pedido pedido = new Pedido(id, dataCriacao, null, null, cliente, vendedor, loja, itens);
        pedido.setDataPagamento(dataPagamentoStr);
        pedido.setDataVencimentoReserva(dataVencimentoStr);

        if (pedido.confirmarPagamento()) {
            System.out.println("Pagamento confirmado!");
        } else {
            System.out.println("Pagamento não confirmado. A reserva está vencida.");
        }

        System.out.println(pedido.gerarDescricaoVenda());
    }
}

class Venda {
    private int quantidade;
    private double valorVenda;

    public Venda(int quantidade, double valorVenda) {
        this.quantidade = quantidade;
        this.valorVenda = valorVenda;
    }

    public String toString() {
        return "Quantidade: " + quantidade + ", Valor da Venda: " + valorVenda;
    }
}

class Vendedor {
    private String nome;
    private int idade;
    private Loja loja;
    private double salarioBase;
    private List<Double> salarioRecebido;

    public Vendedor(String nome, int idade, Loja loja, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new ArrayList<>();
    }

    public void adicionarSalarioRecebido(double salario) {
        salarioRecebido.add(salario);
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome + ", Idade: " + idade + ", Loja: " + loja.getNomeFantasia());
    }

    public double calcularMedia() {
        if (salarioRecebido.isEmpty()) {
            return 0;
        }
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.size();
    }

    public double calcularBonus() {
        return salarioBase * 0.35; 
    }

    public String getNome() {
        return nome;
    }
}

class Gerente {
    private String nome;
    private int idade;
    private Loja loja;
    private double salarioBase;
    private List<Double> salarioRecebido;

    public Gerente(String nome, int idade, Loja loja, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new ArrayList<>();
    }

    public void adicionarSalarioRecebido(double salario) {
        salarioRecebido.add(salario);
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome + ", Idade: " + idade + ", Loja: " + loja.getNomeFantasia());
    }

    public double calcularMedia() {
        if (salarioRecebido.isEmpty()) {
            return 0;
        }
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.size();
    }

    public double calcularBonus() {
        return salarioBase * 0.35; 
    }
}

class Cliente {
    private String nome;
    private int idade;
    private Endereco endereco;

    public Cliente(String nome, int idade, Endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome + ", Idade: " + idade);
        endereco.apresentarLogradouro();
    }

    public String getNome() {
        return nome;
    }
}

class Loja {
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private Endereco endereco;
    private List<Vendedor> vendedores;
    private List<Cliente> clientes;

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.vendedores = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void apresentarse() {
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        endereco.apresentarLogradouro();
    }
}

class Endereco {
    private String estado;
    private String cidade;
    private String bairro;
    private String numero;
    private String complemento;

    public Endereco(String estado, String cidade, String bairro, String numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public void apresentarLogradouro() {
        System.out.println("Endereço: " + numero + ", " + complemento + ", " + bairro + ", " + cidade + ", " + estado);
    }
}

class Item {
        private int id;
        private String nome;
        private String tipo;
        private double valor;
    
        public Item(int id, String nome, String tipo, double valor) {
            this.id = id;
            this.nome = nome;
            this.tipo = tipo;
            this.valor = valor;
        }
    
        public void gerarDescricao() {
            System.out.println("ID: " + id + ", Nome: " + nome + ", Tipo: " + tipo + ", Valor: " + valor);
        }
    
        public double getValor() {
            return valor;
        }
    }

class Pedido {
    private int id;
    private Date dataCriacao;
    private Date dataPagamento;
    private Date dataVencimentoReserva;
    private Cliente cliente;
    private Vendedor vendedor;
    private Loja loja;
    private List<Item> itens;

    public Pedido(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva, Cliente cliente,
            Vendedor vendedor, Loja loja, List<Item> itens) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
    }

    public void setDataPagamento(String dataPagamentoStr) {
        try {
            this.dataPagamento = new SimpleDateFormat("dd/MM/yyyy").parse(dataPagamentoStr);
        } catch (ParseException e) {
            System.out.println("Data de pagamento inválida. " + e.getMessage());
        }
    }

    public void setDataVencimentoReserva(String dataVencimentoStr) {
        try {
            this.dataVencimentoReserva = new SimpleDateFormat("dd/MM/yyyy").parse(dataVencimentoStr);
        } catch (ParseException e) {
            System.out.println("Data de vencimento inválida. " + e.getMessage());
        }
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.getValor();
        }
        return total;
    }

    public String gerarDescricaoVenda() {
        return "Pedido ID: " + id + ", Data de Criação: " + dataCriacao + ", Valor Total: " + calcularValorTotal();
    }

    public boolean confirmarPagamento() {
        Date now = new Date();
        return now.before(dataVencimentoReserva);
    }
}

class ProcessaPedido {
    public void processar(int id, Cliente cliente, Vendedor vendedor, List<Item> itens, Loja loja) {
        Pedido pedido = new Pedido(id, new Date(), null, null, cliente, vendedor, loja, itens);
    }
}
