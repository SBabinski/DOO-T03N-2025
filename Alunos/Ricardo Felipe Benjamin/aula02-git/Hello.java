import java.util.Scanner;

public class Hello {


    public static void HelloWorld(){
        System.out.println("Hello World");
    }

    public static void OlaMundo(){
        System.out.println("Ola Mundo");
    }

    public static void OlaQueTal(){
        System.out.println("Ola Que Tal");
    }

    public static void main(String[] args) {

      Scanner sc =  new Scanner(System.in);
      int option = 0;

        while(option != 4){ 
            System.out.println("Escolha uma Opção: ");
            System.out.println("[1]Imprimir Hello World");
            System.out.println("[2]Imprimir Ola Mundo ");
            System.out.println("[3]Imprimir Ola Que Tal");
            System.out.println("[4]Sair");
            option = sc.nextInt();

            if(option == 1){
                HelloWorld();
            }else if(option == 2){
                OlaMundo();
            }else if(option == 3){
                OlaQueTal();
            }else if(option == 4){
                System.out.println("Saindo...");
            }else{
                System.out.println("Error");
            }
        }
        
    }

}