import java.util.Arrays;

public class Vendedor {
    String nome;
    int idade;
    String loja;
    Endereco endereco;
    double salarioBase;
    double[] salarioRecebido = {2500.0, 2600.0, 2700.0};

    public Vendedor(String nome, int idade, String loja, Endereco endereco, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.endereco = endereco;
        this.salarioBase = salarioBase;
    }

    public void apresentarse() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Idade: " + this.idade);
        System.out.println("Loja: " + this.loja);
        endereco.mostrarRua();
    }

    public double calcularMedia() {
        return Arrays.stream(salarioRecebido).average().orElse(0.0);
    }

    public double calcularBonus() {
        return this.salarioBase * 0.2;
    }
}
