import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Menu {
public static Cliente Comprador;
public static Veiculo Veic_ulo;
public static Mecanico pessoa;
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        while (true) {
            System.out.println(" 1 - Cadastro de cliente, 2 - cadastro de veiculos e entrada do veiculo " +
                    "3 - buscar veiculo por modelo ou marca, " +
                    "4 - saida do veiculo, 5 - mecanico, 6 -trocar oleo/mecanismos, 7 - Sair ");
            int opcao = scanner.nextInt();

            switch (opcao){
                case 1:
                    Client();
                    break;
                case 2:
                    Cadastro_Veiculos(Comprador);
                    break;
                case 3:
                    Buscar_veiculo(Veic_ulo);
                    break;
                case 4:
                    SairVeiculo(Veic_ulo);
                    break;
                case 5:
                    CriarMecanico();
                    break;
                case 6:
                    MecanicoEtrocas(pessoa);
                    break;
                case 7:
                    System.out.println("Você saiu do site");
                    return;
            }
        }
    }
    public static void Client(){
System.out.println("Nome do cliente");
String num = scanner.next();

System.out.println("idade do cliente");
int idad = scanner.nextInt();

Comprador = new Cliente(num,idad);
    }
    public static void Cadastro_Veiculos(Cliente comprador){
       System.out.println("É uma moto ou um carro?");
        String automovel = scanner.next();

        System.out.println("Qual é o modelo?");
        String modelo = scanner.next();

        System.out.println("Qual é a marca?");
        String marca = scanner.next();

       Veic_ulo = new Veiculo(comprador.getNome(), comprador.getIdade(),automovel,modelo,marca);
        Veic_ulo.Addveiculo(Veic_ulo);

    }
    public static void  Buscar_veiculo(Veiculo Veic_ulo){
        Veic_ulo.Listar();
    }
public static void SairVeiculo(Veiculo Veic_ulo){
        Veic_ulo.SaidaDecarro();
}

    public static void MecanicoEtrocas(Mecanico pessoa){
       pessoa.Listarmecainco_arrumar_automovel();
    }
    public static void CriarMecanico() {
System.out.println("Nome do mecanico");
String Name = scanner.next();

System.out.println("Idade do mecanico");

int Idade = scanner.nextInt();

System.out.println("Disponivel ou ocupado o mecanico");
String Diponibi = scanner.next();

pessoa = new Mecanico(Name,Idade,Diponibi);

    }
    }
