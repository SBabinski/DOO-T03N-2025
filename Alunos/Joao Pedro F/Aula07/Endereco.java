public class Endereco {
    private final String estado;
    private final String cidade;
    private final String bairro;
    private final int numero;
    private final String complemento;

    public Endereco(String estado, String cidade, String bairro, int numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public void apresentarLogradouro() {
        System.out.println("Endereço: " + ruaFormatada());
    }

    public String ruaFormatada() {
        return bairro + ", " + numero + " - " + cidade + " / " + estado + " (" + complemento + ")";
    }
}
