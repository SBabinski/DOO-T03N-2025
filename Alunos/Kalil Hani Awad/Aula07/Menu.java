import java.util.Scanner;

public class Menu {
    public static Scanner scanner = new Scanner(System.in);
    private static Endereco enderecoLoja;
    private static Item item;
    private static Gerente gerente;
    private static Pedido pedido;
    private static ProcessaPedido processador;
    private int id;
    private Endereco local;
    private Endereco lojaEndereco;
    public static void main(String[] args) {
        enderecoLoja = criarEndereco();

        while (true) {
            System.out.println("1 - Endereço  2 - Adicionar Item  3 - Criar Gerente  4 - Ver Pedido 5 - Info Gerente " +
                    "6 - Mostrar Endereco 7 - Info Item 8 - Pagar 9 - Sair");
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    enderecoLoja = criarEndereco();
                    break;
                case 2:
                    adicionarItem(pedido);
                    break;
                case 3:
                    gerente = criarGerente(enderecoLoja);
                    break;
                case 4:
                    verPedido(pedido);
                    break;
                case 5:
                    infoGerente(gerente);
                    break;
                case 6:
                    mostrarEndereco(enderecoLoja);
                    break;
                case 7:
                        infoItem(item);
                    break;
                case 8:
                    if (pedido != null) {
                        processador = new ProcessaPedido(pedido);
                        pagar(processador);
                    } else {
                        System.out.println("Crie um pedido primeiro!");
                    }
                    break;
                case 9:
                    System.out.println("Você saiu");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static Endereco criarEndereco() {
        System.out.println("Estado da loja:");
        String estado = scanner.next();
        System.out.println("Cidade:");
        String cidade = scanner.next();
        System.out.println("Bairro:");
        String bairro = scanner.next();
        System.out.println("Número:");
        String numero = scanner.next();
        System.out.println("Complemento:");
        String complemento = scanner.next();
        return new Endereco(estado, cidade, bairro, numero, complemento);
    }

    private static void adicionarItem(Pedido lista) {
        System.out.println("ID do produto:");
        int id = scanner.nextInt();
        System.out.println("Nome do item:");
        String nome = scanner.next();
        System.out.println("Tipo:");
        String tipo = scanner.next();
        System.out.println("Valor:");
        double valor = scanner.nextDouble();
        System.out.println("Quantidade:");
        double quantidade = scanner.nextDouble();
        System.out.println("Data (dia mês ano):");
        int dia = scanner.nextInt();
        int mes = scanner.nextInt();
        int ano = scanner.nextInt();

        Item item = new Item(id, nome, tipo, valor, quantidade, dia, mes, ano);
        lista.AddItem(item);
    }

    private static Gerente criarGerente(Endereco endereco) {
        System.out.println("Nome do gerente:");
        String nome = scanner.next();
        System.out.println("Idade:");
        int idade = scanner.nextInt();
        System.out.println("Rua:");
        String rua = scanner.next();
        System.out.println("Salário base:");
        double salarioBase = scanner.nextDouble();

        Double[] salarios = new Double[3];
        for (int i = 0; i < 3; i++) {
            System.out.println("Salário " + (i+1) + ":");
            salarios[i] = scanner.nextDouble();
        }

        return new Gerente(
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                nome,
                idade,
                rua,
                salarioBase,
                salarios
        );
    }

    private static void infoGerente(Gerente gerente){
        System.out.println(gerente.apresentarse(enderecoLoja.toString()) +
                ", Média salarial: " + gerente.calcularMedia() +
                ", Bônus: " + gerente.calcularBonus());
    }


    private static void mostrarEndereco(Endereco endereco) {
        if (endereco != null) {
            endereco.apresentarLogradouro();
        } else {
            System.out.println("Endereço não cadastrado!");
        }
    }

    private static void infoItem(Item item) {
        item.gerarDescricao();
    }

    private static void verPedido(Pedido pedido) {
        if (pedido != null) {
            pedido.mostrarItens();
        } else {
            System.out.println("Pedido vazio!");
        }
    }

    private static void pagar(ProcessaPedido processador) {
        processador.processar();
    }
}