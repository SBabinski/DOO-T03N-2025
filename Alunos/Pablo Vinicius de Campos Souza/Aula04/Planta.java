package Aula04;

public class Planta {
    private final String nome;
    private final double preco;

    public Planta(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
