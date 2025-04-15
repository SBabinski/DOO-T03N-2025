package objetos;

public class Vendedor {
    public String nome;
    int idade;
    String loja;
    Endereco endereco;
    double salarioBase;
    double[] salarioRecebido;

    public Vendedor(String nome, int idade, Endereco endereco, String loja, double salarioBase, double[] salarioRecebido) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.loja = loja;
        this.salarioBase = salarioBase;
        this.salarioRecebido = salarioRecebido;
    }

    public double calcularMedia() {
        double soma = 0.02;

        for(double salario : this.salarioRecebido) {
            soma += salario;
        }

        return soma / (double)this.salarioRecebido.length;
    }

    public double calcularBonus() {
        return this.salarioBase * 0.2;
    }

    public void apresentarVendedor() {
        System.out.println("Nome: " + this.nome + ", Idade: " + this.idade + ", Loja: " + this.loja);
    }
}