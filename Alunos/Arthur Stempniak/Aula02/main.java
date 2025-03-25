import java.util.Scanner;

public class main { 

    public static Scanner scan = new Scanner(System.in);

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
            "\n3) Sair");

            escolha = scan.nextInt();

            if (escolha == 1) {
                System.out.println("Você escolheu calcular o total.");
                System.out.print("Informe a quantidade de produtos: ");
                
                int qtdProdutos = scan.nextInt();
            
                total = 0;
            
                for (int i = 0; i < qtdProdutos; i++) {
                    System.out.print("Informe o valor do " + (i + 1) + "° produto: ");
                    valorProduto = scan.nextDouble();

                    total += valorProduto;
                }
            
                System.out.printf("O total da compra é: R$ %.2f\n", total);
            }
            else if(escolha == 2){

                System.out.println("Voce escolheu calcular o troco.");

                    System.out.printf("O total da compra deu: R$ %.2f\n", total);
                        
                    System.out.println("Insira o valor que o cliente lhe deu");
                        valorCliente = scan.nextInt();
                
                troco = valorCliente - total;

                if(valorCliente<total){
                        System.out.println("Dinheiro entregue pelo cliente não é suficiente"
                        +"\nFalta a quantia de : R$ "+  troco);
                }
                else if(valorCliente==total){
                    System.out.println("Voce deve retornar de troco"+" "+ troco);
                }
                else{
                    System.out.println("Voce deve retornar o valor de "+" " + troco);
                }

            }
            else if(escolha == 3){
                System.out.println("Saindo... Ate logo!");
                loop = false;
            }
            else {
                System.out.println("Opcao invalida, tente novamente.");
            }
        }
    }
}

