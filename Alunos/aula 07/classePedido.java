import java.util.*;
import java.text.SimpleDateFormat;

class Pedido {
    int id;
    Date dataCriacao, dataPagamento, dataVencimentoReserva;
    Cliente cliente;
    Vendedor vendedor;
    String loja;
    List<Item> itens;

    public Pedido(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva,
                  Cliente cliente, Vendedor vendedor, String loja, List<Item> itens) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
    }

    public double calcularValorTotal() {
        return itens.stream().mapToDouble(i -> i.valor).sum();
    }

    public void gerarDescricaoVenda() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Pedido criado em: " + sdf.format(dataCriacao) + ", Valor Total: R$" + calcularValorTotal());
    }
}

class ProcessaPedido {
    public Pedido processar(int id, Cliente cliente, Vendedor vendedor, String loja, List<Item> itens,
                            Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva) {

        if (!confirmarPagamento(dataVencimentoReserva)) {
            throw new IllegalArgumentException("Reserva vencida. Não é possível confirmar o pagamento.");
        }

        return new Pedido(id, dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja, itens);
    }

    private boolean confirmarPagamento(Date dataVencimentoReserva) {
        Date hoje = new Date();
        return !hoje.after(dataVencimentoReserva);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Menu simples
        System.out.println("1. Criar pedido (dados fakes)");
        int opcao = sc.nextInt();

        if (opcao == 1) {
            Endereco endereco = new Endereco("SP", "São Paulo", "Centro", "Rua das Flores", "100", "Sala 3");
            Cliente cliente = new Cliente("João", 30, endereco);
            Vendedor vendedor = new Vendedor("Maria", 28, endereco);
            List<Item> itens = Arrays.asList(
                    new Item(1, "Vaso", "Decoração", 120.0),
                    new Item(2, "Sementes", "Jardinagem", 25.0)
            );
            Date hoje = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(hoje);
            cal.add(Calendar.DATE, 5);
            Date vencimento = cal.getTime();

            ProcessaPedido processador = new ProcessaPedido();
            Pedido pedido = processador.processar(1, cliente, vendedor, "Loja Central", itens, hoje, hoje, vencimento);
            pedido.gerarDescricaoVenda();
        }
    }
}