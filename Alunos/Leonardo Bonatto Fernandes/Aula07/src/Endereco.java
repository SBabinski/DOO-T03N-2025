public class Endereco {
    public String estado;
    public String cidade;
    public String bairro;
    public String rua;
    public String numero;
    
    public void mostrarEndereco() {
        System.out.println(rua + ", " + numero + ", " + bairro + ", " + cidade + " - " + estado);
    }
}