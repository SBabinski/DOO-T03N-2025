import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Pessoa {
    private Loja loja;
    private double salarioBase;
    private List<Double> salarioRecebido;
    private List<String> mesesSalario;

    public Vendedor(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new ArrayList<>();
        this.mesesSalario = new ArrayList<>();
        
        // Adding sample data
        this.mesesSalario.add("Janeiro");
        this.mesesSalario.add("Fevereiro");
        this.mesesSalario.add("Março");
        this.salarioRecebido.add(salarioBase);
        this.salarioRecebido.add(salarioBase * 0.9);
        this.salarioRecebido.add(salarioBase * 1.1);
    }

    @Override
    public void apresentarse() {
        System.out.println("Vendedor: " + nome + ", Idade: " + idade + ", Loja: " + loja.getNomeFantasia());
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
        return salarioBase * 0.2;
    }

    // Getters
    public Loja getLoja() { return loja; }
    public double getSalarioBase() { return salarioBase; }
    public List<Double> getSalarioRecebido() { return salarioRecebido; }
    public List<String> getMesesSalario() { return mesesSalario; }
}