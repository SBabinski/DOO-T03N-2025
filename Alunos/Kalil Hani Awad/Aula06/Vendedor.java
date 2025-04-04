public class Vendedor {
    private String nome;
    private int idade;
    private Loja loja;
    private String cidade;
    private String bairro;
    private String rua;
    private double salarioBase;
    private double[] salarioRecebido;

    public Vendedor(String nome, int idade, Loja loja, String cidade, String bairro, String rua, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new double[]{3000, 3500, 4000};
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome +
                ", Idade: " + idade +
                ", Loja: " + loja.getNomeFantasia());
    }

    public double calcularMedia() {
        double soma = 0;
        for (double s : salarioRecebido) {
            soma += s;
        }
        return soma / salarioRecebido.length;
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }

    public String getNome() {
        return nome;
    }

    public Loja getLoja() {
        return loja;
    }
}
