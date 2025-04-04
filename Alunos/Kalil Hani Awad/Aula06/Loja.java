import java.util.ArrayList;

public class Loja {
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String cidade;
    private String bairro;
    private String rua;
    private ArrayList<Vendedor> vendedores = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }

    public void addVendedor(Vendedor v) {
        vendedores.add(v);
    }

    public void addCliente(Cliente c) {
        clientes.add(c);
    }

    public int contarVendedores() {
        return vendedores.size();
    }

    public int contarClientes() {
        return clientes.size();
    }

    public void apresentarse() {
        System.out.println("Nome Fantasia: " + nomeFantasia +
                "\nCNPJ: " + cnpj +
                "\nEndereço: " + cidade + ", " + bairro + ", " + rua);
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }
}
