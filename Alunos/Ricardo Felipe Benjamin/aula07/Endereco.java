public class Endereco {
    
    public String estado;
    public String cidade;
    public String rua;
    public String bairro;
    public String complemento;
    public Integer numero;


    public Endereco(String estado, String cidade, String rua, String bairro, String complemento, Integer numero) {

                this.estado = estado;
                this.cidade = cidade;
                this.rua = rua;
                this.bairro = bairro;
                this.complemento = complemento;
                this.numero = numero;
    }


    public void apresentarLogradouro() {
        System.out.println(rua + ", nº " + numero + " - " + bairro + ", " + cidade + " - " + estado + ". " + complemento);
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }


}
