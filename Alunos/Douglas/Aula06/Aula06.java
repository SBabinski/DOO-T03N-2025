import java.util.ArrayList;

/**
 *
 * @author dougbruno
 */

public class Aula06 {

    public static void main(String[] args) {
        Loja loja = new Loja("My Plant", "My Plant LTDA", "12.345.678/0001-90", "Cascavel", "Centro", "Avenida Brasil");

        Vendedor vendedor1 = new Vendedor("Douglas", 21, "My Plant", "Cascavel", "Centro", "Avenida Brasil", 3000.0,
                new double[]{3100.0, 3200.0, 3300.0});
        Vendedor vendedor2 = new Vendedor("Bruno", 25, "My Plant", "Cascavel", "Centro", "Avenida Brasil", 2800.0,
                new double[]{2900.0, 2950.0, 3000.0});

        loja.adicionarVendedor(vendedor1);
        loja.adicionarVendedor(vendedor2);

        Cliente cliente1 = new Cliente("João", 45, "Cascavel", "Floresta", "Rua Uirapuru");
        Cliente cliente2 = new Cliente("Ana", 33, "Cascavel", "Centro", "Rua Paraná");

        loja.adicionarCliente(cliente1);
        loja.adicionarCliente(cliente2);

        vendedor1.apresentarse();
        System.out.println("Média dos salários: " + vendedor1.calcularMedia());
        System.out.println("Bônus: " + vendedor1.calcularBonus());
        System.out.println();

        cliente1.apresentarse();
        System.out.println();

        loja.apresentarse();
        System.out.println("Total de Clientes: " + loja.contarClientes());
        System.out.println("Total de Vendedores: " + loja.contarVendedores());
    }
}

class Vendedor {
    String nome;
    int idade;
    String loja;
    String cidade;
    String bairro;
    String rua;
    double salarioBase;
    double[] salarioRecebido;

    public Vendedor(String nome, int idade, String loja, String cidade, String bairro, String rua, double salarioBase, double[] salarioRecebido) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;
        this.salarioRecebido = salarioRecebido;
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome + ", Idade: " + idade + ", Loja: " + loja);
    }

    public double calcularMedia() {
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return salarioRecebido.length > 0 ? soma / salarioRecebido.length : 0;
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}

class Cliente {
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

    public void apresentarse() {
        System.out.println("Nome: " + nome + ", Idade: " + idade);
    }
}

class Loja {
    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    String cidade;
    String bairro;
    String rua;
    ArrayList<Vendedor> vendedores = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }

    public void adicionarVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
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

 