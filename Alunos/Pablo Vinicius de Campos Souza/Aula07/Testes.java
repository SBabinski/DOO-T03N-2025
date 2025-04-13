package Aula07;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Testes {
    public static void main(String[] args) {
        final Date dataHoje = new Date();

        // Criar endereço
        Endereco enderecoLoja = new Endereco("PR", "Cascavel", "Centro", "Av. Brasil", 123, "Loja 01");
        Endereco enderecoCliente = new Endereco("PR", "Cascavel", "Universitário", "Av. Carlos Gomes", 1000, "Apto 101");
        Endereco enderecoVendedor = new Endereco("PR", "Cascavel", "Floresta", "Av. Papagaios", 45, null);

        // Criar loja
        Loja loja = new Loja("My Plant", "My Plant LTDA", "12.345.678/0001-90", enderecoLoja);

        // Criar cliente
        Cliente cliente = new Cliente("Maria", 30, enderecoCliente);

        // Criar vendedor
        Vendedor vendedor = new Vendedor("João", 28, enderecoVendedor, 2500.0);
        loja.adicionarVendedor(vendedor); // também seta a loja no vendedor

        // Criar itens
        Item item1 = new Item(1, "Hortênsia", "Planta", 49.90);
        Item item2 = new Item(2, "Samambaia", "Planta", 199.90);
        Item item3 = new Item(3, "Xaxim", "Planta", 29.90);
        List<Item> itens = new ArrayList<>();
        itens.add(item1);
        itens.add(item2);
        itens.add(item3);

        // Criar pedido
        Pedido pedido = new Pedido(101, dataHoje, dataHoje, new Date(dataHoje.getTime() + 3 * 24 * 60 * 60 * 1000L), cliente, vendedor, loja, itens);

        // Testar funcionalidades
        System.out.println(pedido.gerarDescricaoVenda());

        System.out.println("\n=== Itens do Pedido ===");
        for (Item item : pedido.getItens()) {
            item.gerarDescricao();
            System.out.println();
        }

        cliente.apresentarSe();
        vendedor.apresentarSe();
        loja.apresentarSe();

        System.out.println("\n=== Testando método processar ===");
        Date dataOntem = new Date(dataHoje.getTime() - 24 * 60 * 60 * 1000L);
        Date dataAmanha = new Date(dataHoje.getTime() + 24 * 60 * 60 * 1000L);
        ProcessaPedido pp = new ProcessaPedido();

        Pedido pedidoProcessado = pp.processar(102, dataOntem, dataHoje, dataAmanha, cliente, vendedor, loja, itens);
        System.out.printf("Pedido: %d %nData Vencimento Reserva: %s%n%n",
                pedidoProcessado.getId(), pedidoProcessado.getDataVencimentoReserva());

        Pedido pedidoVencido = pp.processar(103, dataOntem, dataAmanha, dataHoje, cliente, vendedor, loja, itens);
        System.out.printf("Pedido: %d %nData Vencimento Reserva: %s%n%n",
                pedidoVencido.getId(), pedidoVencido.getDataVencimentoReserva());
    }
}
