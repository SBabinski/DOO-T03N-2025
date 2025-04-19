public class Endereco {
    private String estado;
    private String cidade;
    private String bairro;
    private String numero;
    private String complemento;
    private Endereco lojaEndereco;

    public Endereco(String estado, String cidade, String bairro, String numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void apresentarLogradouro(){
        String Ambiente = "Estado:".concat(getEstado()).concat("cidade:").concat(getCidade()).concat("Bairro:").concat(getBairro())
                .concat("Numero:").concat(getNumero()).concat("Nome da Loja ou complemento:").concat(getComplemento());
    }
}