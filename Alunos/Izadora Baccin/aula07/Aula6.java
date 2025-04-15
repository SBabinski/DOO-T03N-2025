package fag;

import java.time.LocalDate;
import java.util.*;

public class Aula6 {
    static List<Venda> registroVendas = new ArrayList<>();
    static List<Vendedor> vendedores = new ArrayList<>();
    static List<Cliente> clientes = new ArrayList<>();
    static Loja loja = new Loja("My Plant", "My Plants LTDA", "12.345.678/0001-99",
            "Florianopolis", "Centro", "Rua das Palmeiras", vendedores, clientes);

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("--------------------------------");
            System.out.println("1 - Calcular Preço Total");
            System.out.println("2 - Calcular Troco");
            System.out.println("3 - Registro de Vendas");
            System.out.println("4 - Ver Vendas Por Data");
            System.out.println("5 - Cadastrar Vendedor");
            System.out.println("6 - Cadastrar Cliente");
            System.out.println("7 - Dados da Loja");
            System.out.println("8- Criar Pedido"); 
            System.out.println("9 - Sair");
            System.out.println("--------------------------------\n");
            opcao = scan.nextInt();

            switch (opcao) {
                case 1 -> calcularPrecoTotal(scan);
                case 2 -> calcularTroco(scan);
                case 3 -> registrarVenda();
                case 4 -> verVendasPorData(scan);
                case 5 -> cadastrarVendedor(scan);
                case 6 -> cadastrarCliente(scan);
                case 7 -> mostrarDadosLoja();
                case 8 -> criarPedidoFake();
                case 9 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 9);

