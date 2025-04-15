package objetos;

public class Endereco {
    String estado;
    String cidade;
    String bairro;
    public String rua;
    int numero;
    String complemento;

    public Endereco(String estado, String cidade, String bairro, String rua, int numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public void apresentarLogradouro() {
        System.out.println("Endereço: " + rua + ", nº " + numero + ", " + complemento + " - " + bairro + ", " + cidade + " - " + estado);
    }


}