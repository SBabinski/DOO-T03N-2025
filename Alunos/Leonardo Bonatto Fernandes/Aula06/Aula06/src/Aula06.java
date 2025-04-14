import java.util.ArrayList;
import java.util.List;

class Vendedor {
    private String nome;
    private int idade;
    private Loja loja;
    private String cidade;
    private String bairro;
    private String rua;
    private double salarioBase;
    private List<Double> salarioRecebido;

    public Vendedor(String nome, int idade, Loja loja, String cidade, String bairro, String rua, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new ArrayList<>();
        
        // Adicionando pelo menos 3 salários recebidos
        this.salarioRecebido.add(salarioBase);
        this.salarioRecebido.add(salarioBase * 0.9); // Exemplo de desconto
        this.salarioRecebido.add(salarioBase * 1.1); // Exemplo de bônus
    }

    public void apresentarse() {
        System.out.println("Meu nome é " + nome + ", tenho " + idade + " anos e trabalho na " + loja.getNomeFantasia() + ".");
    }

    public double calcularMedia() {
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.size();
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public Loja getLoja() { return loja; }
    public String getCidade() { return cidade; }
    public String getBairro() { return bairro; }
    public String getRua() { return rua; }
    public double getSalarioBase() { return salarioBase; }
    public List<Double> getSalarioRecebido() { return salarioRecebido; }
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
        System.out.println("Meu nome é " + nome + " e tenho " + idade + " anos.");
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getCidade() { return cidade; }
    public String getBairro() { return bairro; }
    public String getRua() { return rua; }
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

    public int contarClientes() {
        return clientes.size();
    }

    public int contarVendedores() {
        return vendedores.size();
    }

    public void apresentarse() {
        String endereco = rua + ", " + bairro + ", " + cidade;
        System.out.println("Loja: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + endereco);
    }

    public void adicionarVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    // Getters e Setters
    public String getNomeFantasia() { return nomeFantasia; }
    public String getRazaoSocial() { return razaoSocial; }
    public String getCnpj() { return cnpj; }
    public String getCidade() { return cidade; }
    public String getBairro() { return bairro; }
    public String getRua() { return rua; }
    public List<Vendedor> getVendedores() { return vendedores; }
    public List<Cliente> getClientes() { return clientes; }
}

public class Aula06 {
    public static void main(String[] args) {
        // Criando uma loja
        Loja myPlant = new Loja("My Plant", "My Plant LTDA", "12.345.678/0001-90", 
                               "São Paulo", "Centro", "Rua das Flores, 123");

        // Criando vendedores
        Vendedor vendedor1 = new Vendedor("João Silva", 30, myPlant, "São Paulo", 
                                         "Centro", "Rua A, 456", 2500);
        Vendedor vendedor2 = new Vendedor("Maria Santos", 28, myPlant, "São Paulo", 
                                         "Centro", "Rua B, 789", 2800);

        // Adicionando vendedores à loja
        myPlant.adicionarVendedor(vendedor1);
        myPlant.adicionarVendedor(vendedor2);

        // Criando clientes
        Cliente cliente1 = new Cliente("Carlos Oliveira", 35, "São Paulo", 
                                      "Jardins", "Av. Paulista, 1000");
        Cliente cliente2 = new Cliente("Ana Costa", 40, "São Paulo", 
                                     "Moema", "Rua das Palmeiras, 500");
        Cliente cliente3 = new Cliente("Pedro Souza", 25, "São Paulo", 
                                     "Vila Madalena", "Rua Aspicuelta, 200");

        // Adicionando clientes à loja
        myPlant.adicionarCliente(cliente1);
        myPlant.adicionarCliente(cliente2);
        myPlant.adicionarCliente(cliente3);

        // Testando os métodos
        System.out.println("\n--- Apresentação da Loja ---");
        myPlant.apresentarse();

        System.out.println("\n--- Informações dos Vendedores ---");
        for (Vendedor vendedor : myPlant.getVendedores()) {
            vendedor.apresentarse();
            System.out.printf("Média salarial: R$%.2f\n", vendedor.calcularMedia());
            System.out.printf("Bônus: R$%.2f\n", vendedor.calcularBonus());
        }

        System.out.println("\n--- Informações dos Clientes ---");
        for (Cliente cliente : myPlant.getClientes()) {
            cliente.apresentarse();
        }

        System.out.printf("\nTotal de vendedores: %d\n", myPlant.contarVendedores());
        System.out.printf("Total de clientes: %d\n", myPlant.contarClientes());
    }
}