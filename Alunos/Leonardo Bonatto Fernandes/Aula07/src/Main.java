import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // 1. Criar endereço
        Endereco enderecoLoja = new Endereco();
        enderecoLoja.estado = "SP";
        enderecoLoja.cidade = "São Paulo";
        enderecoLoja.bairro = "Centro";
        enderecoLoja.rua = "Rua Principal";
        enderecoLoja.numero = "100";
        
        // 2. Criar gerente
        Gerente gerente = new Gerente();
        gerente.nome = "João Silva";
        gerente.idade = 35;
        gerente.endereco = enderecoLoja;
        gerente.loja = "My Plant Centro";
        gerente.salarioBase = 5000;
        gerente.salariosRecebidos[0] = 5000;
        gerente.salariosRecebidos[1] = 5500;
        gerente.salariosRecebidos[2] = 6000;
        
        // 3. Criar cliente
        Cliente cliente = new Cliente();
        cliente.nome = "Maria Oliveira";
        cliente.idade = 28;
        cliente.endereco = enderecoLoja;
        
        // 4. Criar itens
        Item item1 = new Item();
        item1.id = 1;
        item1.nome = "Vaso de Planta";
        item1.tipo = "Jardim";
        item1.valor = 50;
        
        Item item2 = new Item();
        item2.id = 2;
        item2.nome = "Terra Adubada";
        item2.tipo = "Jardinagem";
        item2.valor = 20;
        
        ArrayList<Item> itensPedido = new ArrayList<>();
        itensPedido.add(item1);
        itensPedido.add(item2);
        
        // 5. Processar pedido
        ProcessaPedido processador = new ProcessaPedido();
        Pedido pedido = processador.processar(1, cliente, gerente, "My Plant Centro", itensPedido);
        
        // 6. Mostrar resultados
        System.out.println("\n--- Informações do Pedido ---");
        pedido.mostrarPedido();
        
        System.out.println("\n--- Informações do Gerente ---");
        gerente.apresentar();
        System.out.printf("Média salarial: R$%.2f\n", gerente.calcularMedia());
        System.out.printf("Bônus: R$%.2f\n", gerente.calcularBonus());
    }
}