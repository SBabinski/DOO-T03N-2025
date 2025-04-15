import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);

       
        Endereco endereco = new Endereco("SP", "São Paulo", "Centro", 100, "Loja 1");

        
        Pessoa cliente = new Pessoa("Maria Silva", 30, endereco);

        
        Pessoa vendedor = new Pessoa("João Pereira", 35, endereco);

        
        ProcessaPedido processador = new ProcessaPedido();

        while (true) {

            // Menu de opções
            System.out.println("\nMENU:");
            System.out.println("1 - Criar Pedido");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            if (opcao == 1) {

                // Criando data atual
                Date dataCriacao = new Date();

                // Perguntar qual a data de vencimento da reserva
                System.out.print("Digite a data de vencimento (formato: dd/MM/yyyy): ");
                String dataVencimentoStr = scanner.nextLine();

                // Converter para string a data
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date dataVencimentoReserva = dateFormat.parse(dataVencimentoStr);

                // Cria a lista de itens
                List<Item> itens = new ArrayList<>();

                // Adicionar itens ao pedido
                String continuar;
                do {
                    
                    System.out.print("Digite o ID do item: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); 

                    System.out.print("Digite o nome do item: ");
                    String nome = scanner.nextLine();

                    System.out.print("Digite o tipo do item: ");
                    String tipo = scanner.nextLine();

                    System.out.print("Digite o valor do item: ");
                    double valor = scanner.nextDouble();
                    scanner.nextLine(); 

                    // Adicionar o item a lista
                    itens.add(new Item(id, nome, tipo, valor));

                    // Perguntar se deseja adicionar mais um item
                    System.out.print("Deseja adicionar outro item? (s/n): ");
                    continuar = scanner.nextLine();
                } while (continuar.equalsIgnoreCase("s"));

                // Criando o pedido com os itens adicionados
                Pedido pedido = processador.processar(1, dataCriacao, dataVencimentoReserva, cliente, vendedor, "Loja A", itens);

                // Gerar a descrição do pedido
                pedido.gerarDescricaoVenda();
            } else if (opcao == 0) {

                // Sair do programa
                
                System.out.println("Saindo do programa...");
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}
