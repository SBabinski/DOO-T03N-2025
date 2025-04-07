import java.util.*;

public class Calculadora {

    static Scanner scanner = new Scanner(System.in);
    static Map<String, Integer> vendasPorDia = new HashMap<>();


    public static void main(String[] args) {

        while (true) {
            System.out.println("\n1. Calcular conta");
            System.out.println("2. Calcular troco");
            System.out.println("3. Registrar venda");
            System.out.println("4. Ver registro de vendas");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Digite um número válido!");
                scanner.next(); // Limpa a entrada inválida
                continue;
            }

            switch (opcao) {
                case 1:
                    conta();
                    break;
                case 2:
                    troco();
                    break;
                case 3:
                    registrarVendas();
                    break;
                case 4:
                    mostrarVendas();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    //  Calcular Conta
    public static void conta() {
        try {
            System.out.print("Quantas plantas você pegou? ");
            int plantas = scanner.nextInt();

            System.out.print("Qual o preço da planta? ");
            float preco = scanner.nextFloat();

            float total = plantas * preco;
            float desconto = 0;

            if (plantas > 10) {
                desconto = total * 0.05f; // 5% de desconto
                total -= desconto;
                System.out.println("Você ganhou 5% de desconto!");
            }

            System.out.printf("O total é: R$ %.2f\n", total);


        } catch (InputMismatchException e) {
            System.out.println("Digite números válidos!");
            scanner.next();
        }
    }

    // Calcular Troco
    public static void troco() {
        try {
            System.out.print("Qual o valor da compra? ");
            float valor = scanner.nextFloat();

            System.out.print("Quanto você pagou? ");
            float pago = scanner.nextFloat();

            if (pago < valor) {
                System.out.println("Valor pago insuficiente.");
            } else {
                float troco = pago - valor;
                System.out.printf("O troco é: R$ %.2f\n", troco);
            }

        } catch (InputMismatchException e) {
            System.out.println("Digite números válidos!");
            scanner.next();
        }
    }

    // Registro de Vendas
    public static void registrarVendas() {
        try {
            System.out.print("Informe o dia da venda: ");
            int dia = scanner.nextInt();

            System.out.print("Informe o mês da venda: ");
            int mes = scanner.nextInt();

            System.out.print("Informe a quantidade de vendas: ");
            int quant = scanner.nextInt();

            String chave = dia + "-" + mes;

            vendasPorDia.put(chave, vendasPorDia.getOrDefault(chave, 0) + quant);

        } catch (InputMismatchException e) {
            System.out.println("Digite números válidos!");
            scanner.next();


        }
    }

    // Mostrar vendas
    public static void mostrarVendas() {

        if (vendasPorDia.isEmpty()) {
            System.out.println("Nenhuma venda registrada");
            return;
        }

        System.out.println("Registro de Vendas (Dia-Mês: Quantidade):");
        for (Map.Entry<String, Integer> entry : vendasPorDia.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue() + " vendas");
        }

        // Consultar venda específica
        System.out.print("\nDeseja consultar um dia específico? (s/n): ");
        char opcao = scanner.next().toLowerCase().charAt(0);
        if (opcao == 's') {
            try {
                System.out.print("Informe o dia: ");
                int dia = scanner.nextInt();
                System.out.print("Informe o mês: ");
                int mes = scanner.nextInt();

                String chave = dia + "-" + mes;
                int vendas = vendasPorDia.getOrDefault(chave, 0);

                System.out.println("Vendas no dia " + dia + "/" + mes + ": " + vendas);
            } catch (InputMismatchException e) {
                System.out.println("Digite números válidos!");
                scanner.next();


            }
        }
    }
}
