import java.util.ArrayList;
import java.util.Scanner;

public class Veiculo extends Cliente {
    public static Scanner scanner = new Scanner(System.in);
    private final String automovel;
    private final String modelo;
    private final String marca;
    public static ArrayList<Veiculo> Automoveis = new ArrayList<>();
public static ArrayList<Veiculo>Remov = new ArrayList<>();
    public Veiculo(String nome, int idade, String automovel, String modelo, String marca) {
        super(nome, idade);
        this.automovel = automovel;
        this.modelo = modelo;
        this.marca = marca;

    }

    public String getAutomovel() {
        return automovel;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                ", Nome do dono do automovel='" + getNome() + '\'' +
                ", Idade do dono do automovel='" + getIdade() + '\'' +
                ", automovel='" + automovel + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }

    public void Addveiculo(Veiculo Veic_ulo){
        Automoveis.add(Veic_ulo);
    }

    public static void SaidaDecarro(){
        System.out.println("O seu automovel é um carro, uma moto...?");
        String Au = scanner.next();

        System.out.println("Qual o modelo do automovel");
        String Mo = scanner.next();

        System.out.println("Qual a marca do automovel");
        String Ma = scanner.next();

        for(Veiculo  L : Automoveis) {
            if (L.getAutomovel().equalsIgnoreCase(Au)
                    && L.getModelo().equalsIgnoreCase(Mo)
                    && L.getMarca().equalsIgnoreCase(Ma)) {
                Remov.add(L);

            }
        }
        Automoveis.removeAll(Remov);
        System.out.println("O carro saiu");
    }
    public void Listar() {
        for(Veiculo e : Automoveis){
            System.out.println(e);
        }
        System.out.println("O seu automovel é um carro, uma moto...?");
        String Au = scanner.next();

        System.out.println("Qual o modelo do automovel");
        String Mo = scanner.next();

        System.out.println("Qual a marca do automovel");
        String Ma = scanner.next();
        for(Veiculo L : Automoveis) {
            if (L.getAutomovel().equalsIgnoreCase(Au)
                    && L.getModelo().equalsIgnoreCase(Mo)
                    && L.getMarca().equalsIgnoreCase(Ma)) {
                System.out.println(L);
            }

        }
    }
}
