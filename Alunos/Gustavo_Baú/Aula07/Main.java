package Alunos.Gustavo_Baú.Aula07;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Criando endereço
        Endereco enderecoLoja = new Endereco("SP", "São Paulo", "Centro", "Av. Principal", "1000");
        
        // Criando gerente
        Gerente gerente = new Gerente("Maria", 35, enderecoLoja, "My Plant Centro", 4000);
        gerente.apresentar();
        System.out.println("Bônus: R$" + gerente.calcularBonus());
        
        // Criando itens
        Item vaso = new Item(1, "Vaso Decorativo", "Decoração", 59.90);
        Item planta = new Item(2, "Suculenta", "Planta", 29.90);
        
        List<Item> itensPedido = new ArrayList<>();
        itensPedido.add(vaso);
        itensPedido.add(planta);
        
        // Processando pedido
        ProcessaPedido processador = new ProcessaPedido();
        Pedido pedido = processador.criarPedido(1, gerente, gerente, "My Plant Centro", itensPedido);
        pedido.mostrarPedido();
    }
}