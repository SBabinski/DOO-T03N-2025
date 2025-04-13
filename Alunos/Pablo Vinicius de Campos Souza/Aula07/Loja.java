package Aula07;

import java.util.ArrayList;

public class Loja {
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private Endereco endereco;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<Cliente> clientes;
    private ArrayList<Gerente> gerentes;

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.vendedores = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public int contarClientes() {
        return clientes != null ? clientes.size() : 0;
    }

    public int contarVendedores() {
        return vendedores != null ? vendedores.size() : 0;
    }

    public void apresentarSe() {
        System.out.println("\n=== Dados da Loja ===");
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        endereco.apresentarLogradouro();
    }

    public void adicionarVendedor(Vendedor vendedor) {
        if (!vendedores.contains(vendedor)) {
            vendedores.add(vendedor);
            vendedor.setLoja(this);
        }
    }

    public void removerVendedor(Vendedor vendedor) {
        if (vendedores.remove(vendedor)) {
            vendedor.setLoja(null);
        }
    }

    public void adicionarGerente(Gerente gerente) {
        if (!gerentes.contains(gerente)) {
            gerentes.add(gerente);
            gerente.setLoja(this);
        }
    }

    public void removerGerente(Gerente gerente) {
        if (gerentes.remove(gerente)) {
            gerente.setLoja(null);
        }
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }
}