import java.util.Scanner;

public class Hello {


    public static void HelloWorld(){
        System.out.println("Hello World");
    }

    public static void OlaMundo(){
        System.out.println("Ola Mundo");
    }
    public static void main(String[] args) {

    Scanner sc =  new Scanner(System.in);
    int option;

        System.out.println("Escolha uma Opção: ");
        System.out.println("[1]Imprimir Hello World");
        System.out.println("[2]Imprimir Ola Mundo ");
        option = sc.nextInt();

            if (option == 1){
                HelloWorld();
            }else if (option == 2){
                OlaMundo();
            }
        
    }

}