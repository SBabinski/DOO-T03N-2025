package Aula07;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Pessoa {
    private Loja loja;
    private double salarioBase;
    private List<Double> salariosRecebidos;

    public Vendedor(String nome, int idade, Endereco endereco, double salarioBase) {
        super(nome, idade, endereco);
        this.salarioBase = salarioBase;
        // Valores fictícios
        this.salariosRecebidos = new ArrayList<>();
        this.salariosRecebidos.add(salarioBase);
        this.salariosRecebidos.add(salarioBase * 1.1);
        this.salariosRecebidos.add(salarioBase * 1.2);
    }

    // Sobreescreve o método da super classe com uma função adicional (apresentar a loja)
    @Override
    public void apresentarSe() {
        System.out.println("\n=== Dados do Vendedor ===");
        super.apresentarSe();
        if (loja != null) {
            loja.apresentarSe();
        }
    }

    public double calcularMedia() {
        return salariosRecebidos.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }
}