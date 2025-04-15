import java.util.ArrayList;
import java.util.List;

public class Gerente extends Pessoa {
    private String loja;
    private double salarioBase;
    private List<Double> salarioRecebido;

    public Gerente(String nome, int idade, Endereco endereco, String loja, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new ArrayList<>();
        inicializarSalarios();
    }

    private void inicializarSalarios() {
        salarioRecebido.add(salarioBase);
        salarioRecebido.add(salarioBase * 0.9);
        salarioRecebido.add(salarioBase * 1.1);
    }

    @Override
    public void apresentarse() {
        System.out.println("Gerente: " + nome + ", Idade: " + idade + ", Loja: " + loja);
    }

    public double calcularMedia() {
        return salarioRecebido.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}