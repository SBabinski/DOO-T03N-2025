import java.util.*;

public class Main {
    static Endereco enderecoLoja = new Endereco("PR", "Cascavel", "Centro", "Av. das Flores", 100, "Próximo ao parque");
    static Loja loja = new Loja("MyPlant", "MyPlant Ltda", "00.111.222/0001-33", enderecoLoja);

    static Endereco enderecoVendedor = new Endereco("PR", "Cascavel", "Centro", "Rua A", 10, "Fundos");
    static Vendedor vendedor1 = new Vendedor("Juninho", 18, "MyPlant", enderecoVendedor, 2500.0);

    static Endereco enderecoCliente = new Endereco("PR", "Cascavel", "Jardins", "Rua B", 20, "Apto 2");
    static Cliente cliente1 = new Cliente("Paula", 25, enderecoCliente);

    public static void main(String[] args) {
        loja.adicionaVendedor(vendedor1);
        loja.adicionaCliente(cliente1);

        menu();
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int op;
        do {
            System.out.println("\nSeja bem-vindo! Escolha a opção que deseja:");
            System.out.println("1 - Apresentação do Vendedor.\n2 - Apresentação do Cliente.\n3 - Apresentar a Loja.\n4 - Sair.\n5 - Criar Pedido (dados fictícios).");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Apresentação do Vendedor:");
                    vendedor1.apresentarse();
                    System.out.println("Média salarial: " + vendedor1.calcularMedia());
                    System.out.println("Bônus: " + vendedor1.calcularBonus());
                    break;
                case 2:
                    System.out.println("\nApresentação do Cliente:");
                    cliente1.apresentarse();
                    break;
                case 3:
                    System.out.println("\nApresentação da Loja:");
                    loja.apresentarse();
                    System.out.println("\nTotal de clientes: " + loja.contarClientes());
                    System.out.println("Total de vendedores: " + loja.contarVendedores());
                    break;
                case 5:
                    fazerPedido();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
            }
        } while (op != 4);
    }

    public static void fazerPedido() {
        ArrayList<Item> itens = new ArrayList<>();
        itens.add(new Item(1, "Vaso Autoirrigável", "Jardim", 59.90));
        itens.add(new Item(2, "Sementes de Girassol", "Semente", 12.50));

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 2); // vencimento daqui 2 dias
        Date vencimento = cal.getTime();

        ProcessarPedido processador = new ProcessarPedido();
        Pedido pedido = processador.processar(101, cliente1, vendedor1, loja, itens, vencimento);

        if (pedido != null) {
            pedido.gerarDescricaoVenda();
        }
    }
}
