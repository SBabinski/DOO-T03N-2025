import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Perguntando dados da loja
        System.out.println("\n********** INFORMAÇÕES DA LOJA **********");
        System.out.print("Digite o nome fantasia da loja: ");
        String nomeFantasia = scanner.nextLine();
        System.out.print("Digite a razão social da loja: ");
        String razaoSocial = scanner.nextLine();
        System.out.print("Digite o CNPJ da loja: ");
        String cnpj = scanner.nextLine();
        System.out.print("Digite a cidade da loja: ");
        String cidade = scanner.nextLine();
        System.out.print("Digite o bairro da loja: ");
        String bairro = scanner.nextLine();
        System.out.print("Digite a rua da loja: ");
        String rua = scanner.nextLine();

        // Criando a loja
        Loja loja = new Loja(nomeFantasia, razaoSocial, cnpj, cidade, bairro, rua);

        // Perguntando dados do vendedor
        System.out.println("\n********** INFORMAÇÕES DO VENDEDOR **********");
        System.out.print("Digite o nome do vendedor: ");
        String nomeVendedor = scanner.nextLine();
        System.out.print("Digite a idade do vendedor: ");
        int idadeVendedor = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Digite o nome da loja onde o vendedor trabalha: ");
        String lojaVendedor = scanner.nextLine();
        System.out.print("Digite a cidade onde o vendedor trabalha: ");
        String cidadeVendedor = scanner.nextLine();
        System.out.print("Digite o bairro onde o vendedor trabalha: ");
        String bairroVendedor = scanner.nextLine();
        System.out.print("Digite a rua onde o vendedor trabalha: ");
        String ruaVendedor = scanner.nextLine();
        System.out.print("Digite o salário base do vendedor: ");
        double salarioBaseVendedor = scanner.nextDouble();
        scanner.nextLine();  

        // Criando o vendedor
        Vendedor vendedor1 = new Vendedor(nomeVendedor, idadeVendedor, lojaVendedor, cidadeVendedor, bairroVendedor, ruaVendedor, salarioBaseVendedor);

        // Perguntando os salários recebidos
        for (int i = 0; i < 3; i++) {
            System.out.print("Digite o salário recebido " + (i + 1) + " do vendedor: ");
            double salarioRecebido = scanner.nextDouble();
            vendedor1.adicionarSalario(salarioRecebido);
        }
        scanner.nextLine(); 

        // Perguntando dados do cliente
        System.out.println("\n********** INFORMAÇÕES DO CLIENTE **********");
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = scanner.nextLine();
        System.out.print("Digite a idade do cliente: ");
        int idadeCliente = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Digite a cidade do cliente: ");
        String cidadeCliente = scanner.nextLine();
        System.out.print("Digite o bairro do cliente: ");
        String bairroCliente = scanner.nextLine();
        System.out.print("Digite a rua do cliente: ");
        String ruaCliente = scanner.nextLine();

        // Criando o cliente
        Cliente cliente1 = new Cliente(nomeCliente, idadeCliente, cidadeCliente, bairroCliente, ruaCliente);

        // Adicionando vendedores e clientes na loja
        loja.adicionarVendedor(vendedor1);
        loja.adicionarCliente(cliente1);

        // Apresentando a loja
        loja.apresentarse();

      
        loja.contarClientes();
        loja.contarVendedores();

       
        vendedor1.apresentarse();
        System.out.printf("  Média de salários: R$ %.2f\n", vendedor1.calcularMedia());
        System.out.printf("  Bônus: R$ %.2f\n", vendedor1.calcularBonus());

        // Apresentando o cliente
        cliente1.apresentarse();

        
        scanner.close();
    }
}
