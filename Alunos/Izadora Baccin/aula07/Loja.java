package fag;

import java.util.List;

public class Loja {
    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    Endereco endereco;
    List<Vendedor> vendedores;
    List<Cliente> clientes;

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco,
                List<Vendedor> vendedores, List<Cliente> clientes) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.vendedores = vendedores;
        this.clientes = clientes;
    }

    public void apresentarSe() {
        System.out.println("Loja: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        endereco.apresentarLogradouro();
    }

    public int contarClientes() {
        return clientes.size();
    }

    public int contarVendedores() {
        return vendedores.size();
    }
}

