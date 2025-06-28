import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Map<String, Usuario> usuariosSalvos = Armazenamento.carregar();
        Scanner sc = new Scanner(System.in);

        System.out.println("\n|---------------------------------------------------|");
        System.out.println("|--------Seja bem-vindo ao Disnazonflix Max+--------|");
        System.out.println("|---------------------------------------------------|\n");

        System.out.print("Digite seu nome ou apelido:\n");
        String nomeDigitado = sc.nextLine().trim();
        String chaveUsuario = Armazenamento.padronizaChave(nomeDigitado);

        Usuario usuario;

        if (usuariosSalvos.containsKey(chaveUsuario)) {

            usuario = usuariosSalvos.get(chaveUsuario);
            System.out.println("\nBem-vindo de volta, " + usuario.getNome() + "!");

        } else {

            usuario = new Usuario(nomeDigitado); 
            usuariosSalvos.put(chaveUsuario, usuario); 
            Armazenamento.salvar(usuariosSalvos);
            System.out.println("\nNovo usuario criado! Bem-vindo, " + usuario.getNome() + "!");

        }

        Menu menu = new Menu(usuario, usuariosSalvos);
        menu.exibir();

    }
}