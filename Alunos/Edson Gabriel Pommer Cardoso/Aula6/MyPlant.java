package Aula6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MyPlant {

    private static List<Venda> registroVendas = new ArrayList<>();
    private static Map<String, Integer> vendasPorData = new HashMap<>();
    private static List<Vendedor> vendedores = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static Loja loja;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        loja = new Loja("My Plant", "Razão Social My Plant", "12.345.678/0001-90", "Cidade Exemplo", "Bairro Exemplo", "Rua Exemplo");
   
        Vendedor vendedor1 = new Vendedor("João", 30, "My Plant", "Cidade Exemplo", "Bairro Exemplo", "Rua Exemplo", 2000.0);
        vendedor1.adicionarSalarioRecebido(2000.0);
        vendedor1.adicionarSalarioRecebido(2200.0);
        vendedor1.adicionarSalarioRecebido(2100.0);
        
        Vendedor vendedor2 = new Vendedor("Maria", 25, "My Plant", "Cidade Exemplo", "Bairro Exemplo", "Rua Exemplo", 2500.0);
        vendedor2.adicionarSalarioRecebido(2500.0);
        vendedor2.adicionarSalarioRecebido(2600.0);
        vendedor2.adicionarSalarioRecebido(2700.0);
        
        vendedores.add(vendedor1);
        vendedores.add(vendedor2);
        loja.setVendedores(vendedores);
   
        Cliente cliente1 = new Cliente("Carlos", 40, "Cidade Exemplo", "Bairro Exemplo", "Rua Exemplo");
        Cliente cliente2 = new Cliente("Ana", 35, "Cidade Exemplo", "Bairro Exemplo", "Rua Exemplo");
        
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
            System.out.println("[8] - Sair");
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
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 8);

        scanner.close();
    }

    private static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade da planta: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();
        
        double precoTotal = quantidade * precoUnitario;
        if (quantidade > 10) {
            double desconto = precoTotal * 0.05; // 
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
    private String loja;
    private String cidade;
    private String bairro;
    private String rua;
    private double salarioBase;
    private List<Double> salarioRecebido;

    public Vendedor(String nome, int idade, String loja, String cidade, String bairro, String rua, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new ArrayList<>();
    }

    public void adicionarSalarioRecebido(double salario) {
        salarioRecebido.add(salario);
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome + ", Idade: " + idade + ", Loja: " + loja);
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
        return salarioBase * 0.2;
    }
}

class Cliente {
    private String nome;
    private int idade;
    private String cidade;
    private String bairro;
    private String rua;

    public Cliente(String nome, int idade, String cidade, String bairro, String rua) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome + ", Idade: " + idade);
    }
}

class Loja {
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String cidade;
    private String bairro;
    private String rua;
    private List<Vendedor> vendedores;
    private List<Cliente> clientes;

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
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

    public int contarClientes() {
        return clientes.size();
    }

    public int contarVendedores() {
        return vendedores.size();
    }

    public void apresentarse() {
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + rua + ", " + bairro + ", " + cidade);
    }
}