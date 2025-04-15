import java.util.ArrayList;
import java.util.List;

public class Loja {
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private Endereco endereco;
    private List<Vendedor> vendedores;
    private List<Cliente> clientes;
    private List<Gerente> gerentes;

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.vendedores = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.gerentes = new ArrayList<>();
    }

    public int contarClientes() {
        return clientes.size();
    }

    public int contarVendedores() {
        return vendedores.size();
    }

    public void apresentarLoja() {
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("Razão Social: " + razaoSocial);
        System.out.println("CNPJ: " + cnpj);
        endereco.apresentarLogradouro();
    }

    public void adicionarVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void adicionarGerente(Gerente gerente) {
        gerentes.add(gerente);
    }

    // Getters
    public String getNomeFantasia() { return nomeFantasia; }
    public String getRazaoSocial() { return razaoSocial; }
    public String getCnpj() { return cnpj; }
    public Endereco getEndereco() { return endereco; }
    public List<Vendedor> getVendedores() { return vendedores; }
    public List<Cliente> getClientes() { return clientes; }
    public List<Gerente> getGerentes() { return gerentes; }
}