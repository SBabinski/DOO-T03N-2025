public class Gerente {
    String nome;
    int idade;
    String loja;
    String cidade;
    String bairro;
    String rua;
    double salarioBase;
    double[] salarioRecebido = new double[3];

    public Gerente(String nome, int idade, String loja, String cidade, String bairro, String rua, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;

        salarioRecebido[0] = salarioBase + 200; 
        salarioRecebido[1] = salarioBase + 150;
        salarioRecebido[2] = salarioBase + 300;
    }

    public void apresentarSe() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
    }

    public double calcularMedia() {
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.length;
    }

    public double calcularBonus() {
        return salarioBase * 0.35;
    }

    public void mostrarSalarios() {
        System.out.println("Lançamentos de Salário:");
        for (int i = 0; i < salarioRecebido.length; i++) {
            System.out.println(" - Mês " + (i + 1) + ": R$" + salarioRecebido[i]);
        }
        System.out.printf("Média Salarial: R$%.2f%n", calcularMedia());
        System.out.printf("Bônus: R$%.2f%n", calcularBonus());
    }
}