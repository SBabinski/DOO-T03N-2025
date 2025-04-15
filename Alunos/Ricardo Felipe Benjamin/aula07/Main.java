import java.util.*;

public class Main {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        int escolha;

        
        Endereco endereco = new Endereco("PR", "Cascavel", "Av. Brasil", "Centro", "Loja 1", 100);
        Gerente gerente1 = new Gerente("Fernanda", 40, "My Plant", endereco, 8000.00);
        Gerente gerente2 = new Gerente("Rogério", 45, "My Plant", endereco, 9000.00);

        List<Gerente> gerentes = new ArrayList<>();
        gerentes.add(gerente1);
        gerentes.add(gerente2);

        
        Item item1 = new Item(1, "Adubo Orgânico", "Fertilizante", 200.0);
        Item item2 = new Item(2, "Sementes de Milho", "Semente", 120.0);
        List<Item> itens = Arrays.asList(item1, item2);

        
        do {
            System.out.println("[1] Mostrar gerentes");
            System.out.println("[2] Mostrar endereço dos gerentes");
            System.out.println("[3] Calcular média e bônus de um gerente");
            System.out.println("[4] Processar pedido");
            System.out.println("[0] Sair");
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {
                case 1:
                    for (Gerente g : gerentes) {
                        g.apresentarse();
                    }
                    break;

                case 2:
                    for (Gerente g : gerentes) {
                        System.out.println("Endereço de " + g.nome + ":");
                        g.endereco.apresentarLogradouro();
                    }
                    break;

                case 3:
                    System.out.println("Escolha o gerente:");
                    for (int i = 0; i < gerentes.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + gerentes.get(i).nome);
                    }
                    escolha = scan.nextInt();
                    scan.nextLine();
                    if (escolha >= 1 && escolha <= gerentes.size()) {
                        Gerente g = gerentes.get(escolha - 1);
                        System.out.printf("Média Salarial de %s: R$%.2f\n", g.nome, g.calcularMedia());
                        System.out.printf("Bônus: R$%.2f\n", g.calcularBonus());
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;

                case 4:
                    System.out.println("Processando pedido com o gerente " + gerente1.nome + " como cliente e vendedor...");
                    Date dataCriacao = new Date();
                    Date dataPagamento = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DAY_OF_MONTH, 2);
                    Date dataVencimento = cal.getTime();

                    ProcessaPedido processador = new ProcessaPedido();
                    Pedido pedido = processador.processar(1, gerente1, gerente1, "My Plant", itens, dataCriacao, dataPagamento, dataVencimento);

                    if (pedido != null) {
                        System.out.println("\n--- Itens do Pedido ---");
                        for (Item item : pedido.itens) {
                            item.gerarDescricao();
                        }
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }
}
