import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Pessoa {
    String loja;
    float salarioBase;
    List<Float> salarioRecebido = new ArrayList<>();
    Endereco endereco;

    public Vendedor(String nome, int idade, String loja, float salarioBase) {
        super(nome, idade);
        this.loja = loja;
        this.salarioBase = salarioBase;
        this.endereco = new Endereco("PR", "Cascavel", "Centro", "Av. Brasil", 1000, "Loja 5");

        salarioRecebido.add(2000f);
        salarioRecebido.add(2100f);
        salarioRecebido.add(2200f);
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
        System.out.println("Média salarial: " + (soma / salarioRecebido.size()));
    }

    public void calcularBonus() {
        System.out.println("Bônus: " + (salarioBase * 0.2));
    }
}
