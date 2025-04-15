import java.util.ArrayList;

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

    void adicionaVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    void adicionaCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    int contarClientes() {
        return clientes.size();
    }

    int contarVendedores() {
        return vendedores.size();
    }

    void apresentarse() {
        System.out.println("Loja: " + nomeFantasia + ", CNPJ: " + cnpj + ", Endereço: " + rua + ", " + bairro + ", " + cidade);
    }
}