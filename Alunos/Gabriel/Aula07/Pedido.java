package Alunos.Gabriel.Aula07;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Pedido {

    private int id;
    private LocalDate dataCriacao;
    private LocalDate dataPagamento;
    private LocalDate dataVencimentoReserva;
    private String cliente;
    private String vendedor;
    private String loja;
    private Double[] itens;


    public Pedido(){

    }

    public Pedido(
        String cliente, 
        LocalDate dataCriacao, 
        LocalDate dataPagamento,
        LocalDate dataVencimentoReserva, 
        int id,  
        String loja,
      String vendedor) 
        {
        this.cliente = cliente;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.id = id;
        this.loja = loja;
        this.vendedor = vendedor;
        }

    public int getId() {
        return id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public LocalDate getDataVencimentoReserva() {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setDataVencimentoReserva(LocalDate dataVencimentoReserva) {
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

    

    public void calcularValorTotal(Item item1, Item item2){
        double total = item1.getValor() + item2.getValor();
        System.out.println("Valor total:"+total);



    }

    public void gerarDescricaoVenda(){
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = this.dataCriacao.format(formatar);
        System.out.println("Data de criacao:"+ dataFormatada);
    }




    
    
}

