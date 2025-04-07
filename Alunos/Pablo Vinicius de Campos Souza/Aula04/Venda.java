package Aula04;

import java.time.LocalDate;

public class Venda {
    private static final int PORCENTAGEM_DESCONTO = 5;
    private static int contadorId = 1;

    private final int id;
    private final LocalDate data;
    private final Planta planta;
    private final int quantidade;
    private final double valorTotal;
    private final double valorDesconto;
    private double valorPago;

    public Venda(LocalDate data, Planta planta, int quantidade) {
        this.id = contadorId;
        contadorId++;
        this.planta = planta;
        this.quantidade = quantidade;
        this.data = data;
        this.valorTotal = calcularValorTotal();
        this.valorDesconto = calcularValorDesconto();
    }

    public double calcularValorTotal() {
        double precoUnitario = planta.getPreco();
        if (quantidade > 10) {
            return precoUnitario * quantidade * (100 - PORCENTAGEM_DESCONTO) / 100;
        }
        return precoUnitario * quantidade;
    }

    public double calcularValorDesconto() {
        double precoUnitario = planta.getPreco();
        if (quantidade > 10) {
            return precoUnitario * quantidade - valorTotal;
        }
        return 0; // não teve desconto
    }

    public double calcularTroco() {
        if (valorTotal < valorPago) {
            return valorPago - valorTotal;
        }
        return 0; // não precisa dar troco
    }

    public String identificarVenda() {
        return String.format("ID: [%d] - %dx %s (%s)", id, quantidade, planta.getNome(), LocalDateUtil.localDateParaString(data));
    }

    public String detalharVenda() {
        return String.format("Data da venda: %s %n", LocalDateUtil.localDateParaString(data)) +
                String.format("Nome da planta: %s %n", planta.getNome()) +
                String.format("Preço da planta: R$ %s %n", planta.getPreco()) +
                String.format("Quantidade: %d %n", quantidade) +
                String.format("Valor total das plantas: R$ %.2f %n", valorTotal + valorDesconto) +
                String.format("Valor do desconto: R$ %.2f %n", valorDesconto) +
                String.format("Valor cobrado: R$ %.2f %n", valorTotal) +
                String.format("Valor pago pelo cliente: R$ %.2f %n", valorPago) +
                String.format("Valor do troco: R$ %.2f %n", calcularTroco());
    }

    // --------------Getters e Setters--------------
    public int getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }
}
