import java.util.Date;
import java.util.ArrayList;

public class Pedido {
    public int id;
    public Date dataCriacao;
    public Date dataPagamento;
    public Date dataVencimento;
    public Cliente cliente;
    public Vendedor vendedor;
    public String loja;
    public ArrayList<Item> itens = new ArrayList<>();
    
    public double calcularTotal() {
        double total = 0;
        for(Item item : itens) {
            total += item.valor;
        }
        return total;
    }
    
    public void mostrarPedido() {
        System.out.println("Pedido #" + id + " - Data: " + dataCriacao);
        System.out.println("Total: R$" + calcularTotal());
    }
}