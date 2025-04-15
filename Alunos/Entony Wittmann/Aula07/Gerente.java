public class Gerente extends Vendedor {
    public Gerente(String nome, int idade, String loja, Endereco endereco, double salarioBase) {
        super(nome, idade, loja, endereco, salarioBase);
    }

    @Override
    public double calcularBonus() {
        return this.salarioBase * 0.35;
    }
}
