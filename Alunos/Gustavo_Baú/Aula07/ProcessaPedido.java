package Alunos.Gustavo_Baú.Aula07;

import java.util.Date;
import java.util.List;

public class ProcessaPedido {
    public Pedido criarPedido(int id, Pessoa cliente, Pessoa vendedor, String loja, List<Item> itens) {
        Pedido pedido = new Pedido(id, cliente, vendedor, loja);
        
        for(Item item : itens) {
            pedido.adicionarItem(item);
        }
        
        if(confirmarPagamento(pedido)) {
            System.out.println("Pagamento confirmado!");
        } else {
            System.out.println("Pagamento não confirmado (fora do prazo)");
        }
        
        return pedido;
    }
    
    private boolean confirmarPagamento(Pedido pedido) {
        Date hoje = new Date();
        return !hoje.after(pedido.getDataVencimento());  // Corrigido usando o getter
    }
}