import java.util.ArrayList;
import java.util.List;

class Vendedor {
    String nome;
    int idade;
    String loja;
    String cidade;
    String bairro;
    String rua;
    double salarioBase;
    double[] salarioRecebido = {2500.00, 2700.00, 2600.00};

    void apresentarse() {
        System.out.println("Vendedor: " + nome + ", Idade: " + idade + ", Loja: " + loja);
    }

    double calcularMedia() {
        double total = 0;
        for (double salario : salarioRecebido) {
            total += salario;
        }
        return total / salarioRecebido.length;
    }

    double calcularBonus() {
        return salarioBase * 0.2;
    }
}

class Cliente {
    String nome;
    int idade;
    String cidade;
    String bairro;
    String rua;

    void apresentarse() {
        System.out.println("Cliente: " + nome + ", Idade: " + idade);
    }
}

class Loja {
    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    String cidade;
    String bairro;
    String rua;

    List<Vendedor> vendedores = new ArrayList<>();
    List<Cliente> clientes = new ArrayList<>();

    void apresentarse() {
        System.out.println("Loja: " + nomeFantasia + ", CNPJ: " + cnpj);
        System.out.println("Endereço: " + rua + ", " + bairro + ", " + cidade);
    }

    int contarClientes() {
        return clientes.size();
    }

    int contarVendedores() {
        return vendedores.size();
    }
}

public class SistemaMyPlant {
    public static void main(String[] args) {
        // Criando loja
        Loja loja = new Loja();
        loja.nomeFantasia = "My Plant";
        loja.razaoSocial = "My Plant Ltda";
        loja.cnpj = "12.345.678/0001-99";
        loja.cidade = "Cidade Verde";
        loja.bairro = "Centro";
        loja.rua = "Rua das Palmeiras";

        // Criando vendedores
        Vendedor vendedor1 = new Vendedor();
        vendedor1.nome = "Lucas";
        vendedor1.idade = 30;
        vendedor1.loja = loja.nomeFantasia;
        vendedor1.cidade = loja.cidade;
        vendedor1.bairro = loja.bairro;
        vendedor1.rua = loja.rua;
        vendedor1.salarioBase = 2500;

        Vendedor vendedor2 = new Vendedor();
        vendedor2.nome = "Fernanda";
        vendedor2.idade = 28;
        vendedor2.loja = loja.nomeFantasia;
        vendedor2.cidade = loja.cidade;
        vendedor2.bairro = loja.bairro;
        vendedor2.rua = loja.rua;
        vendedor2.salarioBase = 2800;

        // Criando clientes
        Cliente cliente1 = new Cliente();
        cliente1.nome = "João";
        cliente1.idade = 40;
        cliente1.cidade = "Cidade Verde";
        cliente1.bairro = "Jardins";
        cliente1.rua = "Av. das Flores";

        Cliente cliente2 = new Cliente();
        cliente2.nome = "Maria";
        cliente2.idade = 35;
        cliente2.cidade = "Cidade Verde";
        cliente2.bairro = "Vila Nova";
        cliente2.rua = "Rua dos Jasmins";

        // Adicionando ao array
        loja.vendedores.add(vendedor1);
        loja.vendedores.add(vendedor2);
        loja.clientes.add(cliente1);
        loja.clientes.add(cliente2);

        // Exibindo dados
        loja.apresentarse();

        System.out.println("\nVendedores:");
        for (Vendedor v : loja.vendedores) {
            v.apresentarse();
            System.out.printf("Média Salarial: R$ %.2f%n", v.calcularMedia());
            System.out.printf("Bônus: R$ %.2f%n", v.calcularBonus());
            System.out.println();
        }

        System.out.println("Clientes:");
        for (Cliente c : loja.clientes) {
            c.apresentarse();
        }

        System.out.println("\nQuantidade de vendedores: " + loja.contarVendedores());
        System.out.println("Quantidade de clientes: " + loja.contarClientes());
    }
}
