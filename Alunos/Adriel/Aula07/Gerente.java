import java.util.ArrayList;
import java.util.List;

public class Gerente extends Pessoa {
    private Loja loja;
    private double salarioBase;
    private List<Double> salarioRecebido;

    public Gerente(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new ArrayList<>();
        // Adding 3 sample salaries
        this.salarioRecebido.add(salarioBase);
        this.salarioRecebido.add(salarioBase * 0.9);
        this.salarioRecebido.add(salarioBase * 1.1);
    }

    @Override
    public void apresentarse() {
        System.out.println("Gerente: " + nome + ", Idade: " + idade + ", Loja: " + loja.getNomeFantasia());
    }

    public double calcularMedia() {
        if (salarioRecebido.isEmpty()) return 0;
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.size();
    }

    public double calcularBonus() {
        return salarioBase * 0.35;
    }

    // Getters
    public Loja getLoja() { return loja; }
    public double getSalarioBase() { return salarioBase; }
    public List<Double> getSalarioRecebido() { return salarioRecebido; }
}