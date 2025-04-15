package fag;

import java.util.Arrays;

public class Vendedor {
    String nome;
    int idade;
    String loja;
    Endereco endereco;
    double salarioBase;
    double[] salarioRecebido = {2500.0, 2600.0, 2550.0}; // valores fixos para exemplo

    public Vendedor(String nome, int idade, String loja, Endereco endereco, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.endereco = endereco;
        this.salarioBase = salarioBase;
    }

    public void apresentarSe() {
        System.out.println("Vendedor: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
        endereco.apresentarLogradouro();
    }

    public double calcularMedia() {
        return Arrays.stream(salarioRecebido).average().orElse(0);
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}

