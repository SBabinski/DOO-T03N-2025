package objetos;

public class Gerente extends Vendedor {
    public Gerente(String nome, int idade, Endereco endereco, String loja, double salarioBase, double[] salarioRecebido) {
        super(nome, idade, endereco, loja, salarioBase, salarioRecebido);
    }

    @Override
    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}