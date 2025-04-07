import java.util.ArrayList;
import java.util.Scanner;

public class lojadavogabi { 

    public static Scanner scan = new Scanner(System.in);


    static ArrayList<String> vendas = new ArrayList<>();

    public static void main(String[] args) {

        boolean loop = true;
        double valorProduto = 0;
        double valorCliente = 0;
        double total = 0;
        double troco = 0;

        System.out.println("Bom dia, Dona Gabrielinha!!"+
        "\nSeja bem-vinda a sua calculadora!");

        while(loop){
            int escolha;

            System.out.println("\nEscolha uma das opcoes abaixo"+
            "\n1) Calcular Total"+
            "\n2) Calcular Troco"+
            "\n3) Registrar Venda"+
            "\n4) Exibir Vendas Registradas"+
            "\n5) Sair");

            escolha = scan.nextInt();

            if (escolha == 1) {
                System.out.println("Voce escolheu calcular o total.");
                System.out.print("Informe a quantidade de plantas: ");
                
                int qtdProdutos = scan.nextInt();
                System.out.print("Informe o preco da planta: ");
                valorProduto = scan.nextDouble();

                total = valorProduto * qtdProdutos;
                double desconto = 0;

                
                if (qtdProdutos > 10) {
                    desconto = total * 0.05;
                    total -= desconto;
                    System.out.printf("Voce recebeu um desconto de 5%%! O valor com desconto e: R$ %.2f\n", total);
                }

                
                vendas.add("Venda: Quantidade = " + qtdProdutos + ", Total = R$ " + total + ", Desconto = R$ " + desconto);
                System.out.printf("O total da compra e: R$ %.2f\n", total);
            }
        
            else if(escolha == 2){
                System.out.println("Você escolheu calcular o troco.");

                System.out.printf("O total da compra deu: R$ %.2f\n", total);
                        
                System.out.println("Insira o valor que o cliente lhe deu: ");
                valorCliente = scan.nextDouble();
                
                troco = valorCliente - total;

                if(valorCliente < total){
                    System.out.printf("Dinheiro entregue pelo cliente não é suficiente.\nFalta a quantia de: R$ %.2f\n", -troco);
                }
                else if(valorCliente == total){
                    System.out.println("Não há troco! O valor está exato.");
                }
                else{
                    System.out.printf("Você deve retornar o valor de: R$ %.2f\n", troco);
                }

            }
            else if (escolha == 3) {
                System.out.println("Você escolheu registrar uma venda.");
                System.out.print("Informe a quantidade de plantas: ");
                int qtdProdutos = scan.nextInt();
                System.out.print("Informe o preço da planta: ");
                valorProduto = scan.nextDouble();

                total = valorProduto * qtdProdutos;
                double desconto = 0;

                
                if (qtdProdutos > 10) {
                    desconto = total * 0.05;
                    total -= desconto;
                }

                
                vendas.add("Venda: Quantidade = " + qtdProdutos + ", Total = R$ " + total + ", Desconto = R$ " + desconto);
                System.out.printf("Venda registrada com sucesso! Total com desconto (se houver): R$ %.2f\n", total);
            }

            else if (escolha == 4) {
                System.out.println("Exibindo vendas registradas:");
                if (vendas.isEmpty()) {
                    System.out.println("Nenhuma venda registrada até o momento.");
                } else {
                    for (String venda : vendas) {
                        System.out.println(venda);
                    }
                }
            }
        
            else if(escolha == 5){
                System.out.println("Saindo... Até logo!");
                loop = false;
            }
            else {
                System.out.println("Opção inválida, tente novamente.");
            }
        }
    }
}
