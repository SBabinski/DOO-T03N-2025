package Alunos.Gustavo_Baú.Aula07;

public class Gerente extends Pessoa {
    
    private String loja;
    private double salarioBase;
    private double[] salariosRecebidos;
    
    public Gerente(String nome, int idade, Endereco endereco, String loja, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
        this.salariosRecebidos = new double[]{3000.0, 3200.0, 3500.0};
    }
    
    public void apresentar() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
    }
    
    public double calcularMediaSalarios() {
        double soma = 0;
        for(double salario : salariosRecebidos) {
            soma += salario;
        }
        return soma / salariosRecebidos.length;
    }
    
    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}