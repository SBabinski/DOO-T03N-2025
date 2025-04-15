public class classesEObjetos {
    public static void main(String[] args) {
        Loja myPlant = new Loja("My Plant", "My Plant Ltda", "12.345.678/0001-99", "Floripa", "Centro", "Rua das Flores");

        Cliente cliente1 = new Cliente("Ana", 30, "Floripa", "Centro", "Rua do Sol");
        Cliente cliente2 = new Cliente("Bruno", 25, "Floripa", "Trindade", "Rua das Árvores");

        Vendedor vendedor1 = new Vendedor("Carlos", 28, myPlant, "Floripa", "Centro", "Rua das Rosas", 3000.0);

        myPlant.adicionaCliente(cliente1);
        myPlant.adicionaCliente(cliente2);
        myPlant.adicionaVendedor(vendedor1);

        myPlant.apresentarse();
        cliente1.apresentarse();
        vendedor1.apresentarse();

        System.out.println("Clientes: " + myPlant.contarClientes());
        System.out.println("Vendedores: " + myPlant.contarVendedores());
        System.out.println("Média Salarial: R$" + vendedor1.calcularMedia());
        System.out.println("Bônus: R$" + vendedor1.calcularBonus());
    }
}