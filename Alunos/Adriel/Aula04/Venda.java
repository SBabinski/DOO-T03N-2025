package Alunos.Adriel.Aula04;

public class Venda {
    int quantidade;
    double valorVenda;
    double desconto;
    int dia, mes;

    public Venda(int quantidade, double valorVenda, double desconto, int dia, int mes) {
        this.quantidade = quantidade;
        this.valorVenda = valorVenda;
        this.desconto = desconto;
        this.dia = dia;
        this.mes = mes;
    }
}