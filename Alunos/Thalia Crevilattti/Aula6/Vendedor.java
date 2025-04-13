import java.util.Arrays;
// classe vendendor

public class Vendedor {
    private String nome;
    private int idade;
    private String loja;
    private String cidade;
    private String bairro;
    private String rua;
    private int salarioBase;
    private int[] salarioRecebido;

    public Vendedor(String nome, int idade, String loja, String cidade, String bairro, String rua, int salarioBase, int[] salarioRecebido) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;
        this.salarioRecebido = salarioRecebido;
    }

    public void dadosVendedor() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
        System.out.println("Cidade: " + cidade);
        System.out.println("Bairro: " + bairro);
        System.out.println("Rua: " + rua);
        System.out.println("Salário Base: R$ " + salarioBase);
        System.out.println("Salários Recebidos: " + Arrays.toString(salarioRecebido));
    }

    public double calcularMedia() {
        int soma = 0;
        for (int salario : salarioRecebido) {
            soma += salario;
        }
        double media = (double) soma / salarioRecebido.length;
        System.out.println("Média dos salários: R$ " + media);
        return media;
    }

    public double calcularBonus() {
        double bonus = salarioBase * 0.2;
        System.out.println("Bônus: R$ " + bonus);
        return bonus;
    }

    public static void main(String[] args) {
        Vendedor vendedor1 = new Vendedor(
            "Carlos Silva",
            30,
            "Loja Central",
            "São Paulo",
            "Centro",
            "Av. Principal",
            2500,
            new int[]{2500, 2700, 2600}
        );

        vendedor1.dadosVendedor();
        vendedor1.calcularMedia();
        vendedor1.calcularBonus();
    }
}

