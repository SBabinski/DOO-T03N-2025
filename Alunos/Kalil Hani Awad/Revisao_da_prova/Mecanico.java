import java.util.ArrayList;
import java.util.Scanner;

public class Mecanico {
    public static Scanner scanner = new Scanner(System.in);
    private String nome;
    private int idade;
    public static ArrayList<Mecanico> Mecanica = new ArrayList<>();
    private String disponivel;

    public Mecanico(String nome, int idade, String disponivel) {
        this.nome = nome;
        this.idade = idade;
        this.disponivel = disponivel;
        Mecanica.add(this);
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }

    public String getDisponivel() {
        return disponivel;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public void Trocar_Oleo() {
        System.out.println("Oleo trocado");
    }

    @Override
    public String toString() {
        return "Mecanico{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", disponivel='" + disponivel + '\'' +
                '}';
    }

    public void Listarmecainco_arrumar_automovel() {
        System.out.println("Todos os mecanicos");
        for (Mecanico M : Mecanica) {
            System.out.println(M);
        }
        System.out.println("Qual dos mecanicos vai fazer o procedimento?");
        String Nume_meca = scanner.next();
        for (Mecanico p : Mecanica) {
            if (Nume_meca.equalsIgnoreCase(p.getNome())) {
                p.setDisponivel("Ocupado");
                for (Mecanico M : Mecanica) {
                    System.out.println(M);
                }
            }
        }
            Trocar_Oleo();
            System.out.println("O que quer trocar no carro");
            String objeto = scanner.next();
            System.out.println("Foi trocado:" + objeto);

            for (Mecanico p : Mecanica) {
                if (Nume_meca.equalsIgnoreCase(p.getNome())) {
                    p.setDisponivel("Disponivel");
                }
                System.out.println(p);
            }


        }
    }








