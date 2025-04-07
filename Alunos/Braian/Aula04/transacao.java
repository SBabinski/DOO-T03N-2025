package Alunos.Braian.Aula04;

public class transacao {
    private int quantidadeDePlantas;
    private double precoUnitario;
    private double valorTotal;
    private double desconto;
    private String dataVenda;

    public transacao(int quantidadeDePlantas, double precoUnitario, String dataVenda) {
        this.quantidadeDePlantas = quantidadeDePlantas;
        this.precoUnitario = precoUnitario;
        this.dataVenda = dataVenda;
        calcularValorTotal();
    }

    private void calcularValorTotal() {
        valorTotal = quantidadeDePlantas * precoUnitario;
        desconto = (quantidadeDePlantas > 10) ? valorTotal * 0.05 : 0;
        valorTotal -= desconto;
    }

    public int getQuantidadeDePlantas() {
        return quantidadeDePlantas;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getDesconto() {
        return desconto;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setQuantidadeDePlantas(int quantidadeDePlantas) {
        this.quantidadeDePlantas = quantidadeDePlantas;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }
}
