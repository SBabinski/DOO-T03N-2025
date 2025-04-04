import java.util.ArrayList;
import java.util.Scanner;

class Atividade_06 {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Vendedor> vendedores = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static ArrayList<Loja> lojas = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("CADASTRO DA LOJA");
        Loja lojaPadrao = cadastrarLoja();
        lojas.add(lojaPadrao);

        while (true) {
            System.out.println("\n MENU ");
            System.out.println("1 - Cadastrar Vendedor");
            System.out.println("2 - Listar Vendedores (inclui média salarial e bônus)");
            System.out.println("3 - Cadastrar Cliente");
            System.out.println("4 - Mostrar Loja");
            System.out.println("0 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarVendedor(lojaPadrao);
                    break;
                case 2:
                    listarVendedores();
                    break;
                case 3:
                    cadastrarCliente(lojaPadrao);
                    break;
                case 4:
                    mostrarLoja(lojaPadrao);
                    break;
                case 0:
                    System.out.println("Você saiu");
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private static Loja cadastrarLoja() {
        System.out.print("Nome Fantasia: ");
        String nomeFantasia = scanner.nextLine();
        System.out.print("Razão Social: ");
        String razaoSocial = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Bairro: ");
        String bairro = scanner.nextLine();
        System.out.print("Rua: ");
        String rua = scanner.nextLine();
        return new Loja(nomeFantasia, razaoSocial, cnpj, cidade, bairro, rua);
    }

    private static void cadastrarVendedor(Loja loja) {
        System.out.print("Nome do vendedor: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Salário Base: ");
        double salario = scanner.nextDouble();
        scanner.nextLine();
        Vendedor v = new Vendedor(nome, idade, loja, loja.getCidade(), loja.getBairro(), loja.getRua(), salario);
        vendedores.add(v);
        loja.addVendedor(v);
    }

    private static void cadastrarCliente(Loja loja) {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        Cliente c = new Cliente(nome, idade, loja.getCidade(), loja.getBairro(), loja.getRua());
        clientes.add(c);
        loja.addCliente(c);
    }

    private static void listarVendedores() {
        for (Vendedor v : vendedores) {
            v.apresentarse();
            System.out.println("Média Salarial: " + v.calcularMedia());
            System.out.println("Bônus: " + v.calcularBonus());
            System.out.println("---------------------------");
        }
    }

    private static void mostrarLoja(Loja loja) {
        loja.apresentarse();
        System.out.println("Vendedores: " + loja.contarVendedores());
        System.out.println("Clientes: " + loja.contarClientes());
    }
}
