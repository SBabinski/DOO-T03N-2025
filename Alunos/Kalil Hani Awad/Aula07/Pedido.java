import java.util.ArrayList;

public class Pedido extends Item {
    private String datacriacao;
    private String dataPagamento;
    private String dataVencimentoReserva;
    private String cliente;
    private String vendedor;
    private Endereco Lojona;
    private int Id;
    private String nome;
    private String tipo;
    private ArrayList<Item> Itens = new ArrayList<>();

    public Pedido(int id, String nome, String tipo, Double valor, Double quantidade, int dia, int mes, int ano, String datacriacao, String dataPagamento, String dataVencimentoReserva) {
        super(id, nome, tipo, valor, quantidade, dia, mes, ano);
        this.datacriacao = datacriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
    }

    public String getDatacriacao() {

        return datacriacao;
    }

    public String getDataVencimentoReserva() {

        return dataVencimentoReserva;
    }

    public String getDataPagamento() {

        return dataPagamento;
    }

    public Double calcularValorTotal() {

        return getQuantidade() * getValor();
    }


    public String gerarDescricaoVenda() {
        return "Data:"+getDia()+"/"+getMes()+"/"+getAno() +"," +"Valor_total:"+getQuantidade() * getValor();
    }
    public void AddItem(Item Produto){
        Itens.add(Produto);
    }
    public void mostrarItens(){
        for(Item obj : Itens){
            System.out.println(obj);
        }
    }

}