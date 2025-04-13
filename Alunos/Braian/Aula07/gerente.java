package Alunos.Braian.Aula07;
public class Gerente extends Pessoa{
  private double salarioBase;
    private double[] salarioRecebido;
    private Endereco endereco;

    public Gerente(String nome, int idade, String loja, double salarioBase, Endereco endereco) {
        super(nome, idade, loja);
        this.salarioBase = salarioBase;
        this.endereco = endereco;
        this.salarioRecebido = new double[] { 1780.00, 1810.00, 1850.00 };
    }
    @Override
    public void apresentarSe() {
        super.apresentarSe();
        System.out.println("Salário Base: R$ " + salarioBase);
        endereco.apresentarLogradouro();
    }

    public double calcularMedia() {
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return salarioRecebido.length > 0 ? soma / salarioRecebido.length : 0;
    }

    public double calcularBonus() {
        return salarioBase * 0.35;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double[] getSalarioRecebido() {
        return salarioRecebido;
    }

    public void setSalarioRecebido(double[] salarioRecebido) {
        this.salarioRecebido = salarioRecebido;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
