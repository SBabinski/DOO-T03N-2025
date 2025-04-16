import java.util.ArrayList;
import java.util.List;

public class Gerente extends Pessoa {
    String loja;
    Endereco endereco;
    float salarioBase;
    List<Float> salarioRecebido = new ArrayList<>();

    public Gerente(String nome, int idade, String loja, Endereco endereco, float salarioBase) {
        super(nome, idade);
        this.loja = loja;
        this.endereco = endereco;
        this.salarioBase = salarioBase;

        // Salários fictícios
        salarioRecebido.add(5000f);
        salarioRecebido.add(5200f);
        salarioRecebido.add(5100f);
    }

    @Override
    public void apresentarse() {
        super.apresentarse();
        System.out.println("Loja: " + loja);
        endereco.apresentarLogradouro();
    }

    public void calcularMedia() {
        float soma = 0;
        for (float salario : salarioRecebido) {
            soma += salario;
        }
        System.out.println("Média Salarial: " + (soma / salarioRecebido.size()));
    }

    public void calcularBonus() {
        System.out.println("Bônus: " + (salarioBase * 0.35f));
    }
}
