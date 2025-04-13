public class Vendedor {
    String nome;
    int idade;
    String loja;
    Endereco endereco;
    double salarioBase;
    double[] salarioRecebido;

    public Vendedor(String nome, int idade, Endereco endereco, String loja, double salarioBase, double[] salarioRecebido) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
        this.loja = loja;
        this.salarioBase = salarioBase;
        this.salarioRecebido = salarioRecebido;
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome + ", Idade: " + idade + ", Loja: " + loja);
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
        return salarioBase * 0.2;
    }
}