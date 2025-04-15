import java.util.ArrayList;
import java.util.List;

public class Gerente {
    
    private String nome;
    private int idade;
    private String loja;
    private String cidade;
    private String bairro;
    private String rua;
    private double salarioBase;
    private List<Double> salarioRecebido;

    
    public Gerente(String nome, int idade, String loja, String cidade, String bairro, String rua, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new ArrayList<>();
        
        
        this.salarioRecebido.add(3500.0);
        this.salarioRecebido.add(3600.0);
        this.salarioRecebido.add(3700.0);
    }

    // apresenta as informações do gerente
    public void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
    }

    //  calcular a media dos salario
    public double calcularMedia() {
        double soma = 0.0;
        for (Double salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.size();
    }


    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}
