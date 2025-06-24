import model.serie;
import model.usuario;
import service.SerieService;
import util.Persistencia;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SerieService api = new SerieService();
        usuario usuario = Persistencia.carregar();

        if (usuario == null) {
            System.out.print("Informe seu nome: ");
            usuario = new usuario();
            usuario.setNome(sc.nextLine());
        }
        
         System.out.println("Olá Winicius, seja bem vindo novamente");

        while (true) {
            System.out.println("\n1. Buscar série\n2. Ver listas\n3. Salvar e sair");
            int opcao = sc.nextInt(); sc.nextLine();


            try {
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Nome da série: ");
                        serie serie = api.buscarPorNome(sc.nextLine());
                        System.out.println(serie);

                        System.out.println("\nAdicionar a:\n1. Favoritas\n2. Assistidas\n3. Desejadas\n4. Cancelar");
                        int lista = sc.nextInt(); sc.nextLine();
                        switch (lista) {
                            case 1 -> usuario.adicionarSerie("favoritas", serie);
                            case 2 -> usuario.adicionarSerie("assistidas", serie);
                            case 3 -> usuario.adicionarSerie("desejadas", serie);
                        }
                    }
                    case 2 -> {
                        System.out.println("Qual lista? (favoritas/assistidas/desejadas)");
                        String tipo = sc.nextLine();
                        List<serie> lista = usuario.getLista(tipo);
                        if (lista.isEmpty()) {
                            System.out.println("Lista vazia.");
                        } else {
                            lista.forEach(System.out::println);
                        }
                    }
                    case 3 -> {
                        Persistencia.salvar(usuario);
                        System.out.println("Dados salvos. Saindo...");
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