        scan.close();
    }
    
    private static void criarPedidoFake() {
        String[] nomesPlantas = {"Orquídea", "Samambaia", "Cacto", "Suculenta", "Bromélia"};
        Random rand = new Random();

        String nomePlanta = nomesPlantas[rand.nextInt(nomesPlantas.length)];
        int unidades = rand.nextInt(20) + 1; 
        float precoUnitario = 10 + rand.nextFloat() * 40; 

        float precoTotal = unidades * precoUnitario;
        float desconto = 0;
        if (unidades > 10) {
            desconto = precoTotal * 0.05f;
            precoTotal -= desconto;
        }

        LocalDate data = LocalDate.now().minusDays(rand.nextInt(30)); 

        Venda vendaFake = new Venda(nomePlanta, unidades, precoUnitario, desconto, precoTotal, data);
        registroVendas.add(vendaFake);

        System.out.println("Pedido fake criado com sucesso!");
        System.out.printf("Planta: %s\nUnidades: %d\nPreço Unitário: R$%.2f\nDesconto: R$%.2f\nPreço Total: R$%.2f\nData: %s\n",
                nomePlanta, unidades, precoUnitario, desconto, precoTotal, data.toString());
    }

    
    private static void calcularPrecoTotal(Scanner scan) {
        System.out.println("Qual o nome da planta?");
        String nome = scan.next();
        System.out.println("Quantas unidades?");
        int unid = scan.nextInt();
        System.out.println("Qual o preço da unidade?");
        float preco = scan.nextFloat();

        float precoTotal = unid * preco;
        float desc = 0;

        if (unid > 10) {
            desc = precoTotal * 0.05f;
            precoTotal -= desc;
            System.out.printf("O valor da compra com 5%% de desconto: $%.2f\n", precoTotal);
        } else {
            System.out.printf("O valor de %d %s é: $%.2f\n", unid, nome, precoTotal);
        }

        registroVendas.add(new Venda(nome, unid, preco, desc, precoTotal, LocalDate.now()));
    }

    private static void calcularTroco(Scanner scan) {
        System.out.println("Valor total da compra?");
        float total = scan.nextFloat();
        System.out.println("Valor recebido?");
        float recebido = scan.nextFloat();

        if (recebido < total) {
            System.out.println("Valor insuficiente! ");
        } else {
            float troco = recebido - total;
            System.out.printf("O valor do troco é: $%.2f\n", troco);
        }
    }

    private static void registrarVenda() {
        System.out.println("--------------------------------\n");
        System.out.println("Registro de vendas: ");
        if (registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            for (Venda venda : registroVendas) {
                System.out.println("------------------------------\n");
                System.out.printf("Planta: %s\nUnidades: %d\nPreço Unitário: $%.2f\nDesconto: $%.2f\nPreço Total: $%.2f\n",
                        venda.nome, venda.unid, venda.preco, venda.desc, venda.precoTotal);
            }
        }
    }

    private static void verVendasPorData(Scanner scan) {
        System.out.println("Qual o mês? 1-12");
        int mes = scan.nextInt();
        System.out.println("Qual o dia? 1-31 ou 0 Para o mês todo:");
        int dia = scan.nextInt();

        List<Venda> vendasFiltradas;
        if (dia == 0) {
            vendasFiltradas = registroVendas.stream()
                    .filter(v -> v.data.getMonthValue() == mes)
                    .toList();
        } else {
            vendasFiltradas = registroVendas.stream()
                    .filter(v -> v.data.getMonthValue() == mes && v.data.getDayOfMonth() == dia)
                    .toList();
        }

        if (vendasFiltradas.isEmpty()) {
            System.out.println("Sem vendas registradas nessa data!");
        } else {
            for (Venda venda : vendasFiltradas) {
                System.out.printf("Data: %s\nPlanta: %s\nUnidades: %d\nPreço Total: %.2f\n\n",
                        venda.data, venda.nome, venda.unid, venda.precoTotal);
            }
        }
    }

    private static void cadastrarVendedor(Scanner scan) {
        scan.nextLine(); 
        System.out.println("Nome:");
        String nome = scan.nextLine();
        System.out.println("Idade:");
        int idade = scan.nextInt();
        scan.nextLine();
        System.out.println("Cidade:");
        String cidade = scan.nextLine();
        System.out.println("Bairro:");
        String bairro = scan.nextLine();
        System.out.println("Rua:");
        String rua = scan.nextLine();
        System.out.println("Salário base:");
        double salarioBase = scan.nextDouble();

        Vendedor vendedor = new Vendedor(nome, idade, loja.nomeFantasia, cidade, bairro, rua, salarioBase);
        vendedores.add(vendedor);

        System.out.println("Vendedor cadastrado com sucesso!");
        vendedor.apresentarSe();
        System.out.printf("Média salarial: R$%.2f\n", vendedor.calcularMedia());
        System.out.printf("Bônus: R$%.2f\n", vendedor.calcularBonus());
    }

    private static void cadastrarCliente(Scanner scan) {
        scan.nextLine(); 
        System.out.println("Nome:");
        String nome = scan.nextLine();
        System.out.println("Idade:");
        int idade = scan.nextInt();
        scan.nextLine();
        System.out.println("Cidade:");
        String cidade = scan.nextLine();
        System.out.println("Bairro:");
        String bairro = scan.nextLine();
        System.out.println("Rua:");
        String rua = scan.nextLine();

        Cliente cliente = new Cliente(nome, idade, cidade, bairro, rua);
        clientes.add(cliente);

        System.out.println("Cliente cadastrado com sucesso!");
        cliente.apresentarSe();
    }

    private static void mostrarDadosLoja() {
        loja.apresentarSe();
        System.out.println("Total de vendedores: " + loja.contarVendedores());
        System.out.println("Total de clientes: " + loja.contarClientes());
    }

    static class Venda {
        String nome;
        int unid;
        float preco;
        float desc;
        float precoTotal;
        LocalDate data;

        public Venda(String nome, int unid, float preco, float desc, float precoTotal, LocalDate data) {
            this.nome = nome;
            this.unid = unid;
            this.preco = preco;
            this.desc = desc;
            this.precoTotal = precoTotal;
            this.data = data;
        }
    }

    static class Vendedor {
        String nome;
        int idade;
        String loja;
        String cidade;
        String bairro;
        String rua;
        double salarioBase;
        double[] salarioRecebido = {2500.0, 2600.0, 2550.0};

        public Vendedor(String nome, int idade, String loja, String cidade, String bairro, String rua, double salarioBase) {
            this.nome = nome;
            this.idade = idade;
            this.loja = loja;
            this.cidade = cidade;
            this.bairro = bairro;
            this.rua = rua;
            this.salarioBase = salarioBase;
        }

        public void apresentarSe() {
            System.out.println("Nome: " + nome);
            System.out.println("Idade: " + idade);
            System.out.println("Loja: " + loja);
        }

        public double calcularMedia() {
            return Arrays.stream(salarioRecebido).average().orElse(0);
        }

        public double calcularBonus() {
            return salarioBase * 0.2;
        }
    }

    static class Cliente {
        String nome;
        int idade;
        String cidade;
        String bairro;
        String rua;

        public Cliente(String nome, int idade, String cidade, String bairro, String rua) {
            this.nome = nome;
            this.idade = idade;
            this.cidade = cidade;
            this.bairro = bairro;
            this.rua = rua;
        }

        public void apresentarSe() {
            System.out.println("Nome: " + nome);
            System.out.println("Idade: " + idade);
        }
    }

    static class Loja {
        String nomeFantasia;
        String razaoSocial;
        String cnpj;
        String cidade;
        String bairro;
        String rua;
        List<Vendedor> vendedores;
        List<Cliente> clientes;

        public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua,
                    List<Vendedor> vendedores, List<Cliente> clientes) {
            this.nomeFantasia = nomeFantasia;
            this.razaoSocial = razaoSocial;
            this.cnpj = cnpj;
            this.cidade = cidade;
            this.bairro = bairro;
            this.rua = rua;
            this.vendedores = vendedores;
            this.clientes = clientes;
        }

        public void apresentarSe() {
            System.out.println("Nome Fantasia: " + nomeFantasia);
            System.out.println("CNPJ: " + cnpj);
            System.out.println("Endereço: " + rua + ", " + bairro + ", " + cidade);
        }

        public int contarClientes() {
            return clientes.size();
        }

        public int contarVendedores() {
            return vendedores.size();
        }
    }
}