import java.util.ArrayList;
import java.util.Date;

public class ProcessaPedido {
    public Pedido processar(int id, Cliente cliente, Vendedor vendedor, String loja, ArrayList<Item> itens) {
        Pedido pedido = new Pedido();
        pedido.id = id;
        pedido.dataCriacao = new Date();
        pedido.dataVencimento = new Date(pedido.dataCriacao.getTime() + (3 * 24 * 60 * 60 * 1000)); // +3 dias
        pedido.cliente = cliente;
        pedido.vendedor = vendedor;
        pedido.loja = loja;
        pedido.itens = itens;
        
        // Simula pagamento após 2 dias (para teste)
        Date dataPagamento = new Date(pedido.dataCriacao.getTime() + (2 * 24 * 60 * 60 * 1000));
        if(confirmarPagamento(pedido, dataPagamento)) {
            pedido.dataPagamento = dataPagamento;
            System.out.println("Pagamento confirmado!");
        } else {
            System.out.println("Pagamento não realizado - Pedido vencido!");
        }
        
        return pedido;
    }
    
    private boolean confirmarPagamento(Pedido pedido, Date dataPagamento) {
        return dataPagamento.before(pedido.dataVencimento);
    }
}