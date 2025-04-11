package fag;

public class Main {
    public static void main(String[] args) {
       
        Loja myPlant = new Loja("My Plant", "My Plant Comércio de Plantas LTDA",
                "12.345.678/0001-90", "Cascavel", "Centro", "Rua das Flores, 123");

        
        Vendedor vendedor1 = new Vendedor("Carlos", 30, myPlant, "Cascavel", "Centro", "Rua das Palmeiras", 2000.0);
        Vendedor vendedor2 = new Vendedor("Ana", 25, myPlant, "Cascavel", "Jardim", "Rua Pernabumco", 2100.0);

        
        myPlant.adicionaVendedor(vendedor1);
        myPlant.adicionaVendedor(vendedor2);

        
        Cliente cliente1 = new Cliente("Fernanda", 28, "Cascavel", "Neva", "Rua XV");
        Cliente cliente2 = new Cliente("João", 40, "Cascavel", "Coqueiral", "Rua Rio de Janeiro");

      
        myPlant.adicionaCliente(cliente1);
        myPlant.adicionaCliente(cliente2);

        
        System.out.println("== Apresentação da Loja ==");
        myPlant.apresentarse();

        System.out.println("\n== Vendedores ==");
        vendedor1.apresentarse();
        System.out.println("Média salarial: " + vendedor1.calcularMedia());
        System.out.println("Bônus: " + vendedor1.calcularBonus());

        System.out.println();

        vendedor2.apresentarse();
        System.out.println("Média salarial: " + vendedor2.calcularMedia());
        System.out.println("Bônus: " + vendedor2.calcularBonus());

        System.out.println("\n== Clientes ==");
        cliente1.apresentarse();
        cliente2.apresentarse();

        System.out.println("\n== Contagens ==");
        myPlant.contarVendedores();
        myPlant.contarClientes();
    }
}
