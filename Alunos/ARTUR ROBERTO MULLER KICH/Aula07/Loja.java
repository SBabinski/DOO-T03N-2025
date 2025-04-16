public class Loja {
    String nome;
    String cnpj;
    Vendedor[] vendedores;
    Cliente[] clientes;
    Endereco endereco;

    public Loja(String nome, String cnpj, Vendedor[] vendedores, Cliente[] clientes) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.vendedores = vendedores;
        this.clientes = clientes;
        this.endereco = new Endereco("PR", "Cascavel", "Centro", "Av. Tancredo Neves", 1200, "Prédio Comercial");
    }

    public void apresentarse() {
        System.out.println("Nome da Loja: " + nome);
        System.out.println("CNPJ: " + cnpj);
        endereco.apresentarLogradouro();
    }

    public void contarVendedores() {
        System.out.println("Quantidade de Vendedores: " + vendedores.length);
    }

    public void contarClientes() {
        System.out.println("Quantidade de Clientes: " + clientes.length);
    }
}
