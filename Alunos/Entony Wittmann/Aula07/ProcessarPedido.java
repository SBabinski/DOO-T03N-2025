import java.util.ArrayList;
import java.util.Date;

public class ProcessarPedido {
    public Pedido processar(int id, Cliente cliente, Vendedor vendedor, Loja loja, ArrayList<Item> itens, Date dataVencimentoReserva) {
        Date hoje = new Date();
        if (!confirmarPagamento(dataVencimentoReserva, hoje)) {
            System.out.println("Pagamento não confirmado: reserva vencida.");
            return null;
        }
        return new Pedido(id, hoje, hoje, dataVencimentoReserva, cliente, vendedor, loja, itens);
    }

    private boolean confirmarPagamento(Date vencimento, Date atual) {
        return atual.before(vencimento) || atual.equals(vencimento);
    }
}
