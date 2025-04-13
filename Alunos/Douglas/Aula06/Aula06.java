/**
 *
 * @author dougbruno
 */

public class Aula06 {

    public static void main(String[] args) {
        Loja loja = new Loja("My Plant", "My Plant LTDA", "12.345.678/0001-90", "Cascavel", "Centro", "Avenida Brasil");

        Vendedor vendedor1 = new Vendedor("Douglas", 21, "My Plant", "Cascavel", "Centro", "Avenida Brasil", 3000.0,
                new double[]{3100.0, 3200.0, 3300.0});
        Vendedor vendedor2 = new Vendedor("Bruno", 25, "My Plant", "Cascavel", "Centro", "Avenida Brasil", 2800.0,
                new double[]{2900.0, 2950.0, 3000.0});

        loja.adicionarVendedor(vendedor1);
        loja.adicionarVendedor(vendedor2);

        Cliente cliente1 = new Cliente("João", 45, "Cascavel", "Floresta", "Rua Uirapuru");
        Cliente cliente2 = new Cliente("Ana", 33, "Cascavel", "Centro", "Rua Paraná");

        loja.adicionarCliente(cliente1);
        loja.adicionarCliente(cliente2);

        vendedor1.apresentarse();
        System.out.println("Média dos salários: " + vendedor1.calcularMedia());
        System.out.println("Bônus: " + vendedor1.calcularBonus());
        System.out.println();

        cliente1.apresentarse();
        System.out.println();

        loja.apresentarse();
        System.out.println("Total de Clientes: " + loja.contarClientes());
        System.out.println("Total de Vendedores: " + loja.contarVendedores());
    }
}

 