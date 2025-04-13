package Alunos.Braian.Aula07;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pedido {
  private int id;
  private Date dataCriacao;
  private Date dataPagamento;
  private Date dataVencimentoReserva;
  private String cliente;
  private String vendedor;
  private String loja;
  private Item[] itens;

  public Pedido(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva, String cliente, String vendedor, String loja, Item[] itens) {
    this.id = id;
    this.dataCriacao = dataCriacao;
    this.dataPagamento = dataPagamento;
    this.dataVencimentoReserva = dataVencimentoReserva;
    this.cliente = cliente;
    this.vendedor = vendedor;
    this.loja = loja;
    this.itens = itens;
  }

  public int getId() {
    return id;
  }
  public Date getDataCriacao() {
    return dataCriacao;
  }
  public Date getDataPagamento() {
    return dataPagamento;
  }
  public Date getDataVencimentoReserva() {
    return dataVencimentoReserva;
  }
  public String getCliente() {
    return cliente;
  }
  public String getVendedor() {
    return vendedor;
  }

  public String getLoja() {
    return loja;
  }
  public Item[] getItens() {
    return itens;
  }
  public void setId(int id) {
    this.id = id;
  }
  public void setDataCriacao(Date dataCriacao) {
    this.dataCriacao = dataCriacao;
  }
  public void setDataPagamento(Date dataPagamento) {
    this.dataPagamento = dataPagamento;
  }
  public void setDataVencimentoReserva(Date dataVencimentoReserva) {
    this.dataVencimentoReserva = dataVencimentoReserva;
  }
  public void setCliente(String cliente) {
    this.cliente = cliente;
  }
  public void setVendedor(String vendedor) {
    this.vendedor = vendedor;
  }
  public void setLoja(String loja) {
    this.loja = loja;
  }
  public void setItens(Item[] itens) {
    this.itens = itens;
  }
  /*
   * Método para apresentar os detalhes do pedido de maneira mais detalhada
   */
  public void apresentarPedido() {
    System.out.println("Pedido ID: " + this.id);
    System.out.println("Cliente: " + this.cliente);
    System.out.println("Vendedor: " + this.vendedor);
    System.out.println("Loja: " + this.loja);
    System.out.println("Data de criação: " + this.dataCriacao);
    System.out.println("Data de pagamento: " + this.dataPagamento);
    System.out.println("Data de vencimento da reserva: " + this.dataVencimentoReserva);
    for (Item item : itens) {
      item.gerarDescricao();
    }
  }

  public double calcularValorTotal() {
    double total = 0;
    for (Item item : itens) {
      total += item.getValor();
    }
    System.out.println("Valor total do pedido: R$" + total);
    return total;
  }

  public void gerarDescricaoVenda() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    System.out.println("Pedido criado em: " + sdf.format(dataCriacao) + ", Valor total: R$" + calcularValorTotal());
  }

}