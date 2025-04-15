import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
       
        Vendedor vendedor1 = new Vendedor();
        Vendedor vendedor2 = new Vendedor("Maria Oliveira", 28, "Gabrielinha's Garden Center", 
            "Cascavel", "Centro", "R. Paraná", 5250.0);

        // Criando Clientes
        Cliente cliente1 = new Cliente("Ana Santos", 25, "Cascavel", "Centro", "R. Paraná");
        Cliente cliente2 = new Cliente("Carlos Pereira", 35, "Cascavel", "Cascavel Velho", "R. Gilberto Freire");

        // Criando Loja
        Loja loja = new Loja("My Plant", "Gabrielinha's Garden Center LTDA", 
            "12.345.678/0001-90", "Cascavel", "Centro", "R. Paraná");

        Scanner scanner = new Scanner(System.in);
        int opcaoPrincipal;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Apresentar Loja e Vendedores");
            System.out.println("2 - Apresentar Clientes");
            System.out.println("3 - Calculadora");
            System.out.println("4 - Visualizar Salários dos Vendedores");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opcaoPrincipal = scanner.nextInt();

            switch (opcaoPrincipal) {
                case 1:
                    System.out.println("\n=== INFORMAÇÕES DA LOJA ===");
                    System.out.println("A My Plant é uma loja de plantas e jardinagem com uma equipe de vendedores especializados e dedicados a atender os clientes" + 
                    "aqui está nossas informações.");
                    loja.apresentarLoja();
                    System.out.println("\n=== VENDEDORES ===");
                    System.out.println("A loja conta com os seguintes vendedores:");
                    vendedor1.apresentarVendedor();
                    vendedor2.apresentarVendedor();
                    break;
                case 2:
                    System.out.println("\n=== CLIENTES ===");
                    System.out.println("Aqui estão alguns de nossos clientes mais fiéis:");
                    cliente1.apresentarCliente();
                    cliente2.apresentarCliente();
                    break;
                case 3:
                    menuCalculadora(scanner);
                    break;
                case 4:
                    visualizarSalariosVendedores(vendedor1, vendedor2);
                    break;
                case 5:
                    System.out.println("Saindo... Obrigado por visitar a My Plant!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcaoPrincipal != 5);

        scanner.close();
    }

    private static void visualizarSalariosVendedores(Vendedor vendedor1, Vendedor vendedor2) {
    System.out.println("\n=== SALÁRIOS RECEBIDOS ===");
    
    System.out.println("\nVendedor: " + vendedor1.getNome());
    System.out.println("Salários recebidos por mês:");
    
    // Obter as listas de salários e meses
    List<Double> salarios = vendedor1.getSalarioRecebido();
    List<String> meses = vendedor1.getMesesSalario();
    
    // Garantir que temos a mesma quantidade de meses e salários
    int tamanho = Math.min(salarios.size(), meses.size());
    
    for (int i = 0; i < tamanho; i++) {
        System.out.printf("%s: R$ %.2f\n", meses.get(i), salarios.get(i));
    }
    
    System.out.printf("Média salarial: R$ %.2f\n", vendedor1.calcularMedia());
    System.out.printf("Bônus: R$ %.2f\n", vendedor1.calcularBonus());
    
    // Repetir para o segundo vendedor
    System.out.println("\nVendedor: " + vendedor2.getNome());
    System.out.println("Salários recebidos por mês:");
    
    salarios = vendedor2.getSalarioRecebido();
    meses = vendedor2.getMesesSalario();
    tamanho = Math.min(salarios.size(), meses.size());
    
    for (int i = 0; i < tamanho; i++) {
        System.out.printf("%s: R$ %.2f\n", meses.get(i), salarios.get(i));
    }
    
    System.out.printf("Média salarial: R$ %.2f\n", vendedor2.calcularMedia());
    System.out.printf("Bônus: R$ %.2f\n", vendedor2.calcularBonus());
}

    private static void menuCalculadora(Scanner scanner) {
        int opcaoCalculadora;
        
        do {
            System.out.println("\n--- Calculadora da Dona Gabrielinha ---");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcaoCalculadora = scanner.nextInt();
            
            switch (opcaoCalculadora) {
                case 1:
                    calcularPrecoTotal(scanner);
                    break;
                case 2:
                    calcularTroco(scanner);
                    break;
                case 3:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcaoCalculadora != 3);
    }
    
    private static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade da planta: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();
        
        double total = quantidade * precoUnitario;
        System.out.printf("Preço total da compra: R$ %.2f\n", total);
    }
    
    private static void calcularTroco(Scanner scanner) {
        System.out.print("Digite o valor recebido do cliente: ");
        double valorRecebido = scanner.nextDouble();
        System.out.print("Digite o valor total da compra: ");
        double valorCompra = scanner.nextDouble();
        
        if (valorRecebido < valorCompra) {
            System.out.println("Valor recebido insuficiente!");
        } else {
            double troco = valorRecebido - valorCompra;
            System.out.printf("Troco a ser dado: R$ %.2f\n", troco);
        }
    }
}