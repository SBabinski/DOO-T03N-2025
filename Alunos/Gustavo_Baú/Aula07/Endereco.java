package Alunos.Gustavo_Baú.Aula07;

public class Endereco {
    
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;
    
    public Endereco(String estado, String cidade, String bairro, String rua, String numero) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }
    
    public void mostrarEndereco() {
        System.out.println("Endereço completo:");
        System.out.println(rua + ", " + numero);
        System.out.println(bairro + ", " + cidade + " - " + estado);
    }
}