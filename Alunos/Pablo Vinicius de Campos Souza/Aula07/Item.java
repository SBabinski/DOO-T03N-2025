package Aula07;

public class Item {
    private int id;
    private String nome;
    private String tipo;
    private double valor;

    public Item(int id, String nome, String tipo, double valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public void gerarDescricao() {
        System.out.printf("[%d]: (%s) %s - R$ %.2f", id, tipo, nome, valor);
        // [ID]: (TIPO) NOME - R$ 0.00
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

}