package Alunos.Gabriel.Aula11;

import java.util.Scanner;


public class Main{

public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
int opcao = 0;

while (opcao !=7){
    System.out.println("//   ATIVIDADES  \\");
    System.out.println("Atividade 1");
    System.out.println("Atividade 2");
    System.out.println("Atividade 3");
    System.out.println("Atividade 4");
    System.out.println("Atividade 5");
    System.out.println("Atividade 6");
    System.out.println("SAIR 7");
    opcao = scanner.nextInt();


switch (opcao) {
    case 1->{Produto.atv1();}

    case 2->{Produto.atv2();}

    case 3->{Produto.atv3();}

    case 4->{Produto.atv4();}

    case 5->{Produto.atv5();}

    case 6->{Produto.atv6();}

    case 7->{System.out.println("Saindo!");
                scanner.close();}

    default-> { System.out.println("Algo deu errado!!!");}
        
} 
}
}
}
