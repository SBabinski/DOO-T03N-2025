package Alunos.Gabriel.Aula07;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Endereco endereco = new Endereco(
            "PR",
            "Cascavel",
            "Centro",
            "Av.Brasil",
            1234,
            "Mercado");
         Gerente gerente = new Gerente(
            "Alex", 
            35, 
            "MyPlant", 
            2000.00, 
            "Cascavel", 
            "Centro", 
            "Av.Brasil");
        Item item1 = new Item(
            123456,
            "Tulipa",
            "Liliaceae",
            20.00);
        Item item2 = new Item(
            987654,
            "Rosa",
            "Liliaceae",
            15.00);  
        Pedido pedido = new Pedido(
            "Ana", 
            LocalDate.of(2025,4,12), 
            LocalDate.of(2025, 4, 13), 
            LocalDate.of(2025, 4, 20), 
            1,  
            "MyPlant", 
            "Cesar");
        ProcessaPedido processadoPedido = new ProcessaPedido();
            int opcao = 0;
            while(opcao != 5){
            
            System.out.println("//   MENU    //");
            System.out.println("1 - Mostrar informacoes do Gerente");
            System.out.println("2 - Mostrar informacoes de Endereco ");
            System.out.println("3 - Mostrar informacoes do Item");
            System.out.println("4 - Mostrar informacoes do Pedido");
            System.out.println("5 - SAIR");
            opcao = scanner.nextInt();

             switch(opcao){
                case 1 ->{
                    gerente.apresentarse();
                    gerente.mediaSalario();
                    gerente.bonus();
                }
                case 2 ->{
                    endereco.apresentarLogradouro();
                }
                case 3 ->{
                    item1.gerarDescricao();
                    item2.gerarDescricao();
                }
                case 4 ->{
                    pedido.gerarDescricaoVenda();
                    pedido.calcularValorTotal(item1, item2);
                    processadoPedido.processar(pedido);

                }
                case 5 -> {
                    System.out.println("SAINDO!!!"); 
                    scanner.close();
                
                }
                default ->{
                    System.out.println("ALGO DEU ERRADO!!!");
                }
             }
            }
           
            
            
    }


}
