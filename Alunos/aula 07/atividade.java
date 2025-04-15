import java.util.*;
import java.text.SimpleDateFormat;

class Endereco {
    String estado, cidade, bairro, rua, numero, complemento;

    public Endereco(String estado, String cidade, String bairro, String rua, String numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public void apresentarLogradouro() {
        System.out.println(rua + ", " + numero + ", " + bairro + ", " + cidade + ", " + estado + " - " + complemento);
    }
}

class Pessoa {
    String nome;
    int idade;
    Endereco endereco;

    public Pessoa(String nome, int idade, Endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }
}

class Gerente extends Pessoa {
    String loja;
    double salarioBase;
    List<Double> salarioRecebido;

    public Gerente(String nome, int idade, String loja, Endereco endereco, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
        this.salarioRecebido = Arrays.asList(5000.0, 5200.0, 5300.0);
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome + ", Idade: " + idade + ", Loja: " + loja);
    }

    public double calcularMedia() {
        return salarioRecebido.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}

class Cliente extends Pessoa {
    public Cliente(String nome, int idade, Endereco endereco) {
        super(nome, idade, endereco);
    }
}

class Vendedor extends Pessoa {
    public Vendedor(String nome, int idade, Endereco endereco) {
        super(nome, idade, endereco);
    }
}

class Item {
    int id;
    String nome, tipo;
    double valor;

    public Item(int id, String nome, String tipo, double valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public void gerarDescricao() {
        System.out.println("Item ID: " + id + ", Nome: " + nome + ", Tipo: " + tipo + ", Valor: R$" + valor);
    }
}

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
