import java.util.ArrayList;

public class Gerente extends Pessoa {

    public String loja;
    public Endereco endereco;
    public double salarioBase;
    public ArrayList<Double> salarioRecebido = new ArrayList<>();

    public Gerente(String nome, int idade, String loja, Endereco endereco, double salarioBase) {
        super(nome, idade);
        this.loja = loja;
        this.endereco = endereco;
        this.salarioBase = salarioBase;
        this.salarioRecebido.add(2000.00);
        this.salarioRecebido.add(21000.00);
        this.salarioRecebido.add(2200.00);
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome + ", Idade: " + idade + ", Loja: " + loja);
    }

    public double calcularMedia() {
        double total = 0;
        for (double valor : salarioRecebido) {
            total += valor;
        }
        return total / salarioRecebido.size();
    }

    public double calcularBonus() {
        return salarioBase * 0.35;
    }
    
}
