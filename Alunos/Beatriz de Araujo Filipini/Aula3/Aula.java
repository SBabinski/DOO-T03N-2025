import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Aula {

    static class Venda {
        int quantidade;
        double precoUnitario;
        double valorTotal;
        double desconto;

        public Venda(int quantidade, double precoUnitario, double valorTotal, double desconto) {
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
            this.valorTotal = valorTotal;
            this.desconto = desconto;
        }

        @Override
        public String toString() {
            return "Quantidade: " + quantidade + " | Preço Unitário: " + precoUnitario 
                    + " | Valor Total: " + valorTotal + " | Desconto: " + desconto;
        }
    }

    static class CalculadoraVenda {
        List<Venda> registroVendas;

        public CalculadoraVenda() {
           
            registroVendas = new ArrayList<>();
        }

       
        public double[] calcularValorTotal(int quantidade, double precoUnitario) {
            double valorTotal = quantidade * precoUnitario;
            double desconto = 0;

          
            if (quantidade > 10) {
                desconto = valorTotal * 0.05; 
                valorTotal -= desconto;
            }

            return new double[]{valorTotal, desconto};
        }

        
        public void registrarVenda(int quantidade, double precoUnitario) {
            double[] resultados = calcularValorTotal(quantidade, precoUnitario);
            double valorTotal = resultados[0];
            double desconto = resultados[1];

            
            Venda venda = new Venda(quantidade, precoUnitario, valorTotal, desconto);
            registroVendas.add(venda);
        }

        
        public void exibirRegistroVendas() {
            if (registroVendas.isEmpty()) {
                System.out.println("Nenhuma venda registrada.");
            } else {
                for (Venda venda : registroVendas) {
                    System.out.println(venda);
                }
            }
        }

        public void menu() {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Realizar venda");
                System.out.println("2. Exibir registro de vendas");
                System.out.println("3. Sair");

                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();

                if (opcao == 1) {
                    System.out.print("Informe a quantidade de plantas: ");
                    int quantidade = scanner.nextInt();
                    System.out.print("Informe o preço unitário da planta: ");
                    double precoUnitario = scanner.nextDouble();
                    registrarVenda(quantidade, precoUnitario);
                    System.out.println("Venda registrada com sucesso!");
                } else if (opcao == 2) {
                    exibirRegistroVendas();
                } else if (opcao == 3) {
                    System.out.println("Saindo do sistema...");
                    break;
                } else {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }

            scanner.close();
        }
    }

    public static void main(String[] args) {
        
        CalculadoraVenda calculadora = new CalculadoraVenda();

        
        calculadora.menu();
    }
}
