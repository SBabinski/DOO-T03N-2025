package Alunos.Gustavo_Baú.Aula07;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    
    private int id;
    private Date dataCriacao;
    private Date dataPagamento;
    private Date dataVencimento;
    private Pessoa cliente;
    private Pessoa vendedor;
    private String loja;
    private List<Item> itens;
    
    public Pedido(int id, Pessoa cliente, Pessoa vendedor, String loja) {
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.dataCriacao = new Date();
        this.dataVencimento = new Date(dataCriacao.getTime() + (3L * 24 * 60 * 60 * 1000));
        this.itens = new ArrayList<>();
    }
    
    public Date getDataVencimento() {
        return this.dataVencimento;
    }
    
    public void adicionarItem(Item item) {
        itens.add(item);
    }
    
    public double calcularTotal() {
        double total = 0;
        for(Item item : itens) {
            total += item.getValor();
        }
        return total;
    }
    
    public void mostrarPedido() {
        System.out.println("Pedido #" + id + " - " + loja);
        System.out.println("Data: " + dataCriacao);
        System.out.println("Valor total: R$" + calcularTotal());
    }
}